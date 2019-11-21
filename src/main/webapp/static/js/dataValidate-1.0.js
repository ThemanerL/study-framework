/**
 * 相当于 pageContext.setAttribute("APP_PATH", request.getContextPath());
 * @type {string}
 */
var APP_PATH = (function () {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    return pathName.substr(0, index + 1);
})();

/**
 * 当前的总页码数
 */
var currentPages;

/**
 * 当前的页码
 */
var currentPageNum;

/**
 * 清空input框的状态和错误提示信息
 * @param ele 指定将被清除样式的元素
 */
function formReset(ele) {
    //清空表单样式
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}

/**
 * 查询所有的部门信息添加到下拉选择框
 * @param ele 添加到哪个下拉框
 */
function getDepts(ele) {
    $.ajax({
        url: APP_PATH + "/depts",
        type: "GET",
        success: function (result) {
            // 清空下拉列表中上次添加的选项
            $(ele).empty();
            $.each(result.map.depts, function (index, dept) {
                var optionEle = $("<option>").append(dept.deptName).attr("value", dept.id);
                $(ele).append(optionEle);
            })
        }
    })
}

/**
 * 获得指定Emp的信息
 * @param empId
 */
function getEmp(empId) {
    $.ajax({
        url: APP_PATH + "/emp/" + empId,
        type: "GET",
        success: function (result) {
            var emp = result.map.emp;
            $("#empName_update_show").text(emp.empName);
            $("#email_update_input").val(emp.email);
            $("#empUpdateModal input[name=gender]").val([emp.gender]);
            $("#empUpdateModal select").val([emp.dId]);
        }
    })
}

/**
 * 在从该input框失去焦点后调用校验
 * @param ele 要校验的元素，支持"#email_add_input"或者#empName_add_input
 * @param reg 要对比的正则表达式
 * @param msg 如果校验失败将显示的内容
 * @param dataType 要效验的数据是什么类型，两个可选的值email/empName
 */
function lostFocus(ele, reg, msg, dataType) {
    var originValue = $(ele).val();
    if (!reg.test(originValue)) {
        submitCode = false;
        show_validate_msg(ele, "error", msg);
    } else {
        submitCode = true;
        checkUsed(ele, originValue, dataType);
    }
}

/**
 * 查找该元素在数据库中有没有重复
 * @param ele 要校验的元素，支持"#email_add_input"或者#empName_add_input
 * @param originValue 要效验的数据的值
 * @param dataType 要效验的数据是什么类型，两个可选的值email/empName
 */
function checkUsed(ele, originValue, dataType) {
    $.ajax({
        url: APP_PATH + "/validate",
        type: "POST",
        data: JSON.stringify({"dataType": dataType, "value": originValue}),
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            if (444 === result.statusCode) {
                submitCode = false;
                show_validate_msg(ele, "error", ("empName" === dataType ? "用户名" : "邮箱") + "已经被使用！")
            } else {
                submitCode = true;
                show_validate_msg(ele, "success", "")
            }
        }
    })
}

/**
 * add提交后根据后台状态在前台显示提示
 * @param result 后台返回的数据
 */
function addMsg(result) {
    formReset("#empAddModal form");
    if (400 === result.statusCode) {
        if (undefined !== result.map.error.email) {
            show_validate_msg("#email_add_input", "error", result.map.error.email);
        } else {
            show_validate_msg("#empName_add_input", "error", result.map.error.empName);
        }
    } else if (444 === result.statusCode) {
        alert(result.msg);
    } else {
        //隐藏模态框
        $("#empAddModal").modal('hide');
        //清空表单
        $("#empAddModal form")[0].reset();
        //将模块input框的状态信息提示信息都清空
        formReset("#empAddModal form");
        //跳转到最后一页
        toPage(currentPages + 1);
        alert(result.msg);
    }
}

/**
 * update提交后根据后台状态在前台显示提示
 * @param result 后台返回的数据
 */
function updateMsg(result) {
    formReset("#empUpdateModal form");
    if (400 === result.statusCode) {
        show_validate_msg("#email_update_input", "error", result.map.error.email);
    } else if (444 === result.statusCode) {
        alert(result.msg);
    } else {
        //隐藏模态框
        $("#empUpdateModal").modal('hide');
        //清空表单
        $("#empUpdateModal form")[0].reset();
        //将模块input框的状态信息提示信息都清空
        formReset("#empUpdateModal form");
        //跳转回初始记录所在页
        toPage(currentPageNum);
        alert(result.msg);
    }
}

/**
 * 输入框增加成功失败颜色更换和显示文字提示
 * @param ele 这是ele字段的错误提示
 * @param status 将要给span增加的类
 * @param msg 错误提示
 */
function show_validate_msg(ele, status, msg) {
    $(ele).parent().removeClass("has-error has-success");
    $(ele).next("span").text();
    if ("error" === status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    } else {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    }
}

/**
 * 跳转到指定页码
 * @param pn
 */
function toPage(pn) {
    $.ajax({
        url: APP_PATH + "/emps",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            // 1、解析表格数据
            build_emps_table(result);
            // 2、解析分页数据
            build_page_info(result);
            // 3、点击页码跳转
            build_page_nav(result);
        }
    });
}

<!-- ----------------------------------以下方法在toPage中使用------------------------------- -->
/**
 * 解析前台list表单表格数据进行填充展示
 * @param result
 */
function build_emps_table(result) {
    $("#emps_table tbody").empty();
    var emps = result.map.pageInfo.list;
    $.each(emps, function (index, item) {
        var checkBoxTd = $("<td><input type='checkBox' class='check_item'>");
        var empIdTd = $("<td>").append(item.id);
        var empNameTd = $("<td>").append(item.empName);
        var empGenderTd = $("<td>").append("0" === item.gender ? "女" : "男");
        var empEmailTd = $("<td>").append(item.email);
        var deptNameTd = $("<td>").append(item.department.deptName);
        var editBtn = $("<button>").addClass("btn btn-info btn-sm edit_btn")
            .append($("<span>").addClass("glyphicon glyphicon-pencil"))
            .append("编辑").attr("empId", item.id);
        var deleteBtn = $("<button>").addClass("btn btn-danger btn-sm delete_btn")
            .append($("<span>").addClass("glyphicon glyphicon-trash"))
            .append("删除").attr("empId", item.id);
        var btnTd = $("<td>").append(editBtn).append(" ").append(deleteBtn);
        // append返回执行后返回原来的元素
        $("<tr>").append(checkBoxTd)
            .append(empIdTd)
            .append(empNameTd)
            .append(empGenderTd)
            .append(empEmailTd)
            .append(deptNameTd)
            .append(btnTd)
            .appendTo("#emps_table tbody");
    });
}

<!-- 解析显示分页信息 -->
function build_page_info(result) {
    var page_info = $("#page_info");
    page_info.empty();
    var pageNum = result.map.pageInfo.pageNum;
    currentPageNum = pageNum;
    var pages = result.map.pageInfo.pages;
    currentPages = pages;
    var total = result.map.pageInfo.total;
    page_info.append("当前第" + pageNum + "页,总" + pages + "页,总" + total + "条记录");
}

<!-- 分页导航条 -->
function build_page_nav(result) {
    //page_nav
    $("#page_nav").empty();
    var ul = $("<ul>").addClass("pagination");

    //构建元素
    var firstPageLi = $("<li>").append($("<a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li>").append($("<a>").append("&laquo;"));
    if (false === result.map.pageInfo.hasPreviousPage) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        firstPageLi.click(function () {
            toPage(1);
        });
        prePageLi.click(function () {
            toPage(result.map.pageInfo.pageNum - 1);
        });
    }

    var nextPageLi = $("<li>").append($("<a>").append("&raquo;"));
    var lastPageLi = $("<li>").append($("<a>").append("末页").attr("href", "#"));
    if (false === result.map.pageInfo.hasNextPage) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            toPage(result.map.pageInfo.pageNum + 1);
        });
        lastPageLi.click(function () {
            toPage(result.map.pageInfo.pages);
        });
    }
    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    $.each(result.map.pageInfo.navigatepageNums, function (index, item) {
        var numLi = $("<li>").append($("<a>").append(item));
        if (result.map.pageInfo.pageNum === item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            toPage(item);
        });
        ul.append(numLi);
    });
    //添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);

    //把ul加入到nav
    var navEle = $("<nav>").append(ul);
    navEle.appendTo("#page_nav");
}

<!-- --------------------------------------------------------------------------------------- -->
