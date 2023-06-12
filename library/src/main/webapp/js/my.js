//借阅窗口中时间标签的内容改变时执行
var ctx = "/";
function cg() {
    $("#savemsg").attr("disabled", false);
    var rt = $("#time").val().split("-");
    var ny = new Date().getFullYear();
    var nm = new Date().getMonth() + 1;
    var nd = new Date().getDate();
    if (rt[0] < ny) {
        alert("日期不能早于今天")
        $("#savemsg").attr("disabled", true);
    } else if (rt[0] == ny) {
        if (rt[1] < nm) {
            alert("日期不能早于今天")
            $("#savemsg").attr("disabled", true);
        } else if (rt[1] == nm) {
            if (rt[2] < nd) {
                alert("日期不能早于今天")
                $("#savemsg").attr("disabled", true);
            } else {
                $("#savemsg").attr("disabled", false);
            }
        }
    }
}
//点击借阅图书时执行
function borrow() {
    var url =getProjectPath()+ "/book/borrowBook";
    $.post(url, $("#borrowBook").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath()+"/book/search";
        }
    })
}

//重置添加和编辑窗口中输入框的内容
function resetFrom() {
    $("#aoe").attr("disabled",true)
    var $vals=$("#addOrEditBook input");
    $vals.each(function(){
        $(this).attr("style","").val("")
    });
}
//重置添加和编辑窗口中输入框的样式
function resetStyle() {
    $("#aoe").attr("disabled",false)
    var $vals=$("#addOrEditBook input");
    $vals.each(function(){
        $(this).attr("style","")
    });
}
//查询id对应的图书信息，并将图书信息回显到编辑或借阅的窗口中
function findBookById(bookId,doname) {
    resetStyle()
    var url = getProjectPath()+"/book/findById?bookId=" + bookId;
    $.get(url, function (response) {
        //如果是编辑图书，将获取的图书信息回显到编辑的窗口中
        if(doname=='edit'){
            $("#ebid").val(response.data.bookId);
            $("#ebname").val(response.data.bookName);
            $("#ebisbn").val(response.data.bookIsbn);
            $("#ebpress").val(response.data.bookPress);
            $("#ebauthor").val(response.data.bookAuthor);
            $("#ebpagination").val(response.data.bookPagination);
            $("#ebprice").val(response.data.bookPrice);
            $("#ebstatus").val(response.data.bookStatus);
        }
        //如果是借阅图书，将获取的图书信息回显到借阅的窗口中
        if(doname=='borrow'){
            $("#savemsg").attr("disabled",true)
            $("#time").val("");
            $("#bid").val(response.data.bookId);
            $("#bname").val(response.data.bookName);
            $("#bisbn").val(response.data.bookIsbn);
            $("#bpress").val(response.data.bookPress);
            $("#bauthor").val(response.data.bookAuthor);
            $("#bpagination").val(response.data.bookPagination);
        }
    })
}
//点击添加或编辑的窗口的确定按钮时，提交图书信息
function addOrEdit() {
    //获取表单中图书id的内容
    var ebid = $("#ebid").val();
    //如果表单中有图书id的内容，说明本次为编辑操作
    if (ebid > 0) {
        var url = getProjectPath()+"/book/editBook";
        $.post(url, $("#addOrEditBook").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/book/search";
            }
        })
    }
    //如果表单中没有图书id，说明本次为添加操作
    else {
        var url = getProjectPath()+"/book/addBook";
        $.post(url, $("#addOrEditBook").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/book/search";
            }
        })
    }
}
//归还图书时执行
function returnBook(bookId) {
    var r = confirm("确定归还图书?");
    if (r) {
        var url = getProjectPath()+"/book/returnBook?bookId=" + bookId;
        $.get(url, function (response) {
            alert(response.message)
            //还书成功时，刷新当前借阅的列表数据
            if (response.success == true) {
                window.location.href = getProjectPath()+"/book/searchBorrowed";
            }
        })
    }
}
//确认图书已经归还
function returnConfirm(bookId) {
    var r = confirm("确定图书已归还?");
    if (r) {
        var url = getProjectPath()+"/book/returnConfirm?bookId=" + bookId;
        $.get(url, function (response) {
            alert(response.message)
            //还书确认成功时，刷新当前借阅的列表数据
            if (response.success == true) {
                window.location.href = getProjectPath()+"/book/searchBorrowed";
            }
        })
    }
}
//检查图书信息的窗口中，图书信息填写是否完整
function checkval(){
    var $inputs=$("#addOrEditTab input")
    var count=0;
    $inputs.each(function () {
        if($(this).val()==''||$(this).val()=="不能为空！"){
            count+=1;
        }
    })
    //如果全部输入框都填写完整，解除确认按钮的禁用状态
    if(count==0){
        $("#aoe").attr("disabled",false)
    }
}
//页面加载完成后，给图书模态窗口的输入框绑定失去焦点和获取焦点事件
$(function() {
    var $inputs=$("#addOrEditBook input")
    var eisbn="";
    $inputs.each(function () {
        //给输入框绑定失去焦点事件
        $(this).blur(function () {
            if($(this).val()==''){
                $("#aoe").attr("disabled",true)
                $(this).attr("style","color:red").val("不能为空！")
            }
            else if($(this).attr("name")=="isbn"&&eisbn!==$(this).val()){
                if($(this).val().length!=13){
                    $("#aoe").attr("disabled",true)
                    alert("必须为13位数的标准ISBN，请重新输入！")
                    $(this).val("")
                }
            }else{
                checkval()
            }
        }).focus(function () {
            if($(this).val()=='不能为空！'){
                $(this).attr("style","").val("")
            }else{
                $(this).attr("style","")
            }
            if($(this).attr("name")=="isbn"){
                eisbn=$(this).val();
            }
        })
    })
});


//重置添加和编辑窗口中输入框的内容
function resetUserFrom() {
    $("#savemsg").attr("disabled",true)
    $("#addmsg").html("")
    var $vals=$("#addUer input");
    $vals.each(function(){
        $(this).attr("style","").val("")
    });

}
function findUserById(uId) {
    var url = getProjectPath()+"/users/findById?uId=" + uId;
    $.get(url, function (response) {
        $("#uid").val(response.data.uId);
        $("#uname").val(response.data.uName);
        $("#pw").val(response.data.uPassword);
        $("#urole").val(response.data.uRole);
        $("#uemail").val(response.data.uEmail);
        $("#uhire").val(response.data.uHiredate);

    })
}

function editUser() {
    var url =getProjectPath()+ "/users/editUser";
    $.post(url, $("#editUser").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath()+"/users/search";
        }
    })
}


function changeVal() {
    $("#addmsg").html("")
}

function checkVal() {
    $("#savemsg").attr("disabled", false);
    $("#addmsg").html("")
    var adduname = $("#adduname").val();
    var adduemail = $("#adduemail").val();
    var addPw = $("#addPw").val();
    var addtime = $("#time").val();
    if ($.trim(adduname) == '') {
        $("#savemsg").attr("disabled", true);
        $("#addmsg").html("姓名不能为空")
    } else {
        checkName(adduname);
        if ($.trim(adduemail) == '') {
            $("#savemsg").attr("disabled", true);
            $("#addmsg").html("邮箱不能为空")
        } else if ($.trim(adduemail) != '') {
            checkEmail(adduemail);
                if ($.trim(addPw) == '') {
                $("#savemsg").attr("disabled", true);
                $("#addmsg").html("密码不能为空")
            }else if($.trim(addPw) != ''){
                if($.trim(addtime) == ''){
                    $("#savemsg").attr("disabled", true);
                    $("#addmsg").html("入职日期不能为空")
                }else{
                    cg()
                }
            }
        }
    }
}

function checkName(uName, uEmail) {
    var url = getProjectPath()+"/users/checkName?u_name=" + uName;
    $.post(url, function (response) {
        if (response.success != true) {
            $("#savemsg").attr("disabled", true);
            $("#addmsg").html(response.message)
        }
    })
}

function checkEmail(uEmail) {
    var url = getProjectPath()+"/users/checkEmail?u_email=" + uEmail;
    $.post(url, function (response) {
        if (response.success != true) {
            $("#savemsg").attr("disabled", true);
            $("#addmsg").html(response.message)
        }
    })
}

function saveUser() {
    var url =getProjectPath()+"/users/addUser";
    $.post(url, $("#addUser").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href =  getProjectPath()+"/users/search";
            // alert("新增成功！")
        }
    })

}
function delUser(uId) {
    var r = confirm("确定办理工号：" + uId + "的离职?");
    if (r) {
        var url = getProjectPath() + "/users/delUser?uId=" + uId;
        $.get(url, function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath() + "/users/search";
            }
        })
    }
}
//获取当前项目的名称
    function getProjectPath() {
        //获取主机地址之后的目录，如： cloudlibrary/admin/books.jsp
        //var pathName = window.document.location.pathname;
        //获取带"/"的项目名，如：/cloudlibrary
        //var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
       // return projectName;
        if(ctx == '/') ctx = '';
        return ctx;
    }

    /**
     * 数据展示页面分页插件的参数
     * cur 当前页
     * total 总页数
     * len   显示多少页数
     * pagesize 1页显示多少条数据
     * gourl 页码变化时 跳转的路径
     * targetId 分页插件作用的id
     */
    var pageargs = {
        cur: 1,
        total: 0,
        len: 5,
        pagesize: 5,
        gourl: "",
        targetId: 'pagination',
        callback: function (total) {
            var oPages = document.getElementsByClassName('page-index');
            for (var i = 0; i < oPages.length; i++) {
                oPages[i].onclick = function () {
                    changePage(this.getAttribute('data-index'), pageargs.pagesize);
                }
            }
            var goPage = document.getElementById('go-search');
            goPage.onclick = function () {
                var index = document.getElementById('yeshu').value;
                if (!index || (+index > total) || (+index < 1)) {
                    return;
                }
                changePage(index, pageargs.pagesize);
            }
        }
    }
    /**
     *图书查询栏的查询参数
     * name 图书名称
     * author 图书作者
     * press 图书出版社
     */
    var bookVO = {
        name: '',
        author: '',
        press: ''
    }
    /**
     *借阅记录查询栏的查询参数
     * name 图书名称
     * borrower 借阅人
     */
    var recordVO = {
        recordBookname: '',
        recordBorrower: ''
    }
    var userVO = {
        record_id: '',
        record_bookname: ''
    }

//数据展示页面分页插件的页码发送变化时执行
    function changePage(pageNo, pageSize) {
        pageargs.cur = pageNo;
        pageargs.pagesize = pageSize;
        document.write("<form action=" + pageargs.gourl + " method=post name=form1 style='display:none'>");
        document.write("<input type=hidden name='pageNum' value=" + pageargs.cur + " >");
        document.write("<input type=hidden name='pageSize' value=" + pageargs.pagesize + " >");
        //如果跳转的是图书信息查询的相关链接，提交图书查询栏中的参数
        if (pageargs.gourl.indexOf("book") >= 0) {
            document.write("<input type=hidden name='name' value=" + bookVO.name + " >");
            document.write("<input type=hidden name='author' value=" + bookVO.author + " >");
            document.write("<input type=hidden name='press' value=" + bookVO.press + " >");
        }
        //如果跳转的是图书记录查询的相关链接，提交图书记录查询栏中的参数
        if (pageargs.gourl.indexOf("user") >= 0) {
            document.write("<input type=hidden name='id' value=" + userVO.id + " >");
            document.write("<input type=hidden name='name' value=" + userVO.name + " >");
        }
        //如果跳转的是图书记录查询的相关链接，提交图书记录查询栏中的参数
        if (pageargs.gourl.indexOf("record") >= 0) {
            document.write("<input type=hidden name='bookname' value=" + recordVO.bookname + " >");
            document.write("<input type=hidden name='borrower' value=" + recordVO.borrower + " >");
        }
        document.write("</form>");
        document.form1.submit();
        pagination(pageargs);
    }


