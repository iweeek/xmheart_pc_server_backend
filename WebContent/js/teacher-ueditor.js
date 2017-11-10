exports = this;
var deptId;
exports.XPW = exports.EDIT || {};
exports.XPW.DoctorUeditor = (function() {
  function DoctorUeditor() {
    // 初始化页面处理。
	DoctorUeditor.firstOfficeLoad()
  	DoctorUeditor.getDoctorInfo()
  	DoctorUeditor.postDoctorInfo()
  	DoctorUeditor.uploadImg()
  	DoctorUeditor.ue = UE.getEditor('container');
	DoctorUeditor.isDisplayed = false;
	DoctorUeditor.cancel();
  }

  DoctorUeditor._getUrlParam = function (name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
      var r = window.location.search.substr(1).match(reg);  //匹配目标参数
      if (r != null) return unescape(r[2]); return null; //返回参数值
  }
  
  DoctorUeditor.fillData = function (data) {
	  $('#doctorName').val(data.name);
      $('#doctorProfessiona').val(data.professionalTitle);
      $('#doctorDuty').val(data.duty);
      $('#typeSelectInput').val(data.deptId);
      deptId = data.deptId;
      $('#grade').val(data.grade);
      DoctorUeditor.isDisplayed = data.isDisplayed;
      DoctorUeditor.ue.ready(function () {
          //设置编辑器的内容
   	   	   DoctorUeditor.ue.setContent(data.intro);
      });
      if (data.imageUrl) {
    	  	  $('.upload-img').attr('src', data.imageUrl);
    	  	  $('#addImgBtn').hide();
		  $('.add-image-url').show();
      }
  }
  DoctorUeditor.firstOfficeLoad = function () {
	  $.ajax({
		  url: '/depts',
	      type: 'GET'
	    })
	   .done(function(data) {
	      var firstColumnTemplate = $('#firstColumnTemplate').html();
	      Mustache.parse(firstColumnTemplate);   
	      var rendered = Mustache.render(firstColumnTemplate, {data});
	      $('#typeSelectInput').html(rendered);
	      $('#typeSelectInput' + ' ' + 'option[value='+ deptId +']').attr('selected', 'selected');
	   })
  }
  
  DoctorUeditor.getDoctorInfo = function () {
    var id = DoctorUeditor._getUrlParam('teacherId')
    if (id) {
	    $.ajax({
	  	  url: '/teachers/' + id,
	        type: 'GET',
	        dataType: 'json',
	        data: {id: id}
	      })
	     .done(function(data) {
	    	 	DoctorUeditor.fillData(data);
	     })
     }
  }
 
  DoctorUeditor.postDoctorInfo = function () {
	  var id = DoctorUeditor._getUrlParam('teacherId');
	  var url = id ? '/teachers/' + id : '/teachers'
	  $('.btn-group').on('click', '#save', function() {
		  var $this = $(this);
		  $this.attr('disabled','disabled');
		  var name = $('#doctorName').val();
		  var deptId = $('#typeSelectInput').val();
		  var duty = $('#doctorDuty').val();
		  var professionalTitle = $('#doctorProfessiona').val();
		  var grade = $('#grade').val()
		  var intro = DoctorUeditor.ue.getContent();
		  var isDisplayed = DoctorUeditor.isDisplayed; 
		  var imageUrl = $('.upload-img').attr('src');
		  if (!name) {
			  swal("姓名不能为空");
			  $this.removeAttr('disabled');
			  return false;
		  }
		  if (!imageUrl) {
			  swal("头像不能为空");
			  $this.removeAttr('disabled');
			  return false;
		  }
		  if (!professionalTitle) {
			  swal("职称不能为空");
			  $this.removeAttr('disabled');
			  return false;
		  }
		  if (!intro) {
			  swal("简介不能为空");
			  $this.removeAttr('disabled');
			  return false;
		  }
		  var upateParms = {id: id, name: name, imageUrl:imageUrl, deptId: deptId, duty: duty, professionalTitle: professionalTitle, grade: grade, intro: intro, isDisplayed: isDisplayed};
		  var newParms = {name: name, deptId: deptId, imageUrl:imageUrl, duty: duty, professionalTitle: professionalTitle, grade: grade, intro: intro, isDisplayed: isDisplayed};
		  var parms = id ? upateParms : newParms;
		  $.ajax({
		  	  	url: url,
		        type: 'POST',
		        dataType: 'json',
		        data: parms
		      })
		  .done(function(data) {
			  $this.removeAttr('disabled');
			  DoctorUeditor.fillData(data);
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
			      $this.removeAttr('disabled');
				  $.get('/teachers',{deptId: deptId}, function(data){
					  swal({
	                      title: "创建成功",
	                      type: "success",
	                      showCancelButton: false,
	                      confirmButtonColor: "#8cd4f5",
	                      confirmButtonText: "确定",
	                      closeOnConfirm: false
	                  }, function () {
	                	  	  var url = '/teacher.html?pageNo=' + data.pages;
	                      location.href = url;
	                  });
				  })
			  }
		  });
	  })
  }
  
  DoctorUeditor.cancel = function() {
      $('#cancel').on('click', function(){
			// 取消(直接返回上一级，不做接口交互)
			window.history.go(-1);
      });
	}
  
  DoctorUeditor.uploadImg = function () {
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
  
  return DoctorUeditor;
})();

$(function() {
	exports.XPW.DoctorUeditor();
})
