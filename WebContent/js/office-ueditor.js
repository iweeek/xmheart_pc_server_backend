exports = this;
exports.XPW = exports.EDIT || {};
exports.XPW.OfficeUeditor = (function() {
  function OfficeUeditor() {
    // 初始化页面处理。
	OfficeUeditor.getOfficeInfo()
  	OfficeUeditor.postOfficeInfo()
  	OfficeUeditor.uploadImg()
  	OfficeUeditor.ue = UE.getEditor('container');
	OfficeUeditor.isDisplayed = false;
	OfficeUeditor.cancel();
  }

  OfficeUeditor.getUrlParam = function (name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
      var r = window.location.search.substr(1).match(reg);  //匹配目标参数
      if (r != null) return unescape(r[2]); return null; //返回参数值
  }
  
  OfficeUeditor.fillData = function (data) {
	  $('#officeName').val(data.name);
//      $('#officeOutService').val(data.outService);
      $('.outservice[value="'+ data.outService +'"]').attr('checked','true');
      $('#typeSelectInput').val(data.deptId);
      OfficeUeditor.isDisplayed = data.isDisplayed;
      OfficeUeditor.ue.ready(function () {
        //设置编辑器的内容
    	  	OfficeUeditor.ue.setContent(data.intro);
      });
      if (data.imageUrl) {
    	  	  $('.upload-img').attr('src', data.imageUrl);
	  	  $('#addImgBtn').hide();
	  	  $('.add-image-url').show();
      }
  }
  
  OfficeUeditor.getOfficeInfo = function () {
    var id = OfficeUeditor.getUrlParam('deptId')
    if (id) {
	    $.ajax({
	  	  url: '/depts/' + id,
	        type: 'GET',
	        dataType: 'json',
	        data: {id: id}
	      })
	     .done(function(data) {
	    	   OfficeUeditor.fillData(data);
	     })
     }
  }
 
  OfficeUeditor.postOfficeInfo = function () {
	  var id = OfficeUeditor.getUrlParam('deptId');
	  var url = id ? '/depts/' + id : '/depts'
	  $('.btn-group').on('click', '#save', function() {
		  var $this = $(this);
		  $this.attr('disabled','disabled');
		  var name = $('#officeName').val();
	      var outService = $('.outservice:checked').val();
		  var isDisplayed = OfficeUeditor.isDisplayed;
		  var intro = OfficeUeditor.ue.getContent();
		  var imageUrl = $('.upload-img').attr('src');
		  var upateParms = {id: id, name: name, outService: outService, imageUrl: imageUrl, intro: intro, isDisplayed: isDisplayed};
		  var newParms = {name: name, outService: outService, imageUrl: imageUrl,  intro: intro, isDisplayed: isDisplayed};
		  var parms = id ? upateParms : newParms;
		  $.ajax({
		  	  	url: url,
		        type: 'POST',
		        dataType: 'json',
		        data: parms
		      })
		  .done(function(data) {
			  $this.removeAttr('disabled');
			  OfficeUeditor.fillData(data);
//			  swal("保存成功!");
			  console.log(data.id);
			  swal({
					title : "保存成功!",
					type : "success",
//					showCancelButton : true,
//					cancelButtonText : "继续编辑",
					confirmButtonColor : "#8cd4f5",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
					window.history.go(-1);
//					location.href="../static/office_ueditor.html?deptId=" + data.id;
				});
		  });
	  })
  }
  
  OfficeUeditor.uploadImg = function () {
	  $('.add-img-list').on('click', '#addImgBtn', function (){
		  $(this).siblings('.upload-form').find('.add-img-file').trigger('click');
	  })
	  $('.add-img-list').on('change', '.add-img-file', function (){
		  $(this).siblings('.add-img-submit').trigger('click');
	  })
	  $('#uploadForm').submit(function(){
		  $this = $(this);
		  $this.ajaxSubmit({
			  success: function (responseText) {
				  var img = responseText;
				  $this.siblings('.add-image-url').find('.upload-img').attr('src', img);
				  $this.siblings('.add-image-button').hide();
				  $this.siblings('.add-image-url').show();
			  }
		  });
		  return false;
	  })
	  $('.add-img-list').on('mouseover', '.add-image-url, .add-image-edit', function (){
		  $('.add-image-edit').show();
	  })
	  $('.add-img-list').on('mouseleave', '.add-image-url, .add-image-edit', function (){
		  $('.add-image-edit').hide();
	  })
	  $('.add-img-list').on('click', '.add-image-edit', function (){
		  $(this).siblings('.upload-form').find('.add-img-file').trigger('click');
	  })
  }
  
    OfficeUeditor.cancel = function() {
      $('#cancel').on('click', function(){
			// 取消(直接返回上一级，不做接口交互)
			window.history.go(-1);
      });
	}
  
  return OfficeUeditor;
})();

$(function () {
	exports.XPW.OfficeUeditor();
})