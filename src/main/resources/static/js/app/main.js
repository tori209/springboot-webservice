var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var file = $('#files')[0].files[0];
        var ajaxOption = {
            type: 'POST',
            url: '/posts'
        };

        var baseData = {
            title: $("#title").val(),
            author: $('#author').val(),
            content: $('#content').val()
        }

        // 파일 업로드 X 혹은 사이즈 0 파일일 경우
        if (file === undefined || file.size === 0) {
            ajaxOption.contentType = 'application/json;charset=utf-8';
            ajaxOption.data = JSON.stringify(baseData);
        }
        // 파일 업로드가 필요한 경우
        else {
            var form = new FormData();
            form.set("files", file);
            form.set("post", JSON.stringify(baseData));
            ajaxOption.contentType = false;
            ajaxOption.processData = false;
            ajaxOption.data = form;
        }

        $.ajax(ajaxOption).done(function() {
            alert('글이 등록되었습니다.');
            location.reload();
        }).fail(function (error) {
            alert(error.stack);
        });
    }

};



main.init();