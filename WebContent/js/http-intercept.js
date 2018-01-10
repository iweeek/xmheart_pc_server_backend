$(function () {
    function getCookieValue(name) {
        var strCookie = document.cookie;
        var arrCookie = strCookie.split(";");
        for (var i = 0; i < arrCookie.length; i++) {
            var c = arrCookie[i].split("=");
            if (c[0].indexOf(name) > -1) {
                return c[1];
            }
        }
        return ''
    }

    if (!getCookieValue('xmheart_token')) {
        var url = 'http://' + window.location.host + '/login.html'
        window.top.location.href = url;
    }
   
//    $.ajaxSetup({
//        beforeSend:function(xhr){
//        		token = getCookieValue('xmheart_token');
//        		xhr.setRequestHeader("Authorization", token);  
//        }
//    });  
    
    $.ajaxSetup({
//	    error:function(xhr){
//	        	alert("An error occured: " + xhr.status + " " + xhr.statusText);
//	    	},
        beforeSend:function(xhr){
        	    xhr.setRequestHeader("Authorization", getCookieValue('xmheart_token'));  
        },
        error: function(jqXHR, textStatus, errorMsg){ // 出错时默认的处理函数
            // jqXHR 是经过jQuery封装的XMLHttpRequest对象
            // textStatus 可能为： null、"timeout"、"error"、"abort"或"parsererror"
            // errorMsg 可能为： "Not Found"、"Internal Server Error"等

            // 提示形如：发送AJAX请求到"/index.html"时出错[404]：Not Found
//            console.log(textStatus); // error
            if (jqXHR.status == 403) {
                swal({
                    title: "对不起，您没有该栏目的访问权限。",
//                    text: "返回上一页？",
                    type: "error",
//                    showCancelButton: true,
                    confirmButtonColor: "#8cd4f5",
                    confirmButtonText: "确定",
//                    cancelButtonText: "留在本页",
                    closeOnConfirm: false
                    })
                }
            
            if (jqXHR.status == 401) {
                //安全退出，清除客户端cookies
                document.cookie ='xmheart_token=;';
                document.cookie = "user_id=";
                document.cookie = "user_type=";
                swal({
                    title: "对不起，您需要重新登录。",
//                    text: "返回上一页？",
                    type: "error",
//                    showCancelButton: true,
                    confirmButtonColor: "#8cd4f5",
                    confirmButtonText: "确定",
//                    cancelButtonText: "留在本页",
                    closeOnConfirm: true
                }, function() {
                    location.reload(true);
                });
            }
//            
//            $("#logout").click(function() {
//                swal({
//                    title : "登出成功",
//                    type : "success",
//                    confirmButtonColor : "#8cd4f5",
//                    confirmButtonText : "确定",
//                    closeOnConfirm : true
//                }, function() {
//                    location.reload(true);
//                });
//              })
//            alert( '发送AJAX请求到"' + this.url + '"时出错[' + jqXHR.status + ']：' + errorMsg );        
        }
    });

//    $.ajax({
//    		headers: {
//    			'Authorization': getCookieValue('xmheart_token')
//    		}
//    })
  
    // 在接收到数据后做统一处理
    $(document).ajaxError(function (event, request, settings) {
        if (request.status == 401) {
            var url = 'http://' + window.location.host + '/login.html';
            window.top.location.href = url;
        }
    });
});
