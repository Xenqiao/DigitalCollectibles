


$(function (){
    $("#exit").hide();//先让退出按钮消失
    $.post("FindUserServlet",{},function (data){
        let msg="欢迎回来，"+data.username;
        $("#span_username").html(msg);
        $("#load").hide();//登录成功之后让登录按钮消失
        $("#exit").show();//登陆成功之后再让退出按钮显示
    });
});