

function login() {
    // 获取用户名和密码
    var name = $("#name").val().trim();
    var password = $("#password").val().trim();

    // 校验用户名和密码
    if (name === "" || password === "") {
        alert("用户名和密码不能为空");
        return;
    }

    $.ajax({
        url: "/LoginServlet",
        type: "post",
        // data表示发送的数据
        data:"userName="+ name +"&userPassword=" + password,

        success: function (data) {
            if (data != null && data.success) {
                location.href = "success.html";
            } else {
                console.log("登录失败：" + data);
                alert("登录失败，请检查用户名和密码是否正确。");
            }
        },
        error: function (xhr, status, error) {
            console.log("请求发生错误：" + error);
            alert("请求发生错误，请稍后重试。");
        }
    });
}