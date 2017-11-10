$(function() {
    var ctrl = {
        columnId : 0,
        pageNo : 1,
        pageTotal : 0,
        noNextPage : false,
        pinnedNum : 0,
        getColumns: function (parentColumnId, htmlId) {
            $.get('/columns', {
                parentColumnId: parentColumnId
            }, function (data) {
                var optionString = '';
                for (var i in data) {
                    var jsonObj = data[i];
                    optionString += "<option value=\"" + jsonObj.id + "\" >" + jsonObj.columnName + "</option>";
                    $(htmlId).html("<option value='请选择'>请选择</option> " + optionString);
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
        
        getVideos : function(pageNo, pageSize) {
            $('.ui-loading').show();
            var loading = true;
            $.get('/onlineVideos', {
                pageNo : pageNo,
                pageSize : pageSize
            }, function(data) {
                if (data.length < ctrl.pageSize) {
                    ctrl.noNextPage = true;
                }
                $.each(data, function(name, val) {
                    val.publishTime = ctrl.dateFilter(val.publishTime)
//                  if (val.isPinned) {
//                      ctrl.pinnedNum++
//                  }
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
            var url = '/onlineVideos/' + videoId;
            $.post(url, params, function(res) {
                swal("发布成功！");
                ctrl.getVideos(ctrl.pageNo, 10);
            });
        },
        offline : function(videoId) {
            var params = {
                isPublished : false
            };
            // 编辑模式
            var url = '/onlineVideos/' + videoId;
            $.post(url, params, function(res) {
                swal("下线成功！");
                ctrl.getVideos(ctrl.pageNo, 10);
            });
        },
        previous : function() {
            if (ctrl.pageNo > 1) {
                ctrl.pageNo--;
                ctrl.getVideos(ctrl.pageNo, 10, ctrl.columnId);
            }
        },
        next : function() {
            if (!ctrl.noNextPage) {
                ctrl.pageNo++;
                ctrl.getVideos(ctrl.pageNo, 10, ctrl.columnId);
            }
        },
        pinned : function(articleId, type) {
            var url = '/articles/' + articleId;
            $.get(url, function(res){
                console.log(res)
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
                            location.reload()
                        });
                    });
                }
            })
        },
        handleUpDown : function(articleId, pinOrder, type) {
            var url = '/articles/' + articleId;
            if (type === 'up') {
                if (pinOrder == 0) {
                    swal('已经是最顶部了!');
                    return false;
                } else {
                    pinOrder = pinOrder - 1;
                }
            }
            if (type === 'down') {
                if (pinOrder == ctrl.pinnedNum) {
                    swal('已经是最底部了!');
                    return false;
                } else {
                    pinOrder = pinOrder + 1;
                }
            }
            var params = {
                pinOrder : pinOrder,
                isPinned : true
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
            });
        },
        init : function() {
            ctrl.getVideos(ctrl.pageNo, 10, ctrl.columnId);
            $('.ui-nodata').hide();
        }
    }
    ctrl.init();

    // 二级分类的出现
    $('#J_select_first').change(function() {
        var firstId = $(this).val();
        var currentText = $( "#J_select_first option:selected" ).text();
        if (currentText === '请选择') {
            $('.select-title-second').hide();
        }
        if (firstId !== 0 && currentText !== '请选择') {
            ctrl.columnId = firstId;
            $('.select-title-second').show();
            ctrl.getColumns(firstId, '#J_select_second');
        }
        if (currentText == '首页') {
            $('.select-title-second').hide();
        }
    });

    // 获取二级分类的id
    $('#J_select_second').change(function() {
        var secondId = $(this).val();
        var currentText = $( "#J_select_second option:selected" ).text();
        if (secondId !== 0 && currentText !== '请选择') {
            ctrl.columnId = $(this).val()
        };
    });

    // 筛选
    $('#J_filter_btn').on('click', function() {
        ctrl.getVideos(1, 10, ctrl.columnId);
    });

    // 上一页
    $('.previous').on('click', ctrl.previous);

    // 下一页
    $('.next').on('click', ctrl.next);

    // 编辑
    $('#J_articles')
            .on(
                    'click',
                    '.edit-btn',
                    function() {
                        var videoId = $(this).data('id');
                        location.href = 'online_video_edit.html?videoId='
                                + videoId;
                    });

    // 发布
    $('#J_articles').on('click', '.publish-btn', function() {
        var videoId = $(this).data('id');
        ctrl.publish(videoId);
    });

    // 下线
    $('#J_articles').on('click', '.offline-btn', function() {
        var videoId = $(this).data('id');
        ctrl.offline(videoId);
    })

    // 新建
    $('#J_create_btn').on('click', function() {
        location.href = 'online_video_edit.html';
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
        var articleId = $(this).data('id');
        var pinOrder = $(this).data('order');
        ctrl.handleUpDown(articleId, pinOrder, 'up');
    })
    // 下移
    $('#J_articles').on('click', '.down-btn', function() {
        var articleId = $(this).data('id');
        var pinOrder = $(this).data('order');
        ctrl.handleUpDown(articleId, pinOrder, 'down');
    })
})