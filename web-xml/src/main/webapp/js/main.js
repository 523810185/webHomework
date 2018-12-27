var studentList = [];

function onQueryBtnHandler()
{
    queryStudentList();
}

function queryStudentList() {
    var userID = document.getElementById("userID").value;
    var userName = document.getElementById("userName").value;
    var createTimeUp = document.getElementById("createTimeUp").value;
    var createTimeDown = document.getElementById("createTimeDown").value;
    var stateVal = document.getElementById("status").value;
    var channelVal = document.getElementById("channel").value;
    var state = 0;
    var channel = 0;
    if(stateVal == "禁用")
    {
        state = 1;
    }
    else if(stateVal == "黑名单")
    {
        state = 2;
    }
    if(channelVal == "微信公众号端")
    {
        channel = 1;
    }

    // post query
    $.ajax({
        type: "POST",
        url: "http://localhost:9090/queryStudentList",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            userID: userID,
            userName: userName,
            state: state,
            channel: channel,
            createTimeUpStamp: createTimeUp,
            createTimeDownStamp: createTimeDown
        }),
        dataType: "json",
        complete: function (xml, text) {
            var response = xml.responseText;
            var json = JSON.parse(response);
            console.log("接受的json：", json);
            studentList = [];
            for(var item of JSON.parse(json.data))
            {
                studentList.push(item);
            }
            showContent(0, 2);
        }
    });

    // 处理li
    var list = document.getElementsByClassName("pagination")[0].getElementsByTagName("li");
    for(var i=0;i<list.length;i++)
    {
        // list[i].onclick = () => {
        //     showContent(i*3, i*3+ 3 - 1);
        // };
        var f = (n) => {
            return () => {
                showContent(n*3, n*3 + 3 - 1);
            };
        };
        list[i].onclick = f(i - 1); // ???
        // console.log("waa",i,"-",list[i].onclick);
    }
}

function showContent(from, to) {
    console.log("yyy",from, "--",to);
    var table = document.getElementById("table");
    var tbody = table.getElementsByTagName("tbody")[0];

    for(var i = from; i <= to; i++)
    {
        var item = tbody.children[i-from];
        var list = item.getElementsByTagName("td");
        // 清空
        if(i + 1 > studentList.length)
        {
            for(var j=0;j<list.length;j++)
            {
                // var temp = list[j];
                if(j == 0)
                {
                    // list[j].getElementById("optionsRadios1").checked = false;
                    list[j].children[0].children[0].children[0].checked = false;
                }
                else
                {
                    // list[j].value = "";
                    list[j].innerText = "";
                }
            }
        }
        else // 填充
        {
            var jsonObj = studentList[i];
            for(var j=0;j<list.length;j++)
            {
                var temp = list[j];
                if(j == 0)
                {
                    // list[j].getElementById("optionsRadios1").checked = false;
                    list[j].children[0].children[0].children[0].checked = false;
                }
                else if(j == 1)
                {
                    list[j].innerText = jsonObj.userID;
                }
                else if(j == 2)
                {
                    list[j].innerText = jsonObj.userName;
                }
                else if(j == 3)
                {
                    list[j].innerText = jsonObj.college;
                }
                else if(j == 4)
                {
                    list[j].innerText = jsonObj.department;
                }
                else if(j == 5)
                {
                    list[j].innerText = jsonObj.major;
                }
                else if(j == 6)
                {
                    list[j].innerText = jsonObj.channel == 0 ? "PC端" : "微信公众号端";
                }
                else if(j == 7)
                {
                    list[j].innerText = timeStampToStr(jsonObj.createTimeStamp);
                }
                else if(j == 8)
                {
                    list[j].innerText = timeStampToStr(jsonObj.updateTimeStamp);
                }
                else if(j == 9)
                {
                    list[j].innerText = stateToStr(jsonObj.state)
                }
            }
        }
    }
}

function timeStampToStr(timeStamp) {
    let date = new Date(timeStamp);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
}

function stateToStr(num) {
    if(num == 0)
    {
        return "启用";
    }
    else if(num == 1)
    {
        return "禁用"
    }
    else if(num == 2)
    {
        return "黑名单";
    }
}

function queryMyName()
{
    var myid = localStorage.getItem("myid");
    $.ajax({
        type: "POST",
        url: "http://localhost:9090/queryNameByID",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            userID: myid
        }),
        dataType: "json",
        complete: function (xml, text) {
            var response = xml.responseText;
            var json = JSON.parse(response);
            console.log("接受的json：", json);
            var myName = JSON.parse(json.data)[0].userName;
            document.getElementById("myUserName").innerText = myName;
        }
    });
}

window.onload = function() {
    queryStudentList();
    queryMyName();
    setInterval(() => {
        var timeShow = document.getElementById("timeShow");
        var time = new Date();
        timeShow.innerText = time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate() + " " +
            time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds() + " ";
    },1000);
}