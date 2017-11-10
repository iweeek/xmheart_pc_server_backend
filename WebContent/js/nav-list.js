
exports = this;
exports.XPW = exports.EDIT || {};
exports.XPW.NavEdit = (function() {
  function NavEdit() {
    // 初始化页面处理。
    NavEdit.firstNavLoad();
    NavEdit.firstSelectHandle();
    NavEdit.postDialogHandle();
    NavEdit.select2Handle()
    NavEdit.bindNavNews()
    NavEdit.init();
  }
  
  NavEdit.getUrlParam = function (name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
      var r = window.location.search.substr(1).match(reg);  //匹配目标参数
      if (r != null) return unescape(r[2]); return null; //返回参数值
  }
  
  NavEdit.init = function() {
	  var col = NavEdit.getUrlParam('col');
	  NavEdit.col = col;
  		if (col) {
//  			NavEdit.newCol = col.split(',');
//  			if (ctrl.newCol.length == 1) {
//  				ctrl.getColumns(0, '#J_select_first', ctrl.newCol[0]);
  				NavEdit.firstColumnData(col, col, '#typeSelectInput');
//  			}
//  			if (ctrl.newCol.length == 2) {
//  				ctrl.getColumns(0, '#J_select_first', ctrl.newCol[0]);
//  				$('.select-title-second').show();
//  				ctrl.getColumns(ctrl.newCol[0], '#J_select_second', ctrl.newCol[1]);
//  				ctrl.getArticles(1, 10, ctrl.newCol[1]);
//  				return false;
//  			}
  		}
  }
  
  NavEdit.getCookieValue = function(name) {
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
  
  NavEdit.firstNavLoad = function () {
		$.ajax({
			  url: '/columns',
		      type: 'GET',
		      dataType: 'json',
		      data: {parentColumnId: 0},
//		      headers: {
//		          'Authorization': NavEdit.getCookieValue('xmheart_token')
//		      }
//				beforeSend:function(xhr){
//		            token = NavEdit.getCookieValue('xmheart_token');
//		            xhr.setRequestHeader("Authorization", token);  
//		        }
		    })
		   .done(function(data) {
		     var firstColumnTemplate = $('#firstColumnTemplate').html();
		     Mustache.parse(firstColumnTemplate);   // optional, speeds up future uses
		     var rendered = Mustache.render(firstColumnTemplate, {
		    	 	data:data,
		    	 });
		     $('#typeSelectInput').html(rendered);
		   });
  }
  
  NavEdit.firstSelectHandle = function () {
    $('#columnSearch').click(function() {
    		var firstId = $('#typeSelectInput').val();
    		NavEdit.firstColumnData(firstId);
    		NavEdit.col = firstId;
    })
  }

  NavEdit.firstColumnData = function (val, selectedVal, htmlId) {
    $.ajax({
      url: '/navs',
      type: 'GET',
      dataType: 'json',
      data: {columnId: val},
//		beforeSend:function(xhr){
//	        token = NavEdit.getCookieValue('xmheart_token');
//	        xhr.setRequestHeader("Authorization", token);  
//	    }
    })
    .done(function(data) {
    		if (data.length > 0) {
    			$.each(data, function(name, val){
    	    	   	  val.publishTime = NavEdit._dateFilter(val.publishTime)
    	    	    })
    	    		var secondColumnTemplate = $('#secondColumnTd').html();
    	    	    Mustache.parse(secondColumnTemplate);   // optional, speeds up future uses
    	    	    var rendered = Mustache.render(secondColumnTemplate, {
    	    	    		data : data,
    	    	    		isDisplayed : function() {  
    	    	    		    // 去除电子院报和影像厦心
                    if (!(this.childColumnId == 23 || this.childColumnId == 22)) {
                        return true;  
                    }  
                    return false;  
    	            }
    	    	    	});
    	    	    $('#secondTable').html(rendered);
    		} else {
    			swal({ 
    				  title: "当前栏目下没有导航信息", 
    				  timer: 1500, 
    				  showConfirmButton: false 
    			});
    		}
    	    // 自动显示select option当前选中的内容
        if (selectedVal) {
            $(htmlId + ' ' + 'option[value='+selectedVal+']').attr('selected', 'selected');
        }  
    })
  }

  NavEdit._dateFilter = function (date, formatString) {
  	if (!date) return '';
  	var date = new Date(date);

  	formatString = formatString || 'yyyy-MM-dd';

  	var dateMap = {
  		year: date.getFullYear(),
  		month: date.getMonth() + 1,
  		day: date.getDate(),
  		hour: date.getHours(),
  		minutes: date.getMinutes(),
  		seconds: date.getSeconds()
  	};

  	for (var key in dateMap) {
  		var value = dateMap[key];
  		value = value < 10 ? '0' + value : value;
  		dateMap[key] = value.toString();
  	}

  	var year = dateMap.year,
  	    month = dateMap.month,
  	    day = dateMap.day,
  	    hour = dateMap.hour,
  	    minutes = dateMap.minutes,
  	    seconds = dateMap.seconds;

  	var formatDate = formatString.replace(/y+/, function ($0) {
  		return year.substring(year.length, -$0.length);
  	}).replace(/M+/, function () {
  		return month;
  	}).replace(/d+/, function () {
  		return day;
  	}).replace(/H+/, function () {
  		return hour;
  	}).replace(/h+/, function () {
  		return hour % 12 === 0 ? 12 : hour % 12;
  	}).replace(/m+/, function () {
  		return minutes;
  	}).replace(/s+/, function () {
  		return seconds;
  	});
  	return formatDate;
  };

  NavEdit.postDialogHandle = function () {
//    $('#secondTable').on('click', '.post-btn-edit', function() {
//    	  $('#secondColumnId').val($(this).data('column-name'));
//    	  $('#secondColumnId').data('nav-id', $(this).data('nav-id'))
//    	  $('#secondColumnId').data('column-id', $(this).data('column-id'))
//    	  $('#secondColumnId').data('column-name', $(this).data('column-name'))
//    	  $('#postModal').modal('show');
//    })
    $('#secondTable').on('click', '.post-btn-edit', function() {
//    		$('#secondColumnId').val($(this).data('column-name'));
//    	    console.log($(this).data('title'));
//    	    $('#secondColumnId').data('title', $(this).data('title'))
//  	    $('#secondColumnId').data('nav-id', $(this).data('nav-id'))
//  	    $('#secondColumnId').data('column-id', $(this).data('column-id'))
//  	    $('#secondColumnId').data('column-name', $(this).data('column-name'))
    		var navId =  $(this).data('nav-id');
		location.href = '/static/nav_editor.html?navId=' + navId + '&col=' + NavEdit.col;
	});
  }
  
  NavEdit.select2Handle = function () {
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
		        			  columnName: $('#secondColumnId').data('column-name'),
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
  
  NavEdit.bindNavNews = function () {
	  $('#postModal').on('click', '#bindNavTitle', function() {
		  var id = $('#secondColumnId').data('nav-id')
		  var postId = $('#postSelect').val();
		    $.ajax({
		        url: '/navs/' + id,
		        type: 'POST',
		        data: {id: id, articleId: postId},
//				beforeSend:function(xhr){
//		            token = NavEdit.getCookieValue('xmheart_token');
//		            xhr.setRequestHeader("Authorization", token);  
//		        }
		    })
		   .done(function() {
			   $('#postModal').modal('hide');
			   window.location.reload()
		    })
	  })
  }

  return NavEdit;
})();

$(function(){
	exports.XPW.NavEdit();
});
