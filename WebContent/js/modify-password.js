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
    
    var originPas;
    var originrePas;
    var isPass = false;
    
    var ctrl = {
        modify: function () {
            
            console.log(originPas);
            console.log(originrePas);
            originPas = $('#password').val();
            originrePas = $('#rePassword').val();
            
            if (originPas !== originrePas) {
                swal('两次输入的密码不一样~请重新输入');
                return;
            }
            
            if (!isPass) {
                return;
            }
            
            var params = {
                password: $('#password').val()
            }
            
            console.log("getCookieValue('user_id'): " + getCookieValue('username'))
            $.post('/users/update', params)
            .success(function(res) {
                swal({
                    title : '密码修改成功~',
                    type : "success",
                    confirmButtonColor : "#DD6B55",
                    confirmButtonText : "确定！",
                    closeOnConfirm : false
                }, function() {
                    var url = 'http://' + window.location.host + '/index.html'
                    window.location.replace(url);
                });
            })
            .error(function(res) { 
                swal(res.responseJSON.statusMsg);
            })

        }
    }
    
    $('#password').keyup(function (e) {
        
        originPas = $('#password').val();
        originrePas = $('#rePassword').val();
        var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
        var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Za-z0-9])(?=.*[a-z]))).*$", "g");
        var enoughRegex = new RegExp("(?=.{6,}).*", "g");
        if (false == enoughRegex.test(originPas))
        {
            $('#passstrength').html('弱');
        }
        else if (strongRegex.test(originPas))
        {
//            $('#passstrength').toggleClass("ok");
            $('#passstrength').css("color","green");
            $('#passstrength').html('强');
            isPass = true;
        }
        else if (mediumRegex.test(originPas))
        {
//            $('#passstrength').toggleClass("alert");
            $('#passstrength').css("color","orange");
            $('#passstrength').html('中');
            isPass = true;
        }
        else
        {
//            $('#passstrength').toggleClass("error");
            $('#passstrength').css("color","red");
            $('#passstrength').html('弱');
            isPass = false;
        }
    });
    
    $('#modify').on('click', function () {
        ctrl.modify();
    });
});