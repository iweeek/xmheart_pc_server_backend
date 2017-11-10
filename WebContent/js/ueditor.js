$(function () {
    var ue = UE.getEditor('container');
    var title = $('#articleTitle');
    var digest = $("#wordCount").find("textArea");
    var word = $("#wordCount").find(".word");
//    var maxWord = $("#wordCount").find(".max");
    var tags = $('.tag-input');
    var category1 = $('.select-title-first');
    var category2 = $('.select-title-second');
    var columnId = ''
    var article = {};
    var ctrl = {
            col: [],
        //获取url中的参数
        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        },
        statInputNum: function (digest, numItem) {
            var max = numItem.text();
            var curLength;
            console.log('max',max);
            digest[0].setAttribute("maxlength", max);
            curLength = digest.val().length;
            numItem.text(curLength);
            digest.on('input propertychange', function () {
                numItem.text($(this).val().length);
            });
        },
        getArticle: function (articleId) {
            var url = '/articles/' + articleId;
            $.get(url, function (res) {
                article = res;
                tags.val(res.tags);
                digest.val(res.brief.slice(0,200));
                if (res.brief.length > 200) {
                        word.text(200);
                } else {
                        word.text(res.brief.length);
                }
                
                $('.category').show();
                $('.category-edit').text(res.columnName);
                $('.column-create').hide();
                $('.column-edit').show();
                $('#columnEditName').val(res.columnName);
                $('#articleTitle').val(res.title);
                $('#upload-img').attr('src', res.imgUrl);
                $('#add-image-url').show();
                $('#addImgBtn').hide();
                columnId = res.columnId;
                $('#articleDate').datepicker('setDate', new Date(res.publishTime));
//                $(".form_datetime").datetimepicker('setDate', new Date(res.publishTime));
                // $('.category-create').hide();
                //对编辑器的操作最好在编辑器ready之后再做
                ue.ready(function () {
                    //设置编辑器的内容
                    ue.setContent(res.content);
                });

            });
        },
        publish: function () {
                var $this = $(this);
            // 编辑器内容 ue.getContent()获取html内容，返回: <p>hello</p>  ue.getContentTxt()获取纯文本内容，返回: hello
            var brief = digest.val() ? digest.val() : ue.getContentTxt().slice(0,200) 
            var publishTime = $('[data-toggle="datepicker"]').datepicker('getDate').getTime();
//            var publishTime = $(".form_datetime").datetimepicker('getDate').getTime();
            var imgUrl = $('.upload-img').attr('src');
            var params = {
                columnId: columnId,
                title: title.val(),
                content: ue.getContent(),
                tags: tags.val(),
                brief: brief,
                isPublished: true,
                imgUrl: imgUrl,
                publishTime: publishTime
            };
            
            if (!ctrl.valid(params, 'publish')) {
                return;
            }
            $this.attr('disabled','disabled');
            // 编辑模式
            var articleId = ctrl.getUrlParam('articleId');
            if (articleId) {
                var url = '/articles/' + articleId;
                $.post(url, params, function (res) {
                        $this.removeAttr('disabled');
                    swal({
                        title: "发表成功",
                        text: "返回上一页？",
                        type: "success",
                        showCancelButton: true,
                        confirmButtonColor: "#8cd4f5",
                        confirmButtonText: "返回上一页",
                        cancelButtonText: "留在本页",
                        closeOnConfirm: false
                    }, function () {
//                        window.history.go(-1);
                        location.href="../article-list.html?col=" + ctrl.col;
                    });
                }).error(function() { 
                        $this.removeAttr('disabled');
                        sweetAlert("哎呀", "服务器开小差了~请稍后再试", "error"); 
                    });

                return;
            }

            $.post('/articles', params, function (res) {
                swal({
                    title: "新建文章成功",
                    type: "success",
                    showCancelButton: true,
                    confirmButtonColor: "#8cd4f5",
                    confirmButtonText: "返回上一页",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                }, function () {
                        location.href="../article-list.html?col=" + ctrl.col;
                });
                // window.history.go(-1);
            }).error(function() { 
                    $this.removeAttr('disabled');
                    sweetAlert("哎呀", "服务器开小差了~请稍后再试", "error"); 
                });
        },
        save: function() {
            var $this = $(this);
            // 保存(提交请求但isPublished为false)
        		var brief = digest.val() ? digest.val() : ue.getContentTxt().slice(0,200);
//        		var publishTime = $(".form_datetime").datetimepicker('getDate').getTime();
        		var publishTime = $('[data-toggle="datepicker"]').datepicker('getDate').getTime();
        		var imgUrl = $('.upload-img').attr('src');
            var params = {
	            columnId: columnId,
	            title: title.val(),
	            content: ue.getContent(),
	            tags: tags.val(),
	            brief: brief,
	            isPublished: false,
	            imgUrl: imgUrl,
	            publishTime: publishTime
            };
            
            if (!ctrl.valid(params)) {
                return;
            }
            $this.attr('disabled','disabled');
            // 编辑模式
            var articleId = ctrl.getUrlParam('articleId');
            if (articleId) {
                var url = '/articles/' + articleId;
                $.post(url, params, function (res) {
                        $this.removeAttr('disabled');
                    swal({
                        title: "保存成功",
                        type: "success",
                        showCancelButton: true,
                        confirmButtonColor: "#8cd4f5",
                        confirmButtonText: "发表文章",
                        cancelButtonText: "取消",
                        closeOnConfirm: false
                    }, function () {
                        ctrl.publish();
                    });
                }).error(function() { 
                        $this.removeAttr('disabled');
                        sweetAlert("哎呀", "服务器开小差了~请稍后再试", "error"); 
                    });

                return;
            }

            $.post('/articles', params, function (res) {
                    $this.removeAttr('disabled');
                    $('#wordCount textarea').val(brief);
                swal({
                    title: "新建文章成功",
                    type: "success",
                    showCancelButton: true,
                    confirmButtonColor: "#8cd4f5",
                    confirmButtonText: "继续编辑",
                    cancelButtonText: "新建一篇",
                    closeOnConfirm: false
                }, function(isConfirm){
                        if (isConfirm) {
                              ctrl.goToEditor(res);
                        } else {
                            ctrl.goToCreate();
                        }
                    }); 
            }).error(function() { 
                    $this.removeAttr('disabled');
                    sweetAlert("哎呀", "服务器开小差了~请稍后再试", "error"); 
                });
        },
        
        goToEditor: function(res) {
                var url = '/static/ueditor.html?articleId=' + res.id + '&col=' + ctrl.col;
            location.href = url;
        },
        
        goToCreate: function() {
                var url = '/static/ueditor.html?col=' + ctrl.col;
            location.href = url;
        },
        
        valid: function (params, type) {
            var articleId = ctrl.getUrlParam('articleId');
            
            if (!params.columnId && !articleId) {
                sweetAlert("信息不完整", "请填写分类", "error");
                return false;
            }
            if(!params.title) {
                sweetAlert("信息不完整", "请填写标题", "error");
                return false;
            }

            if (!params.content) {
                sweetAlert("信息不完整", "请填写内容", "error");
                return false;
            }
            if (!params.tags) {
                sweetAlert("信息不完整", "请填写标签", "error");
                return false;
            }
            
            return true;
        },
        preview: function () {
            $('#preview-panel').html(ue.getContent());
        },
        cancel: function () {
            // 取消(直接返回上一级，不做接口交互)
//            window.history.go(-1);
            location.href="../article-list.html?col=" + ctrl.col;
        },
        reset: function () {
            ue.setContent('');
            $('.select-title-second').hide();
            $("#wordCount").find(".word").html('200');
        },
        getColumn: function (columnId, htmlId, selectTitle) {
                var url ="/columns/" + columnId
            $.get(url, function (data) {
                $('.ui-loading').hide();
                var optionString = '';
                for (var i in data) {
                    var jsonObj = data[i];
                    if (jsonObj.columnName == "电子院报" || jsonObj.columnName == "影像厦心") {
                            continue;
                    }
                    optionString += "<option value=\"" + jsonObj.id + "\" >" + jsonObj.columnName + "</option>";
                    $(htmlId).html("<option value='请选择'>请选择</option> " + optionString);
                }
                $(selectTitle).show();
            });
        },
        uploadImg: function () {
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
        },
        init: function () {
            $('.ui-loading').show();
            var articleId = ctrl.getUrlParam('articleId');
            var col = ctrl.getUrlParam('col');
            console.log(location.href);
            ctrl.col = col.split(',')
            console.log("ctrl.col" + ctrl.col);
            ctrl.statInputNum(digest, word);
            // 编辑模式
            ctrl.getColumn(0, '#J_select_first');
            if (articleId) {
                ctrl.getArticle(articleId);
            }
        },
        initDate: function() {
        		$('[data-toggle="datepicker"]').datepicker({
        		    language: 'zh-CN',
        		    format: 'yyyy-mm-dd'
        		 });
        		
//        		$(".form_datetime").datetimepicker({
//        	        format: "yyyy-mm-dd hh:ii",
//        	        autoclose: true,
//        	        todayBtn: true,
//        	        pickerPosition: "bottom",
//        	        	minuteStep: 1
//        	    });
        }
    }
    ctrl.init();
    ctrl.initDate();
    ctrl.uploadImg();
    // 二级分类的出现
    $('#J_select_first').change(function () {
        var firstId = $(this).val();
        if (firstId !== 0) {
                ctrl.col = [];
                ctrl.col.push(firstId);
                columnId = firstId;
                $('.select-title-second').hide();
                $('.select-title-third').hide();
                $('.select-title-fourth').hide();
            ctrl.getColumn(firstId, '#J_select_second', '.select-title-second');
        }
    });
    // 三级分类的出现
    $('#J_select_second').change(function () {
        var firstId = $(this).val();
        if (firstId !== 0) {
                ctrl.col.push(firstId);
                columnId = firstId;
                $('.select-title-third').hide();
                $('.select-title-fourth').hide();
            ctrl.getColumn(firstId, '#J_select_third', '.select-title-third');
        }
    });
    // 四级分类的出现
    $('#J_select_third').change(function () {
        var firstId = $(this).val();
        if (firstId !== 0) {
            ctrl.col.push(firstId);
            columnId = firstId;
            $('.select-title-fourth').hide();
            ctrl.getColumn(firstId, '#J_select_fourth', '.select-title-fourth');
        }
    });
    
    $('#J_select_fourth').change(function () {
        var firstId = $(this).val();
        if (firstId !== 0) {
            ctrl.col.push(firstId);
            columnId = firstId;
        }
    });
    $('#publish').on('click', ctrl.publish);
    $('#reset').on('click', ctrl.reset);
    $('#save').on('click', ctrl.save)
    $('#preview').on('click', ctrl.preview);
    $('#cancel').on('click', ctrl.cancel);
    
    $('#editColumn').on('click', function(){
        $('.column-create').show();
        $('.column-edit').hide();
    })
})