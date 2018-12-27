$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "http://localhost:9090/getAllUser",
        success: function (message) {
            if (message) {
                for (var i in message) {
                    $("tbody").append("<tr> <td>" + message[i]["username"] + "</td> " +
                        "<td>" + message[i]["usercode"] + "</td> " +
                        "<td>" + timestampToTime(message[i]["userbirth"]) + "</td> </tr>");
                }
            } else {
                alert("获取失败!");
            }
        },
        error: function (message) {
            alert("获取失败!");
        }
    });

    $("#sub").click(function () {
        var username = $("#username").val();
        var usercode = $("#usercode").val();
        var userbirth = new Date($("#userbirth").val());

        console.log(username);
        console.log(usercode);
        console.log(userbirth);

        var json = {
            'userbirth' : userbirth.getTime(),
            'usercode' : usercode,
            'username' : username
        };

        $.ajax({
            type: "POST",
            url: "http://localhost:9090/addOneUser",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(json),
            dataType: "json",
            success: function () {
                window.location.reload();
            },
            error: function () {
                window.location.reload();
            }
        });
    });
});


function timestampToTime(timestamp) {
    var date = new Date(timestamp);
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate();
    return Y+M+D;
}