$(function() {
	var ctrl = {
		columnId : 0,
		pageNo : 1,
		pageTotal : 0,
		noNextPage : false,
		pinnedNum : 0,	
		getUsers : function(pageNo, pageSize) {
			$('.ui-loading').show();
			var loading = true;
			$.get('/users', {
				pageNo : pageNo,
				pageSize : pageSize
			}, function(data) {
				if (data.length < ctrl.pageSize) {
					ctrl.noNextPage = true;
				}
				$.each(data, function(name, val) {
					if(val.userType == 1) {
						val.userType = '超级管理员';
					}
					if(val.userType == 2) {
						val.userType = '普通管理员';
					}
					
				})
				var template = $('#J_articles_tmpl').html();
				Mustache.parse(template);
				var rendered = Mustache.render(template, {
					result : data
				});
				if (data.length === 0) {
					$('.ui-nodata').show();
					$("#J_articles").html('');
				} else {
					$('.ui-nodata').hide();
					$("#J_articles").html(rendered);
				}
				$('.ui-loading').hide();
			});
		},
		publish : function(videoId) {
			var params = {
				isPublished : true
			};
			// 编辑模式
			var url = '/videos/' + videoId;
			$.post(url, params, function(res) {
				swal("发布成功！");
				ctrl.getVideos(ctrl.pageNo, 10);
			});
		},
		previous : function() {
			if (ctrl.pageNo > 1) {
				ctrl.pageNo--;
				ctrl.getArticles(ctrl.pageNo, 10, ctrl.columnId);
			}
		},
		next : function() {
			if (!ctrl.noNextPage) {
				ctrl.pageNo++;
				ctrl.getArticles(ctrl.pageNo, 10, ctrl.columnId);
			}
		},
		delete : function() {
            // 编辑模式
            var url = '/users/' + id;
            $.ajax({
                url: url,
                type: 'DELETE',
                success: function(result) {
                        swal({
                        title : "操作成功",
                        type : "success",
                        confirmButtonColor : "#DD6B55",
                        confirmButtonText : "确定！",
                        closeOnConfirm : false
                    }, function() {
                        location.reload()
                    });
                },
                error: function(request,msg,error) {
                        swal({
                        title : "操作失败",
                        type : "success",
                        confirmButtonColor : "#DD6B55",
                        confirmButtonText : "确定！",
                        closeOnConfirm : false
                    }, function() {
                        location.reload()
                    });
                }
            });
        },
		init : function() {
			ctrl.getUsers(ctrl.pageNo, 10, ctrl.columnId);
			$('.ui-nodata').hide();
		}
	}
	ctrl.init();

	// 上一页
	$('.previous').on('click', ctrl.previous);

	// 下一页
	$('.next').on('click', ctrl.next);

	// 编辑
	$('#J_articles').on('click','.edit-btn', function() {
		var userId = $(this).data('id');
		location.href = '/static/user_editor.html?userId=' + userId;
	});

	// 新建
	$('#J_create_btn').on('click', function() {
		location.href = '/static/user_editor.html';
	})
	
	// 删除 TODO
    
})