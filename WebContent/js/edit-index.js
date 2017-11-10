exports = this;
exports.XPW = exports.EDIT || {};
exports.XPW.IndexEdit = (function() {
	function IndexEdit() {
		// 初始化页面处理。
		IndexEdit.init();
		IndexEdit.addSlideImg();
		IndexEdit.getData();
		IndexEdit.pageId = 0;
		IndexEdit.postData();
		IndexEdit.limitFont();
	}

	IndexEdit.init = function() {
		IndexEdit.postDialogHandle();
		IndexEdit.select2Handle();
		IndexEdit.bindSearchArticle();
	}

	IndexEdit.whichPostDialog = -1;
	IndexEdit.postDialogHandle = function () {
		$('.post-btn-edit1').on('click', function() {
	        $('#postModal').modal('show');
	        IndexEdit.whichPostDialog = 0;
	    })
	    $('.post-btn-edit2').on('click', function() {
	        $('#postModal').modal('show');
	        IndexEdit.whichPostDialog = 1;
	    })
	    $('.post-btn-edit3').on('click', function() {
	        $('#postModal').modal('show');
	        IndexEdit.whichPostDialog = 2;
	    })
	}
  
	IndexEdit.select2Handle = function () {
	  $('#postModal').on('shown.bs.modal', function(e) {
		  $('#postSelect').select2({
		      placeholder: '请输入要查询的文章标题...',
		      allowClear: true,
		      minimumInputLength: 1,
		      minimumResultsForSearch: Infinity,
		      ajax: {
		        url: '/articles/show',
		        dataType: 'json',
		        data: function (params) {
		        		var query = {
//		        			  columnName: $('#secondColumnId').data('column-name'),
	  		        		  keyword: params.term
	  		            }
	  		        return query;
		        },
		        processResults: function (data, params) {
		          var de;
		          return {
		            results: (function () {
		              var i, len, results;
		              results = [];
		              for (i = 0, len = data.length; i < len; i++) {
		                de = data[i];
		                results.push({
		                  id: de.id,
		                  text: de.title
		                });
		              }
		              return results;
		            })()
		          };
		        },
		        cache: true
		      },
		      language: 'zh-CN',
		    });
	  })
	  //	 解决 select2 在 bootstarp 弹窗中无法输入问题
	  $.fn.modal.Constructor.prototype.enforceFocus = function () { };
    }
  
	IndexEdit.bindSearchArticle = function () {
        $('#postModal').on('click', '#bindIndexTitle', function() {
            var articleId = $('#postSelect').val();
            $.get('/articles/' + articleId, function(data) {
	    		  $('.add-font-list').eq(IndexEdit.whichPostDialog).find('.link-tag').val(data.columnName);
	    		  $('.add-font-list').eq(IndexEdit.whichPostDialog).find('.link-title').val(data.title);
	    		  
	    		  var digest = $('.digest-wrapper textarea');
	    		  var brief = data.brief.substring(0, 40);
	    		  $('.add-font-list').eq(IndexEdit.whichPostDialog).find('textarea').val(brief);
	    		 
	    		  $('.add-font-list').eq(IndexEdit.whichPostDialog).find('.input-link').val(data.url);

               $('.ui-loading').hide();
              })
	         .done(function() {
	             $('#postModal').modal('hide');
	          })
        })
	}

    IndexEdit.addSlideImg = function () {
	  $('.add-list-main').on('click', '#addImgBtn', function (){
		  $(this).siblings('.upload-form').find('.add-img-file').trigger('click');
	  })
	  $('.add-list-main').on('change', '.add-img-file', function (){
		  $(this).siblings('.add-img-submit').trigger('click');
	  })
	  $('.js-upload-form').submit(function(){
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
	  $('.add-list-main').on('mouseover', '.add-image-url, .add-image-edit', function (){
		  $(this).siblings('.add-image-edit').show();
	  })
	  $('.add-list-main').on('mouseleave', '.add-image-url, .add-image-edit', function (){
		  $(this).siblings('.add-image-edit').hide();
	  })
	  $('.add-list-main').on('mouseover', '.add-image-edit', function (){
		  $(this).show();
	  })
	  $('.add-list-main').on('mouseleave', '.add-image-edit', function (){
		  $(this).hide();
	  })
	  $('.add-list-main').on('click', '.add-image-edit', function (){
		  $(this).siblings('.upload-form').find('.add-img-file').trigger('click');
	  })
    }

    IndexEdit.getData = function () {
	  $.get('/indexPage',function(data){
		  IndexEdit.pageId = data.id;
		  if(data.bannerImage1Url) {
			  $('.add-img-list').eq(0).find('.add-image-url img').attr('src', data.bannerImage1Url);
			  $('.add-img-list').eq(0).find('.add-image-button').hide();
			  $('.add-img-list').eq(0).find('.add-image-url').show();
		  }
		  if (data.bannerImage1ActionUrl) {
			  $('.add-img-list').eq(0).find('.add-img-link input').val(data.bannerImage1ActionUrl);
		  }
		  if(data.bannerImage2Url) {
			  $('.add-img-list').eq(1).find('.add-image-url img').attr('src', data.bannerImage2Url);
			  $('.add-img-list').eq(1).find('.add-image-button').hide();
			  $('.add-img-list').eq(1).find('.add-image-url').show();
		  }
		  if (data.bannerImage2ActionUrl) {
			  $('.add-img-list').eq(1).find('.add-img-link input').val(data.bannerImage2ActionUrl);
		  }
		  if(data.bannerImage3Url) {
			  $('.add-img-list').eq(2).find('.add-image-url img').attr('src', data.bannerImage3Url);
			  $('.add-img-list').eq(2).find('.add-image-button').hide();
			  $('.add-img-list').eq(2).find('.add-image-url').show();
		  }
		  if (data.bannerImage3ActionUrl) {
			  $('.add-img-list').eq(2).find('.add-img-link input').val(data.bannerImage3ActionUrl);
		  }
		  $('.add-font-list').eq(0).find('.link-tag').val(data.bannerArticle1Tag),
		  $('.add-font-list').eq(0).find('.link-title').val(data.bannerArticle1Title),
		  $('.add-font-list').eq(0).find('textarea').val(data.bannerArticle1Brief),
		  $('.add-font-list').eq(0).find('.word').text(data.bannerArticle1Brief.length),
		  $('.add-font-list').eq(0).find('.input-link').val(data.bannerArticle1Url),
		  $('.add-font-list').eq(1).find('.link-tag').val(data.bannerArticle2Tag),
		  $('.add-font-list').eq(1).find('.link-title').val(data.bannerArticle2Title),
		  $('.add-font-list').eq(1).find('textarea').val(data.bannerArticle2Brief),
		  $('.add-font-list').eq(1).find('.word').text(data.bannerArticle2Brief.length),
		  $('.add-font-list').eq(1).find('.input-link').val(data.bannerArticle2Url),
		  $('.add-font-list').eq(2).find('.link-tag').val(data.bannerArticle3Tag),
		  $('.add-font-list').eq(2).find('.link-title').val(data.bannerArticle3Title),
		  $('.add-font-list').eq(2).find('textarea').val(data.bannerArticle3Brief),
		  $('.add-font-list').eq(2).find('.word').text(data.bannerArticle3Brief.length),
		  $('.add-font-list').eq(2).find('.input-link').val(data.bannerArticle3Url)
	  })
    }
    
    IndexEdit.postData = function () {
	  $('#submit').on('click', function(){
		  var parms = {
		    bannerImage1Url: $('.add-img-list').eq(0).find('.add-image-url img').attr('src'),
			bannerImage1ActionUrl: $('.add-img-list').eq(0).find('.add-img-link input').val(),
			bannerImage2Url: $('.add-img-list').eq(1).find('.add-image-url img').attr('src'),
			bannerImage2ActionUrl: $('.add-img-list').eq(1).find('.add-img-link input').val(),
			bannerImage3Url: $('.add-img-list').eq(2).find('.add-image-url img').attr('src'),
			bannerImage3ActionUrl: $('.add-img-list').eq(2).find('.add-img-link input').val(),
			bannerArticle1Tag: $('.add-font-list').eq(0).find('.link-tag').val(),
			bannerArticle1Title: $('.add-font-list').eq(0).find('.link-title').val(),
			bannerArticle1Brief: $('.add-font-list').eq(0).find('textarea').val(),
			bannerArticle1Url: $('.add-font-list').eq(0).find('.input-link').val(),
			bannerArticle2Tag: $('.add-font-list').eq(1).find('.link-tag').val(),
			bannerArticle2Title: $('.add-font-list').eq(1).find('.link-title').val(),
			bannerArticle2Brief: $('.add-font-list').eq(1).find('textarea').val(),
			bannerArticle2Url: $('.add-font-list').eq(1).find('.input-link').val(),
			bannerArticle3Tag: $('.add-font-list').eq(2).find('.link-tag').val(),
			bannerArticle3Title: $('.add-font-list').eq(2).find('.link-title').val(),
			bannerArticle3Brief:$('.add-font-list').eq(2).find('textarea').val(),
			bannerArticle3Url: $('.add-font-list').eq(2).find('.input-link').val()
		  };
		  var url = '/indexPage/'+ IndexEdit.pageId;
		  $.ajax({
			  url: url,
		      type: 'POST',
		      dataType: 'json',
		      data: parms
		    })
		   .done(function(data) {
			   swal('更新成功');
		   })
	  });
    }

	IndexEdit.limitFont = function() {
		var numItem = $('.word');
		var digest = $('.digest-wrapper textarea');
		var max = $('.word').eq(0).text(), curLength;
		digest[0].setAttribute("maxlength", max);
		digest[1].setAttribute("maxlength", max);
		digest[2].setAttribute("maxlength", max);
		curLength = digest.val().length;
		numItem.text(curLength);
		digest.on('input propertychange', function() {
			$(this).siblings('.wordwrap').find('.word').text(
					$(this).val().length);
		});
	}

	return IndexEdit;
})();

$(function() {
	exports.XPW.IndexEdit();
})
