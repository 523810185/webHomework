function regist() {
    var userID = document.getElementById("studentNumber").value;
    var password = document.getElementById("studentPassword").value;
    var passwordAgain = document.getElementById("studentVerifyPassword").value;
    if(userID == "")
    {
        alert("请输入用户名！");
    }
    else if(password == "")
    {
        alert("请输入密码！");
    }
    else if(password != passwordAgain)
    {
        alert("两次密码必须相同！");
    }
    else
    {
        postRegistMessage(userID, password);
    }
}

function postRegistMessage(userID, password) {
    var userName = document.getElementById("studentName").value;
    var college = document.getElementById("studentCollege").value;
    var department = document.getElementById("studentDepartment").value;
    var major = document.getElementById("studentMajor").value;
    var nowTime = new Date();
    var createTimeStamp = nowTime.getTime();
    var updateTimeStamp = nowTime.getTime();
    var channel = 0;
    var state = 0;
    $.ajax({
        type: "POST",
        url: "http://localhost:9090/newUserRegist",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            userID: userID,
            password: password,
            userName: userName,
            createTimeStamp: createTimeStamp,
            updateTimeStamp: updateTimeStamp,
            college: college,
            department: department,
            major: major,
            channel: channel,
            state: state
        }),
        dataType: "json",
        complete: function (xml) {
            var registResult = xml.responseText;
            if(registResult == "SUCCESS")
            {
                alert("注册成功！");
                window.location.href='./login.html';
            }
            else if(registResult == "USERNAME_EXIST")
            {
                alert("该账号已被注册！");
            }
        }
    });
}