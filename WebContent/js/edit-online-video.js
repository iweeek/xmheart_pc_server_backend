exports = this;
var videoUrl;
var videoId;
var title = $('#videoTitle');
var brief = $('#brief');
var isAdded = false;
var videoUrl;
exports.XPW = exports.EDIT || {};
exports.XPW.OnlineVideoEdit = (function() {
	OnlineVideoEdit.getUrlParam = function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg); // 匹配目标参数
			if (r != null)
				return unescape(r[2]);
			return null; // 返回参数值
			
	    }
	
    function OnlineVideoEdit() {
//	  OnlineVideoEdit.initVideo();
    	  var id = OnlineVideoEdit.getUrlParam("videoId");
//    	  console.log(id);
    	  if (!id) {
    		  $("#publish").show();
    		  $("#update").hide();
    	  } else {
    		  $("#publish").hide();
    		  $("#update").show();
    	  }
	  // 初始化页面处理。
    	  OnlineVideoEdit.init();
	  OnlineVideoEdit.addVideo();
	  OnlineVideoEdit.publish();
	  OnlineVideoEdit.update();
	  OnlineVideoEdit.cancel();
	  OnlineVideoEdit.uploadImg();
//	  xtIndexEdit.getData();
//	  xtIndexEdit.pageId = 0;
//	  xtIndexEdit.postData();
    }
  OnlineVideoEdit.initVideo = function (video, imgUrl) {
	  var videoSrc = video || '';
	  videoUrl = videoSrc;
//	  console.log(videoUrl);// TODO
	  $("#jquery_jplayer_1").jPlayer({
			ready: function () {
//				location.reload(true);
				$(this).jPlayer("setMedia", {
					title: "Big Buck Bunny",
					m4v: videoSrc,
					poster: imgUrl
				});
			},
			swfPath: "./third_party/jquery.jplayer.swf",
			supplied: "m4v",
			size: {
				width: "640px",
				height: "360px",
				cssClass: "jp-video-360p"
			},
			useStateClassSkin: true,
			autoBlur: false,
			smoothPlayBar: true,
			keyEnabled: true,
			remainingDuration: true,
			toggleDuration: true
		});
	  isAdded = true;
  }
  
  OnlineVideoEdit.addVideo = function () {
	  $('.add-list-main').on('click', '#addVideoBtn', function (){
		  $(this).siblings('.upload-form').find('.add-video-file').trigger('click');
	  })
	  $('.add-list-main').on('change', '.add-video-file', function (){
		  $(this).siblings('.add-video-submit').trigger('click');
	  })
	  $('.js-upload-form').submit(function(){
		  $this = $(this);
		  $this.ajaxSubmit({
			  success: function (responseText) {
				  var video = responseText;
				  videoUrl = video;
				  OnlineVideoEdit.initVideo(video);
//				  "http://www.jplayer.org/video/poster/Big_Buck_Bunny_Trailer_480x270.png"
//				  $this.siblings('.add-image-url').find('.upload-img').attr('src', img);
				  $this.siblings('.add-image-button').hide();
				  $this.siblings('.add-video-url').show();
			  }
		  });
		  return false;
	  })
	  $('.add-list-main').on('mouseover', '.add-video-url, #add-video-edit', function (){
		  $(this).siblings('#add-video-edit').show();
	  })
	  $('.add-list-main').on('mouseleave', '.add-video-url, #add-video-edit', function (){
		  $(this).siblings('#add-video-edit').hide();
	  })
	  $('.add-list-main').on('mouseover', '#add-video-edit', function (){
		  $(this).show();
	  })
	  $('.add-list-main').on('mouseleave', '#add-video-edit', function (){
		  $(this).hide();
	  })
	  $('.add-list-main').on('click', '#add-video-edit', function (){
		  $(this).siblings('.upload-form').find('.add-video-file').trigger('click');
	  })
  }
  
  OnlineVideoEdit.valid = function (params, type) {

	if (!params.title) {
		sweetAlert("信息不完整", "请填写标题", "error");
		return false;
	}

	if (!params.brief) {
		sweetAlert("信息不完整", "请填写简介", "error");
		return false;
	}
	
	if (!isAdded) {
		sweetAlert("信息不完整", "请选择需要上传的视频", "error");
		return false;
	}
	return true;
  }
  
    OnlineVideoEdit.publish = function () {
	    $('#publish').on('click', function(){
		    var $this = $(this);
		    	var imgUrl = $('.upload-img').attr('src');
//		    console.log(title.val());
//		    console.log(brief.val());
//		    console.log(videoUrl);
			var params = {
				title : title.val(),
				brief : brief.val(),
				isPublished : true,
				imgUrl : imgUrl,
				videoUrl : videoUrl
			};
	
			if (!OnlineVideoEdit.valid(params, 'publish')) {
				return;
			}
			$this.attr('disabled', 'disabled');
			var url = '/onlineVideos';
			$.post(url, params, function(res) {
				$this.removeAttr('disabled');
				
				swal({
				    title : "上传成功",
                    type : "success",
                    confirmButtonColor : "#8cd4f5",
                    confirmButtonText : "确定",
                    closeOnConfirm : true
                }, function() {
//                    location.reload(true);
                });
			}).error(function() {
				$this.removeAttr('disabled');
				sweetAlert("哎呀", "服务器开小差了~请稍后再试", "error");
			});

			return;
  	    });
    }
    
    OnlineVideoEdit.update = function () {
	    $('#update').on('click', function(){
		    var $this = $(this);
		    var imgUrl = $('.upload-img').attr('src');
			var params = {
				title : title.val(),
				brief : brief.val(),
				isPublished : true,
				imgUrl : imgUrl,
				videoUrl : videoUrl
			};
	
			if (!OnlineVideoEdit.valid(params, 'update')) {
				return;
			}
			$this.attr('disabled', 'disabled');
			var url = '/onlineVideos/' + videoId;
			$.post(url, params, function(res) {
				$this.removeAttr('disabled');
				swal({
					title : "更新成功",
					type : "success",
					confirmButtonColor : "#8cd4f5",
					confirmButtonText : "确定",
					closeOnConfirm : true
				}, function() {
//					window.history.go(-1);
					location.reload(true);
				});
			});

			return;
  	    });
    }
    
    OnlineVideoEdit.cancel = function () {
	    $('#cancel').on('click', function(){
	    		window.history.go(-1);
  	    });
    }
  
    OnlineVideoEdit.getVideos = function(videoId) {
    		var url = '/onlineVideos/' + videoId;
        $.get(url, function (res) {
            $('#videoTitle').val(res.title);
            $('#brief').val(res.brief);
//            $('#upload-img').attr('src', res.imageUrl);
//            console.log(res.videoUrl);
            videoUrl = res.videoUrl;
            OnlineVideoEdit.initVideo(res.videoUrl, res.imgUrl);
            $('#upload-img').attr('src', res.imgUrl);
            $('#add-image-url').show();
            $('#add-video-url').show();
            $('#addImgBtn').hide();
            $('#addVideoBtn').hide();
        });
    }
		
    OnlineVideoEdit.init = function() {
    		videoId = OnlineVideoEdit.getUrlParam('videoId');
        if (videoId) {
            OnlineVideoEdit.getVideos(videoId);
        }
        $('.add-video-file').hide();
        $('.add-video-submit').hide();
    }
  
    OnlineVideoEdit.uploadImg = function () {
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
					OnlineVideoEdit.initVideo(videoUrl, img);
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
    
  return OnlineVideoEdit;
})();

$(function() {
	exports.XPW.OnlineVideoEdit();
})