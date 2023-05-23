



function showAlert(message) {

    // 创建遮罩层
    var maskDiv = document.createElement("div");
    maskDiv.style.position = "fixed";
    maskDiv.style.top = "0";
    maskDiv.style.left = "0";
    maskDiv.style.width = "100%";
    maskDiv.style.height = "100%";
    maskDiv.style.background = "rgba(0, 0, 0, 0.5)";
    maskDiv.style.zIndex = "9998";
    document.body.appendChild(maskDiv);


    var alertDiv = document.createElement("div");
    alertDiv.innerText = message;
    alertDiv.style.position = "fixed";
    alertDiv.style.top = "50%";
    alertDiv.style.left = "50%";
    alertDiv.style.transform = "translate(-50%, -50%)";
    alertDiv.style.background = "white";
    alertDiv.style.color = "black";
    alertDiv.style.padding = "20px";
    alertDiv.style.border = "2px solid black";
    alertDiv.style.borderRadius = "10px";
    alertDiv.style.boxShadow = "0 0 10px rgba(0, 0, 0, 0.3)";
    alertDiv.style.zIndex = "9999";
    document.body.appendChild(alertDiv);

    // 添加关闭按钮
    var closeButton = document.createElement("button");
    closeButton.innerText = "X";
    closeButton.style.position = "absolute";
    closeButton.style.top = "5px";
    closeButton.style.right = "5px";
    closeButton.style.background = "none";
    closeButton.style.border = "none";
    closeButton.style.fontSize = "16px";
    closeButton.style.cursor = "pointer";

    // 绑定点击事件
    closeButton.addEventListener("click", function() {
        // 移除遮罩层和弹窗
        document.body.removeChild(maskDiv);
        document.body.removeChild(alertDiv);
    });

    // 将关闭按钮添加到 alertDiv 中
    alertDiv.appendChild(closeButton);

    // 将遮罩层和弹窗添加到文档中
    document.body.appendChild(maskDiv);
    document.body.appendChild(alertDiv);
}

function register() {
    var username = $("#username").val().trim();
    var password = $("#password").val().trim();
    var confirmPassword = $("#confirm-password").val().trim();

    if (username === "" || password === "" || confirmPassword === "") {
        showAlert("所有字段都是必填项!!");
        return;
    }

    // 判断账号格式是否正确
    var Regex1 = /^[a-z]{1,10}$/;
    var Regex2 = /^[0-9]{1,10}$/;
    // 判断密码格式是否正确
    if (!Regex2.test(password) && !Regex1.test(password)) {
        showAlert("密码格式不正确，必须全部是数字或小写字母，长度为1至10位。");
        return;
    }
    if (!Regex1.test(username) && !Regex2.test(username)) {
        showAlert("账号格式不正确，必须全部为小写字母或数字，长度为1至10位。");
        return;
    }
    if (password !== confirmPassword) {
        showAlert("两次密码不匹配！");
        return;
    }

    $.ajax({
        url: "/RegisterServlet",
        type: "post",
        // data表示发送的数据
        data:"userName="+ username +"&userPassword=" + password,

        success: function (data) {

            if ( data.success === false ){
                console.log( "为什么嘞？ " + data.message );
                showAlert("用户已存在，为什么嘞？");
            } else {
                setTimeout(null, 10000);
                showAlert("注册成功！ 5秒钟后，自动为您跳转至首页 ");
                location.href = "success.html";
            }

        },

        error: function (xhr, status, error) {
            console.log("请求发生错误：" + error);
            showAlert("请求发生错误，\n请稍后重试。");
        }
    });
}