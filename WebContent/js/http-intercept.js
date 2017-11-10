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
