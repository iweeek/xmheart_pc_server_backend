$(function () {
    if (!Array.prototype.indexOf) {
        Array.prototype.indexOf = function (ele) {
            // 获取数组长度
            var len = this.length;
            // 检查值为数字的第二个参数是否存在，默认值为0
            var fromIndex = Number(arguments[1]) || 0;
            // 当第二个参数小于0时，为倒序查找，相当于查找索引值为该索引加上数组长度后的值
            if (fromIndex < 0) {
                fromIndex += len;
            }
            // 从fromIndex起循环数组
            while (fromIndex < len) {
                // 检查fromIndex是否存在且对应的数组元素是否等于ele
                if (fromIndex in this && this[fromIndex] === ele) {
                    return fromIndex;
                }
                fromIndex++;
            }
            // 当数组长度为0时返回不存在的信号：-1
            if (len === 0) {
                return -1;
            }
        }
    }
    var ctrl = {
        login: function () {
            var originPas = $('#password').val();
            var salt = Math.ceil(Math.random()*10);
            var params = {
                username: $('#username').val(),
                password: $.md5(($.md5(originPas).toString() + salt.toString())),
                salt: salt,
                expiredHour: 168
            }

            $.post('/tokens', params)
            .success(function(res) {
                document.cookie = "user_id=" + res.obj1.id;
                document.cookie = "user_type=" + res.obj1.userType;
                document.cookie = "xmheart_token=" + res.obj2;
                var url = 'http://' + window.location.host + '/manager.html'
                window.location.replace(url);
            })
            .error(function() { swal('账号或密码错误，登录失败~'); })

        }
    }

    $('#login').on('click', function () {
        ctrl.login();
    });
});