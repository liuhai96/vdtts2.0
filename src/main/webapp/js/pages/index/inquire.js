var path = window.document.location.href.substring(0, (window.document.location.href).indexOf(window.document.location.pathname));
layui.use(['laytpl', 'laypage'], function () {
    var layer = layui.layer
        , laytpl = layui.laytpl
        , $ = layui.jquery
        , laypage = layui.laypage;

    let schoolName = $("#schoolName").val();

    //通过分页和类型来获取数据
    let searchSchoolList = function (curr, name) {
        $.get(path + '/api/school/info', {
            page: curr || 1
            , name: name
        }, function (res) {

            let html = laytpl(SCHOOL_tpl.value).render({
                data: res.list
            });

            $('#schoolList').html(html);

            let stars = $("p[class*='scoreStar']");

            for (let i = 0; i < stars.length; i++) {
                let score = $(stars[i]).attr("tip");
                let starWidth = score / 5 * 150;
                $(stars[i]).attr("style", "width:" + starWidth + "px;float:none;");
            }

            laypage.render({
                elem: 'demo0'
                , count: res.count
                , limit: 6
                , groups: 3
                , curr: curr || 1
                , layout: ['prev', 'page', 'next', 'skip']
                , jump: function (e, first) {
                    if (!first) { //一定要加此判断,否则初始时会无限刷新
                        searchSchoolList(e.curr, name);
                    }
                }
            });
        });
    };

    searchSchoolList(1, schoolName);

    $("#selectSchoolByName").on('click', function (event) {
        schoolName = $("#schoolName").val();
        searchSchoolList(1, schoolName);
    });


    let teacherName = $("#teacherName").val();
    let teacherSex = "";

    //通过分页和类型来获取数据
    let searchTeacherList = function (curr, name, sex) {
        $.get(path + '/api/teacher/info', {
            page: curr || 1
            , name: name
            , sex: sex
        }, function (res) {

            let html = laytpl(TEACHER_tpl.value).render({
                data: res.list
            });

            $('#teacherList').html(html);

            let stars = $("p[class*='scoreStar']");

            for (let i = 0; i < stars.length; i++) {
                let score = $(stars[i]).attr("tip");
                let starWidth = score / 5 * 150;
                $(stars[i]).attr("style", "width:" + starWidth + "px;float:none;");
            }

            laypage.render({
                elem: 'demo1'
                , count: res.count
                , limit: 6
                , groups: 3
                , curr: curr || 1
                , layout: ['prev', 'page', 'next', 'skip']
                , jump: function (e, first) {
                    if (!first) { //一定要加此判断,否则初始时会无限刷新
                        searchTeacherList(e.curr, name, sex);
                    }
                }
            });
        });
    };

    searchTeacherList(1, teacherName, teacherSex);

    $(".sexBtn").on("click", function (event) {
        teacherSex = $(this).val();

        searchTeacherList(1, teacherName, teacherSex);
    });

    $("#selectTeacherByName").on('click', function (event) {
        teacherName = $("#teacherName").val();

        searchTeacherList(1, teacherName, teacherSex);
    });

    // $(".teacherInfoLink").on("click", function (event) {
    //     console.log($(this).attr("info"));
    // });

    console.log($("p.teacherInfoLink").eq(0));
});