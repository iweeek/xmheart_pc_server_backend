exports = this;
exports.XPW = exports.EDIT || {};
exports.XPW.UserUeditor = (function() {
  function UserUeditor () {
    // 初始化页面处理。
	UserUeditor.roleData();
	UserUeditor.getRoleInfo();
  	UserUeditor.postRoleInfo();
	UserUeditor.cancel();
  }
  
  UserUeditor.roleData = function (selectedVal) {
	  $.get('/roles', function (data) {
          var optionString = '';
          for (var i in data) {
              var jsonObj = data[i];
              optionString += "<option value=\"" + jsonObj.id + "\" >" + jsonObj.name + "</option>";
          }
          $('#userRole').html("<option value='请选择'>请选择</option> " + optionString);
          if (selectedVal) {
        	  	 $('#userRole option[value='+selectedVal+']').attr('selected', 'selected');
          }
      });
  }

  UserUeditor._getUrlParam = function (name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
      var r = window.location.search.substr(1).match(reg);  //匹配目标参数
      if (r != null) return unescape(r[2]); return null; //返回参数值
  }
  
  UserUeditor.fillData = function (data) {
	  $('#userName').val(data.username);
	  $('#userPassword').val(data.password);
	  UserUeditor.roleData(data.roleId);
  }
  
  UserUeditor.getRoleInfo = function () {
    var id = UserUeditor._getUrlParam('userId');
    if (id) {
	    $.ajax({
	  	  url: '/users/' + id,
	        type: 'GET',
	        dataType: 'json',
	        data: {id: id}
	      })
	     .done(function(data) {
	    	 	UserUeditor.fillData(data);
	     })
     }
  }
 
  UserUeditor.postRoleInfo = function () {
	  var id = UserUeditor._getUrlParam('userId');
	  var url = id ? '/users/' + id : '/users'
	  console.log(id);
	  $('.btn-group').on('click', '#save', function() {
		  var $this = $(this);
		  $this.attr('disabled','disabled');
		  var username = $('#userName').val();
		  var password = $('#userPassword').val();
		  var roleId = $('#userRole').val();
		  if (!username) {
			  swal("名称不能为空");
			  $this.removeAttr('disabled');
			  return false;
		  }
		  if (!password) {
			  swal("密码不能为空");
			  $this.removeAttr('disabled');
			  return false;
		  }
		  if (!roleId) {
			  swal("角色不能为空");
			  $this.removeAttr('disabled');
			  return false;
		  }
		  var salt = Math.ceil(Math.random()*10);
		  var saltPassword = ($.md5(password).toString()/* + salt.toString()*/);
		  var upateParms = {id: id, username: username, password: saltPassword, roleId: roleId };
		  var newParms = {username: username, password: saltPassword, roleId: roleId };
		  var parms = id ? upateParms : newParms;
		  $.ajax({
		  	  	url: url,
		        type: 'POST',
		        dataType: 'json',
		        data: parms
		      })
		   .done(function(data) {
			  $this.removeAttr('disabled');
			  UserUeditor.fillData(data);
			  if (id) {
				  swal({
                      title: "编辑成功",
                      text: "返回上一页？",
                      type: "success",
                      showCancelButton: true,
                      confirmButtonColor: "#8cd4f5",
                      confirmButtonText: "返回上一页",
                      cancelButtonText: "留在本页",
                      closeOnConfirm: false
                  }, function () {
                      window.history.go(-1);
                  });
			  } else {
				  swal({
                      title: "创建成功",
                      text: "返回上一页？",
                      type: "success",
                      showCancelButton: true,
                      confirmButtonColor: "#8cd4f5",
                      confirmButtonText: "返回上一页",
                      cancelButtonText: "留在本页",
                      closeOnConfirm: false
                  }, function () {
                	  	var url = '/user_list.html';
                     location.href = url;
                  });
			  }
		  });
	  })
  }
  
  UserUeditor.cancel = function() {
      $('#cancel').on('click', function(){
			// 取消(直接返回上一级，不做接口交互)
			window.history.go(-1);
      });
  }
  
  
  return UserUeditor;
})();

$(function() {
	exports.XPW.UserUeditor();
})
