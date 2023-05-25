

var imageInput = document.getElementById('image-input');
var imagePreview = document.getElementById('image-preview');

// 点击图片时触发事件，显示文件选择对话框
imagePreview.addEventListener('click', function(event) {
    imageInput.click();
});

imageInput.addEventListener('change', function(event) {
    var file = event.target.files[0];

    // 判断上传的文件是否为图片文件
    var fileType = file.type;
    var isImage = /^image\//.test(fileType);
    if (!isImage) {
        alert('请上传图片文件');
        return;
    }

    // 使用FileReader对象读取文件
    var reader = new FileReader();
    reader.onload = function(event) {
        // 将读取的数据用于更新图片元素
        imagePreview.src = event.target.result;

        // 创建FormData对象，并将图片文件添加到其中
        var formData = new FormData();
        formData.append('image', file);

        // 创建XMLHttpRequest对象，发送FormData对象到Servlet类
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/UServlet');
        xhr.onload = function() {
            if (xhr.status === 200) {
                console.log('图片上传成功');
            } else {
                console.log('图片上传失败');
            }
        };
        xhr.send(formData);
    }
    reader.readAsDataURL(file);
});