exports = this;
exports.XPW = exports.EDIT || {};
exports.XPW.OfficeEdit = (function() {
  function OfficeEdit() {
    // 初始化页面处理。
	OfficeEdit.firstOfficeLoad();
	OfficeEdit.jumpDoctorDep()
	OfficeEdit.NewDoctor()

  }
  OfficeEdit.firstOfficeLoad = function () {
	$.ajax({
	  url: '/depts',
      type: 'GET'
    })
   .done(function(data) {
      var firstColumnTemplate = $('#officeTd').html();
      Mustache.parse(firstColumnTemplate);   
      var rendered = Mustache.render(firstColumnTemplate, {data: data});
      $('#officeTable').html(rendered);
      $('.ui-loading').hide();
   })
  }

  OfficeEdit.jumpDoctorDep = function () {
    $('#officeTable').on ('click', '.post-btn-edit', function() {
      var officeId = $(this).data('office-id');
      location.href = '/static/office_ueditor.html?deptId=' + officeId;
    }) 
  }
  
  
  OfficeEdit.NewDoctor = function () {
	  $('#tools').on ('click', '#columnNew', function() {
	      location.href = '/static/office_ueditor.html';
	  })
  }
  
  return OfficeEdit;
})();

$(function() {
	exports.XPW.OfficeEdit();
})
