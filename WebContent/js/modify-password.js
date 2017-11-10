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

    var ctrl = {
        modify: function () {
            var originPas = $('#password').val();
            var originrePas = $('#rePassword').val();
            
            if (originPas !== originrePas) {
                swal('两次输入的密码不一样~请重新输入');
                return;
            }

            var params = {
                password: $.md5($('#password').val())
            }
            
            $.post('/users/' + getCookieValue('user_id'), params)
            .success(function(res) {
                swal('密码修改成功~');
                var url = 'http://' + window.location.host + '/manager.html'
                window.location.replace(url);
            })
            .error(function(res) { 
                swal(res.responseJSON.statusMsg);
            })

        }
    }

    $('#modify').on('click', function () {
        ctrl.modify();
    });
});