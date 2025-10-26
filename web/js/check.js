
function allcheck(checkbox) {
    //alert("全选");
    let checks = document.getElementsByName("check");
    for (let i = 0; i < checks.length; i++) {
        checks[i].checked = checkbox.checked; //使表单的勾选框与表头的全选框保持一致
    }
}

function delcheck(path, id, id2) {
    alert("批量删除数据");
    //先记录下来，批量发给后端，进行删除
    var ids = new Array();
    var ids2 = new Array();
    var flag = false;
    let checks = document.getElementsByName("check");
    let count = 0;
    for (let i = 0; i < checks.length; i++) {
        if (checks[i].checked) //如果checks[i]被选中
        {
            let val = checks[i].value.split(",");
            ids.push(val[0]);  // sid
            ids2.push(val[1]);  // iid
            count++;
            flag = true;
        }
    }
    if (flag) {
        if (confirm("你确定删除吗？")) {
            location.href = path + "?flag=all&" + id + "="
                + encodeURIComponent(ids.join(",")) + "&" + id2 + "="
                + encodeURIComponent(ids2.join(","));
            alert("删除了" + count + "条数据");
            alert(ids);
        }
    } else {
        alert("没有选中数据");
    }
}
function OKcheck() {
    var ids = new Array();
    var flag = false;
    let checks = document.getElementsByName("check");
    let count = 0;
    for (let i = 0; i < checks.length; i++) {
        if (checks[i].checked) //如果checks[i]被选中
        {
            let val = checks[i].value;
            ids.push(val);  // sid
            count++;
            flag = true;
        }
    }
    if (flag) {
            location.href = "/check?count=all&sid="
                + encodeURIComponent(ids.join(","));
            alert("通过" + count + "条数据");
            alert(ids);
    } else {
        alert("没有选中数据");
    }
}
function checkForm() {
    var userId = document.getElementById("userId").value;
    var password = document.getElementById("password").value;
    var OKPassword = document.getElementById("OKPassword").value;

    var reg = /^[0-9][0-9][0-9]$/;
    if (userId == "") {
        alert("用户名不能为空");
        return false;
    } else if (!userId.match(reg)) {
        alert("用户名格式不对");
        return false;
    }

    if (password == "") {
        alert("密码不能为空");
        return false;
    }

    if (password != OKPassword) {
        alert("两次输入的密码不一致");
        return false;
    }
    return true;
}

function addStuRow() {
    var table = document.getElementById("TableBody");
    var row = table.insertRow();
    row.innerHTML =
        "<td><input type='text' name='sid' value=''></td>" +
        "<td><input type='text' name='sname' value=''></td>" +
        "<td><input type='text' name='spassword' value=''></td>"+
        "<td><input type='text' name='sright' value=''></td>"+
        "<td><input type='text' name='stflag' value=''></td>";
}
function addItemRow() {
    var table = document.getElementById("TableBody");
    var row = table.insertRow();
    row.innerHTML =
        "<td><input type='text' name='iid' value=''></td>" +
        "<td><input type='text' name='iname' value=''></td>" +
        "<td><input type='text' name='iflag' value=''></td>";
}
function addJobRow() {
    var table = document.getElementById("TableBody");
    var row = table.insertRow();
    row.innerHTML =
        "<td><input type='text' name='sid' value=''></td>" +
        "<td><input type='text' name='iid' value=''></td>" +
        "<td><input type='text' name='job' value=''></td>" +
        "<td><input type='text' name='scflag' value=''></td>";
}

function AJAX(InsertUrl,path,Object)
{
    $.ajax({
        type: "POST",
        url: InsertUrl, // 后端接口地址
        data: JSON.stringify(Object), // 将 JavaScript 对象数组转换为 JSON 字符串
        contentType: "application/json", // 告知服务器请求体是 JSON 格式

        success: function (data) {
            console.log("响应数据:", data);
            console.log("666"); // 你的调试信息

            if (data.status) { // 确保 data 对象存在且有 status 属性
                alert("提交成功！");
                location.href = path; // 提交成功跳转至/queryItemAll页面
            } else {
                // 处理后端返回 status 为 false 或其他非预期成功状态
                let message = "提交操作未成功";
                if (data && data.message) { // 如果后端返回了具体的错误信息
                    message += "：" + data.message;
                }
                alert(message);
                console.log("后端响应指示操作未成功:", data);
            }
        },
        error: function (xhr, status, error) {
            //hideLoading(); // <--- 确保 hideLoading 函数已定义且可用
            alert("提交失败，请检查网络或联系管理员！");
            console.error("AJAX请求错误状态: ", status);
            console.error("抛出的错误详情: ", error);
            console.error("服务器响应体 (XHR):", xhr.responseText); // 这对于调试后端错误非常有用
        }
    });
}

async function insertItem(event) { // <--- 1. 接受 event 参数
    if (event) { // 检查 event 是否传入，以防函数被其他方式调用
        event.preventDefault(); // <--- 2. 阻止表单的默认提交行为
    }

    //读取表格，相当于2维数组
    let rows = document.querySelectorAll("#TableBody tr");
    let items = [];

    for (let row of rows) {
        //获取每一行输入框的值，1维数组
        let inputs = row.querySelectorAll("input");
        //  创建一个对象，将输入框的值保存起来
        let item = {
            iid: inputs[0].value.trim(),
            iname: inputs[1].value.trim(),
            iflag: inputs[2].value.trim()
        };
        // 确保所有字段都有值才添加到数组中
        if (item.iid && item.iname && item.iflag) {
            items.push(item);
        } else if (item.iid || item.iname || item.iflag) { // 如果行内有任何一个输入框填写了，但不是全部，可以给个提示或忽略
            console.warn("表格中有一行数据不完整，已忽略该行:", item);
            alert("表格中有一行数据不完整，已忽略该行。"+item.iid+" "+item.iname+" "+item.iflag);
        }
    }
    if (items.length === 0) {
        alert("没有有效的数据可以提交！请至少完整填写一行。");
        return;
    }
    AJAX('/insertItem','/queryItemAll',items);
}

async function insertStu(event) { // <--- 1. 接受 event 参数
    if (event) { // 检查 event 是否传入，以防函数被其他方式调用
        event.preventDefault(); // <--- 2. 阻止表单的默认提交行为
    }

    //读取表格，相当于2维数组
    let rows = document.querySelectorAll("#TableBody tr");
    let students = [];

    for (let row of rows) {
        //获取每一行输入框的值，1维数组
        let inputs = row.querySelectorAll("input");
        //  创建一个对象，将输入框的值保存起来
        let stu = {
            sid: inputs[0].value.trim(),
            sname: inputs[1].value.trim(),
            spassword: inputs[2].value.trim(),
            sright: inputs[3].value.trim(),
            stflag: inputs[4].value.trim()
        };
        // 确保所有字段都有值才添加到数组中
        if (stu.sid && stu.sname && stu.spassword && stu.sright && stu.stflag) {
            students.push(stu);
        } else if (stu.sid || stu.sname || stu.spassword || stu.sright || stu.stflag) { // 如果行内有任何一个输入框填写了，但不是全部，可以给个提示或忽略
            console.warn("表格中有一行数据不完整，已忽略该行:", stu);
            alert("表格中有一行数据不完整，已忽略该行。"+stu.sid+" "+stu.sname+" "+stu.spassword+" "+stu.sright+" "+stu.stflag);
        }
    }
    if (students.length === 0) {
        alert("没有有效的数据可以提交！请至少完整填写一行。");
        return;
    }
    AJAX('/insertStudent','/queryStuAll',students);
}

async function insertJob(event) { // <--- 1. 接受 event 参数
    if (event) { // 检查 event 是否传入，以防函数被其他方式调用
        event.preventDefault(); // <--- 2. 阻止表单的默认提交行为
    }

    //读取表格，相当于2维数组
    let rows = document.querySelectorAll("#TableBody tr");
    let jobs = [];

    for (let row of rows) {
        //获取每一行输入框的值，1维数组
        let inputs = row.querySelectorAll("input");
        //  创建一个对象，将输入框的值保存起来
        let job = {
            sid: inputs[0].value.trim(),
            iid: inputs[1].value.trim(),
            job: inputs[2].value.trim(),
            scflag: inputs[3].value.trim()
        };
        // 确保所有字段都有值才添加到数组中
        if (job.sid && job.iid && job.job  && job.scflag) {
            jobs.push(job);
        } else if (job.sid || job.iid || job.job  || job.scflag) { // 如果行内有任何一个输入框填写了，但不是全部，可以给个提示或忽略
            console.warn("表格中有一行数据不完整，已忽略该行:", job);
            alert("表格中有一行数据不完整，已忽略该行。"+job.sid+" "+job.iid+" "+job.job+" "+job.scflag);
        }
    }
    if (jobs.length === 0) {
        alert("没有有效的数据可以提交！请至少完整填写一行。");
        return;
    }
    AJAX('/insertJob','/queryJobAll',jobs);
}