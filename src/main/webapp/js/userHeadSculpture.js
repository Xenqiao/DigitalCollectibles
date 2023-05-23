

$(function(){

    // 监听文件改变
    var inputFile = document.getElementById('avatarFile')
    inputFile.addEventListener('click', function() {this.value = null;}, false);
    inputFile.addEventListener('change', readData, false);
})


// 文件改变响应
function readData(evt) {
    evt.stopPropagation();
    evt.preventDefault();

    var file = evt.dataTransfer !== undefined ? evt.dataTransfer.files[0] : evt.target.files[0];

    if (!file.type.match(/image.*/)) {return;}
    var reader = new FileReader();
    reader.onload = (function() {

        return function(e) {
            var img = new Image();
            img.src = e.target.result;

            img.onload = (function() {

                var canvas = document.createElement('canvas');
                canvas.width = 800;
                canvas.height = 800;

                var ctx = canvas.getContext("2d");

                ctx.clearRect(0, 0, canvas.width, canvas.height); // canvas清屏

                //重置canvans宽高 canvas.width = img.width; canvas.height = img.height;

                ctx.drawImage(img, 0, 0, canvas.width, canvas.height); // 将图像绘制到canvas上

                //必须等压缩完才读取canvas值，否则canvas内容是黑帆布
                canvas.toDataURL("image/jpeg");

                cropAndUploadImage(canvas.toDataURL("image/jpeg"), function(dataUrl) {
                    // 在回调函数中更新图片路径
                    var imagePath = dataUrl;
                    var avatarImg = document.getElementById("avatarImg");
                    avatarImg.src = imagePath; // 设置图片路径

                    setTimeout(function(){
                        window.location.reload();
                    },1000);
                });
            });
        }
    })(file);

    reader.readAsDataURL(file);
}


function cropAndUploadImage(base64,callBack){

    var b64 = base64.split(",")[1];
    // var load = layerLoad();
    $.ajax({
        url: '/UploadImgServlet',
        type: "post",
        data:{
            photo : b64,
        },
        dataType: 'json',
        success:function(data) {
            if(data.success){
                callBack(data.message);
            }else {

            }
        },
        error: function (xhr, status, error) {
            console.log("请求发生错误：" + error);
        }
    })
}