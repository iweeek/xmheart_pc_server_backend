$(function() {
	var ctrl = {
		columnId : 0,
		pageNo : 1,
		pageTotal : 0,
		noNextPage : false,
		pinnedNum : 0,	
		getRoles : function(pageNo, pageSize) {
			$('.ui-loading').show();
			var loading = true;
			$.get('/roles', {
				pageNo : pageNo,
				pageSize : pageSize
			}, function(data) {
				if (data.length < ctrl.pageSize) {
					ctrl.noNextPage = true;
				}
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
		deleteRoles : function(roleId) {
			var params = {
				id : roleId
			};
			// 编辑模式
			var url = '/deleteRoles/' + roleId;
			$.ajax({
				  url: url,
			      type: 'post',
			    	  data: {id : roleId}
			    })
			   .done(function(data) {
				   swal("删除成功！");
				   ctrl.getRoles(ctrl.pageNo, 10);
			   })
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
		init : function() {
			console.log(123);
			ctrl.getRoles(ctrl.pageNo, 10, ctrl.columnId);
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
		var roleId = $(this).data('id');
		console.log(roleId)
		location.href = '/static/role_editor.html?roleId=' + roleId;
	});
	
	// 删除
	$('#J_articles').on('click','.edit-delete', function() {
		var userId = $(this).data('id');
		ctrl.deleteRoles(userId);
	});
	
	// 新建
	$('#J_create_btn').on('click', function() {
		location.href = '/static/role_editor.html';
	})

})