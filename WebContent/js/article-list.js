$(function() {
    var ctrl = {
        columnId : 0,
        pageNo : 1,
        pageTotal : 0,
        noNextPage : false,
        pinnedNum : 0,
        col: [],
        newCol: [],
      //获取url中的参数
        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        },
        postDialogHandle : function () {
            $('.post-btn-edit').on('click', function() {
              $('#postModal').modal('show');
            })
        },
        bindSearchArticle : function () {
            $('#postModal').on('click', '#bindNavTitle', function() {
                var articleId = $('#postSelect').val();
                $.get('/articles/' + articleId, function(data) {
                     console.log(data);
                     data.publishTime = ctrl.dateFilter(data.publishTime)
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
               })
                 .done(function() {
                     $('#postModal').modal('hide');
                  })
            })
        },
        select2Handle : function () {
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
            //     解决 select2 在 bootstarp 弹窗中无法输入问题
            $.fn.modal.Constructor.prototype.enforceFocus = function () { };
        },
        getColumns: function (parentColumnId, htmlId, selectedVal) {
            $.get('/columns', {
                parentColumnId: parentColumnId
            }, function (data) {
                var optionString = '';
                for (var i in data) {
                    var jsonObj = data[i];
                    if (jsonObj.columnName == "电子院报" || jsonObj.columnName == "影像厦心") {
                    		continue;
                    }
                    	optionString += "<option value=\"" + jsonObj.id + "\" >" + jsonObj.columnName + "</option>";
                    $(htmlId).html("<option value='请选择'>请选择</option> " + optionString);
                }
                if (data.length > 0) {
                		$(htmlId).parent().show();
                }
                // 自动显示select option当前选中的内容
                if (selectedVal) {
                		$(htmlId + ' ' + 'option[value='+selectedVal+']').attr('selected', 'selected');
                }           
            });
        },
        dateFilter: function(date, formatString) {
            if (!date) return '';
            var date = new Date(date);

            formatString = formatString || 'yyyy-MM-dd';

            var dateMap = {
                year : date.getFullYear(),
                month : date.getMonth() + 1,
                day : date.getDate(),
                hour : date.getHours(),
                minutes : date.getMinutes(),
                seconds : date.getSeconds()
            };

            for ( var key in dateMap) {
                var value = dateMap[key];
                value = value < 10 ? '0' + value : value;
                dateMap[key] = value.toString();
            }

            var year = dateMap.year, month = dateMap.month, day = dateMap.day, hour = dateMap.hour, minutes = dateMap.minutes, seconds = dateMap.seconds;

            var formatDate = formatString.replace(/y+/, function($0) {
                return year.substring(year.length, -$0.length);
            }).replace(/M+/, function() {
                return month;
            }).replace(/d+/, function() {
                return day;
            }).replace(/H+/, function() {
                return hour;
            }).replace(/h+/, function() {
                return hour % 12 === 0 ? 12 : hour % 12;
            }).replace(/m+/, function() {
                return minutes;
            }).replace(/s+/, function() {
                return seconds;
            });
            return formatDate;
        },
        getArticles : function(pageNo, pageSize, columnId) {
            $('.ui-loading').show();
            var loading = true;
            $.get('/articles', {
                pageNo : pageNo,
                pageSize : pageSize,
                columnId : columnId
            }, function(data) {
                if (data.length < ctrl.pageSize) {
                    ctrl.noNextPage = true;
                }
                $.each(data, function(name, val) {
                    val.publishTime = ctrl.dateFilter(val.publishTime)
                    //TODO这里有问题
//                    if (val.isPinned) {
//                        ctrl.pinnedNum++
//                    }
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
        publish : function(articleId) {
            var params = {
                isPublished : true
            };
            // 编辑模式
            var url = '/articles/' + articleId;
            $.post(url, params, function(res) {
                swal("发布成功！");
                ctrl.getArticles(ctrl.pageNo, 10, ctrl.columnId);
            });
        },
        offline : function(articleId) {
            var params = {
                isPublished : false
            };
            // 编辑模式
            var url = '/articles/' + articleId;
            $.post(url, params, function(res) {
                swal("下线成功！");
                ctrl.getArticles(ctrl.pageNo, 10, ctrl.columnId);
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
        pinned : function(articleId, type) {
            var url = '/articles/' + articleId;
            $.get(url, function(res){
                if (!res.imgUrl) {
                    swal('置顶文章必须带有图片')
                } else {
                    var params = {}
                    if (type === 'on') {
                        params.isPinned = true;
                    } else {
                        params.isPinned = false;
                    }
                    $.post(url, params, function(res) {
                        swal({
                            title : "操作成功",
                            type : "success",
                            showCancelButton : false,
                            confirmButtonColor : "#DD6B55",
                            confirmButtonText : "确定！",
                            closeOnConfirm : false
                        }, function() {
                        		var url = 'article-list.html?col=' + ctrl.col;
                        		location.href = url;
                        });
                    });
                }
            })
        },
        handleUpDown : function(articleId1, articleId2) {
            var url = '/articles/swapPinOrder';
            var params = {
                articleId1 : articleId1,
                articleId2 : articleId2
            };
            $.post(url, params, function(res) {
                swal({
                    title : "操作成功",
                    type : "success",
                    showCancelButton : false,
                    confirmButtonColor : "#DD6B55",
                    confirmButtonText : "确定！",
                    closeOnConfirm : false
                }, function() {
                    location.reload()
                });
            })
            .error(function(jqXHR, textStatus, errorThrown){
                if (jqXHR.status == 403) {
                    swal({
                        title : "不在同一个栏目下",
                        type : "error",
                        showCancelButton : false,
                        confirmButtonColor : "#DD6B55",
                        confirmButtonText : "确定！",
                        closeOnConfirm : false
                    });
                }
            });
        },
        delete : function(articleId) {
            var url = '/articles/' + articleId;
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
        init: function() {
        		$('.ui-nodata').hide();
            ctrl.postDialogHandle();
            ctrl.select2Handle();
            ctrl.bindSearchArticle();
            var col = ctrl.getUrlParam('col');
            ctrl.col = col;
	    		if (col) {
	    			ctrl.newCol = col.split(',');
	    			if (ctrl.newCol.length == 1) {
	    				ctrl.getColumns(0, '#J_select_first', ctrl.newCol[0]);
	    			}
	    			if (ctrl.newCol.length == 2) {
	    				ctrl.getColumns(0, '#J_select_first', ctrl.newCol[0]);
	    				$('.select-title-second').show();
	    				ctrl.getColumns(ctrl.newCol[0], '#J_select_second', ctrl.newCol[1]);
	    				ctrl.getArticles(1, 10, ctrl.newCol[1]);
	    				return false;
	    			}
	    		}
	    		ctrl.getColumns(0, '#J_select_first');
            ctrl.getArticles(ctrl.pageNo, 10, ctrl.columnId);
        }
    }
    
    ctrl.init();

    // 二级分类的出现
    $('#J_select_first').change(function() {
        var firstId = $(this).val();
        var currentText = $( "#J_select_first option:selected" ).text();
        $('.select-title-second').hide();
        $('.select-title-third').hide();
        $('.select-title-fourth').hide();
        if (firstId !== 0 && currentText !== '请选择') {
            ctrl.columnId = firstId;
            $('#J_filter_btn').data('first', firstId);
            ctrl.getColumns(firstId, '#J_select_second');
        }
        if (currentText == '首页') {
            $('.select-title-second').hide();
        }
    });

    // 获取三级分类的id
    $('#J_select_second').change(function() {
        var secondId = $(this).val();
        var currentText = $( "#J_select_second option:selected" ).text();
        $('.select-title-third').hide();
        $('.select-title-fourth').hide();
        if (secondId !== 0 && currentText !== '请选择') {
            ctrl.columnId = $(this).val();
            $('#J_filter_btn').data('third', secondId);
            ctrl.getColumns(secondId, '#J_select_third');
        };
        
    });
    
 // 获取四级分类的id
    $('#J_select_third').change(function() {
        var thirdId = $(this).val();
        var currentText = $( "#J_select_third option:selected" ).text();
        $('.select-title-fourth').hide();
        if (thirdId !== 0 && currentText !== '请选择') {
            ctrl.columnId = $(this).val();
            $('#J_filter_btn').data('third', thirdId);
            ctrl.getColumns(thirdId, '#J_select_fourth');
        };
    });
    
    // 获取四级分类的id
    $('#J_select_fourth').change(function() {
        var fourthId = $(this).val();
        var currentText = $( "#J_select_fourth option:selected" ).text();
        if (fourthId !== 0 && currentText !== '请选择') {
            ctrl.columnId = $(this).val();
            $('#J_filter_btn').data('fourth', fourthId);
        };
    });
    

    // 筛选
    $('#J_filter_btn').on('click', function() {
    		var $this = $(this);
    		ctrl.col = [];
    		if ($this.data('first')) {
    			ctrl.col.push($this.data('first'));
    		}
    		if ($this.data('second')) {
    			ctrl.col.push($this.data('second'));
    		}
    		if ($this.data('third')) {
    			ctrl.col.push($this.data('third'));
    		}
    		if ($this.data('fourth')) {
    			ctrl.col.push($this.data('fourth'));
    		}
        ctrl.getArticles(1, 10, ctrl.columnId);
    });

    // 上一页
    $('.previous').on('click', ctrl.previous);

    // 下一页
    $('.next').on('click', ctrl.next);

    // 编辑
    $('#J_articles').on('click','.edit-btn',function() {
        var articleId = $(this).data('id');
        var url = '/static/ueditor.html?articleId=' + articleId + '&col=' + ctrl.col;
        location.href = url;
//        window.open(url, "new window");
    });

    // 发布
    $('#J_articles').on('click', '.publish-btn', function() {
        var articleId = $(this).data('id');
        ctrl.publish(articleId);
    });

    // 下线
    $('#J_articles').on('click', '.offline-btn', function() {
        var articleId = $(this).data('id');
        ctrl.offline(articleId);
    })

    // 新建
    $('#J_create_btn').on('click', function() {
        var url = '/static/ueditor.html?col=' + ctrl.col;
        location.href = url;
//        window.open(url, "new window");
    })

    // 置顶
    $('#J_articles').on('click', '.pinned-btn', function() {
        var articleId = $(this).data('id');
        ctrl.pinned(articleId, 'on');
    })
    // 取消置顶
    $('#J_articles').on('click', '.cancel-pinned-btn', function() {
        var articleId = $(this).data('id');
        ctrl.pinned(articleId, 'off');
    })
    // 上移
    $('#J_articles').on('click', '.up-btn', function() {
        var articleId2 = $(this).data('id');
        var index = $(this).parents('tr').index();
        if(index === 0) {
            swal('已经是最顶部了!');
            return false;
        }
        var articleId1 = $('#J_articles tr').eq(index-1).find('.up-btn').data('id');
        ctrl.handleUpDown(articleId1, articleId2);
    })
    // 下移
    $('#J_articles').on('click', '.down-btn', function() {
        var articleId1 = $(this).data('id');
        var index = $(this).parents('tr').index();
        if($('#J_articles tr').eq(index+1).find('.down-btn').length === 0) {
            swal('已经是最底部了!');
            return false;
        }
        var articleId2 = $('#J_articles tr').eq(index+1).find('.down-btn').data('id');
        ctrl.handleUpDown(articleId1, articleId2);
    })
    // 删除
    $('#J_articles').on('click', '.delete-btn', function() {
        var articleId = $(this).data('id');
        ctrl.delete(articleId);
    })
})