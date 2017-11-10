// 轮播
var mySwiper = new Swiper ('#bannerSwiper', {
  loop: true,
  autoplay : 5000,
  freeMode : true,
  paginationClickable :true,
  pagination: '.swiper-pagination'
})

var mySwiper2 = new Swiper ('#noticeSwiper', {
  direction: 'vertical',
  loop: true,
  autoplay : 2000,
  effect : 'fade',
  paginationClickable :true,
  pagination: '.swiper-pagination'
})

// 导航
$(function(){
	$(".xm-navbar-nav .xm-navbar-item").hover(function(){
	  	$t=$(this);
	  	$obj=$t.find(".navboxBase");
	  	$obj.css({"left":-$t.offset().left+"px","width":$("body").width()});
	  	$obj.show();
	  },function(){
	    $(this).find('.navboxBase').hide();
	  });

  // 导航悬浮
  var $xmNav = $('.xm-nav');

  var fixTop = $xmNav.offset().top;
  var fixHeight = $xmNav.height();

  $(window).scroll(function(event) {
  	 var docTop = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
  	 if (fixTop < docTop) {
      $xmNav.addClass('xm-nav-fixed');
    } else {
      $xmNav.removeClass('xm-nav-fixed');
    }
  });
  
 // 头部二维码显示 
  $('#wechatCode').hover(function(){
	  $(this).find('.wechat-code').show();
  },function(){
	  $(this).find('.wechat-code').hide();
  })
  $('#weboCode').hover(function(){
      $(this).find('.webo-code').show();
  },function(){
      $(this).find('.webo-code').hide();
  })
  
//  头部搜索框 
  $('#searchType').click(function(){
	  $('#searchTypeList').show();
  })
  $('#searchTypeList li').click(function(){
	  $('#searchType').text($(this).text());
	  $('#searchTypeList').hide();
	  $('#searchTypeInput').val($(this).data('id'));
  })
  
  //  搜索页面逻辑处理
 function getUrlParam (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURIComponent(r[2]); return null; //返回参数值
  }
  
  if (window.location.pathname == '/search') {
	  var type = getUrlParam('type');
	  var val = getUrlParam('keywords');
	  $('#searchValInput').val(val);
	  $('#ASearchAll').attr('href', 'search?type=1&keywords=' + val);
	  $('#ASearchDoctor').attr('href', 'search?type=2&keywords=' + val);
	  $('#ASearchNews').attr('href', 'search?type=3&keywords=' + val);
	  $('#ASearchOffice').attr('href', 'search?type=4&keywords=' + val);
	  
	  if(type == 1) {
		  $('#ASearchAll').addClass('active').siblings().removeClass('active');
	  }
	  if(type == 2) {
		  $('#ASearchDoctor').addClass('active').siblings().removeClass('active');
	  }
	  if(type == 3) {
		  $('#ASearchNews').addClass('active').siblings().removeClass('active');
	  }
	  if(type == 4) {
		  $('#ASearchOffice').addClass('active').siblings().removeClass('active');
	  }
  }
  
  
  
  
  // 专家轮播
  $('.officesBase').each(function(idx, e){
    var swiper = new Swiper($(this).find('.contentsDoctor'), {
      slidesPerView: 4,
      paginationClickable: true,
      spaceBetween: 0,
      freeMode: true,
      prevButton:$(this).find('.Aprev'),
      nextButton:$(this).find('.Anext'),
    });
  })


  $('.contentsDoctor li').hover(function(){
	var $this = $(this);
	$this.find('.doctorDialog').show();
    var nextIndex = $this.siblings('.swiper-slide-next').index();
    var currentIndex = $this.index();
    if (nextIndex !== -1 && currentIndex === (nextIndex + 2)) {
    		setTimeout(function(){
    			$this.parents('.contents').siblings('.toolsBase').find('.Anext').trigger('click');
    		}, 800)
    }
  },function(){
    $(this).find('.doctorDialog').hide();
  });
  
//导师团队轮播
    var swiper = new Swiper('#teacherTeam', {
      slidesPerView: 4,
      paginationClickable: true,
      spaceBetween: 0,
      freeMode: true,
      prevButton: '.prev',
      nextButton: '.next',
    });


  $('#teacherTeam li').hover(function(){
	var $this = $(this);
    var nextIndex = $this.siblings('.swiper-slide-next').index();
    var currentIndex = $this.index();
    if (nextIndex !== -1 && currentIndex === (nextIndex + 2)) {
    		setTimeout(function(){
    			$this.parents('.teacher-team-wrapper').find('.next').trigger('click');
    		}, 800)
    }
  });
  
  // 科室轮播
  // var swiper = new Swiper('#officeSwiper', {
  //   slidesPerView: 4,
  //   paginationClickable: true,
  //   spaceBetween: 0,
  //   freeMode: true,
  //   prevButton:'.officesBase .Aprev',
  //   nextButton:'.officesBase .Anext',
  // });

  // 胸痛中心
  $(".quickMenus li.first").hover(function(){
		$t=$(this);
		$obj=$t.find(".QMSub");
		if($obj.length>0){
			$obj.css("top",-(12+$t.index()*61)+"px");
			$obj.show();
		}
	},function(){
		$t=$(this);
		$obj=$t.find(".QMSub");
		if($obj.length>0){
			$obj.hide();
		}
	});
  
  $(".QMSubMenus li.second").hover(function(){
		$t=$(this);
		$obj=$t.find(".third-menus");
		if($obj.length>0){
			$obj.show();
		}
	},function(){
		$t=$(this);
		$obj=$t.find(".third-menus");
		if($obj.length>0){
			$obj.hide();
		}
	});
  
  var mySwiper = new Swiper ('#xtSwiper', {
    loop: true,
    paginationClickable :true,
    pagination: '.swiper-pagination'
  })

  //  详情页字体控制
  $("#EMChange a").click(function(){
  	$t=$(this);
  	if($t.data("type")=="0"){
  		$("#EMChange img").stop().animate({"left":"-5px"},288);
  		$("#FontState").text("14px");
  		$("#DIVContent").find('*').css({"font-size":"14px"});
  	}else if($t.data("type")=="1"){
  		$("#EMChange img").stop().animate({"left":"40px"},288);
  		$("#FontState").text("16px");
  		$("#DIVContent").find('*').css({"font-size":"16px"});
  	}else{
  		$("#EMChange img").stop().animate({"left":"86px"},288);
  		$("#FontState").text("18px");
  		$("#DIVContent").find('*').css({"font-size":"18px"});
  	}
  });
  
  
  // 电子院报
  var year = "";
  var time = "";
  $("#ddlYears2").change(function(){
	  year = $(this).val();
  });
  $("#ddlTimes2").change(function(){
	  times = $(this).val();
  });
  $("#GoToNewspaper2").on("click",function(){
	  year = $("#ddlYears2").val();
	  times = $("#ddlTimes2").val();
	  var str = "?page=1&year=" + year + "&times=" + times;
	  location.href = str;
  });

  $('#ddlYears2').change(function(){
    var selectVal = $(this).val();
    if (selectVal !== 0) {
      ajaxTimes(selectVal);
    }else {
      $('#ddlTimes2').html('');
      var html;
      $('#ddlTimes2').html(html);
    }
  })

  function ajaxTimes(val) {
    $.ajax({
      url: 'getTimes',
      type: 'GET',
      dataType: 'json',
      data: {year: val},
    })
    .done(function(data) {
      $('#ddlTimes2').html('');
      var html;
      for (var i = 0; i< data.length; i++) {
        html += '<option value = "' + data[i] + '">' + data[i] + '期</option>'
      }
      $('#ddlTimes2').html(html);
    })
  }
//  前台视频播放
  var videoUrl = $('#H2Title').data('src');
  if (videoUrl) {
	  $("#jquery_jplayer_1").jPlayer({
			ready: function () {
				$(this).jPlayer("setMedia", {
					title: "Big Buck Bunny",
					m4v: videoUrl,
					poster: "http://www.jplayer.org/video/poster/Big_Buck_Bunny_Trailer_480x270.png"
				});
			},
			swfPath: "./third_party/jquery.jplayer.swf",
			supplied: "m4v",
			size: {
				width: "100%",
				height: "460px",
				cssClass: "jp-video-360p"
			},
			useStateClassSkin: true,
			autoBlur: false,
			smoothPlayBar: true,
			keyEnabled: true,
			remainingDuration: true,
			toggleDuration: true
		});
  }
})
