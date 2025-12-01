
function allcheck(checkbox) {
    //alert("全选");
    let checks = document.getElementsByName("check");
    for (let i = 0; i < checks.length; i++) {
        checks[i].checked = checkbox.checked; //使用复选框的状态来保持全选状态一致
    }
}

function delcheck(path, id, id2) {
    alert("开始删除操作");
    //先记录要删除的记录，然后进行删除
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
        if (confirm("确定要删除吗？")) {
            location.href = path + "?flag=all&" + id + "="
                + encodeURIComponent(ids.join(",")) + "&" + id2 + "="
                + encodeURIComponent(ids2.join(","));
            alert("删除了" + count + "条记录");
            alert(ids);
        }
    } else {
        alert("没有选中记录");
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
            location.href = "../student/check?count=all&sid="
                + encodeURIComponent(ids.join(","));
            alert("通过了" + count + "条记录");
            alert(ids);
    } else {
        alert("没有选中记录");
    }
}
function checkForm() {
    var userId = document.getElementById("userId").value;
    var password = document.getElementById("password").value;
    var OKPassword = document.getElementById("OKPassword").value;

    var reg = /^[0-9][0-9][0-9]$/;
    if (userId == "") {
        alert("用户ID不能为空");
        return false;
    } else if (!userId.match(reg)) {
        alert("用户ID格式错误");
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

function addCourseRow() {
    var table = document.getElementById("TableBody");
    var row = table.insertRow();
    row.innerHTML =
        "<td><input type='text' name='courseId' value=''></td>" +
        "<td><input type='text' name='courseName' value=''></td>" +
        "<td><input type='text' name='courseCode' value=''></td>" +
        "<td><input type='text' name='deptId' value=''></td>" +
        "<td><input type='text' name='credit' value=''></td>" +
        "<td><input type='text' name='courseHours' value=''></td>" +
        "<td><input type='text' name='courseType' value=''></td>" +
        "<td><input type='text' name='teacherName' value=''></td>" +
        "<td><input type='text' name='semester' value=''></td>" +
        "<td><input type='text' name='courseDesc' value=''></td>";
}

function AJAX(InsertUrl,path,Object)
{
    $.ajax({
        type: "POST",
        url: InsertUrl, // 服务器接口地址
        data: JSON.stringify(Object), // 将 JavaScript 对象数组转换为 JSON 字符串
        contentType: "application/json", // 告知服务器发送的数据是 JSON 格式
        dataType: "json", // 明确指定返回数据类型为JSON，确保jQuery正确解析

        success: function (data, textStatus, xhr) {
            console.log("响应数据:", data);
            console.log("响应数据类型:", typeof data);
            console.log("响应状态:", textStatus);
            console.log("XHR对象:", xhr);

            // 处理返回的数据，可能是字符串或对象
            var responseData = data;
            if (typeof data === 'string') {
                try {
                    responseData = JSON.parse(data);
                    console.log("解析后的数据:", responseData);
                } catch (e) {
                    console.error("JSON解析失败:", e);
                    console.log("原始响应文本:", xhr.responseText);
                    // 如果解析失败，尝试从responseText解析
                    try {
                        responseData = JSON.parse(xhr.responseText);
                    } catch (e2) {
                        console.error("从responseText解析也失败:", e2);
                        alert("服务器响应格式错误，请检查控制台！");
                        return;
                    }
                }
            }

            // 检查返回的状态
            if (responseData && (responseData.status === "success" || responseData.status === true)) {
                alert("提交成功！");
                // 添加时间戳避免缓存，确保跳转后重新加载数据
                var separator = path.indexOf('?') > -1 ? '&' : '?';
                // 使用window.location.replace确保不会在历史记录中留下当前页面
                window.location.replace(path + separator + '_t=' + new Date().getTime());
            } else {
                // 如果服务器返回 status 为 false 或其他非成功状态
                let message = "提交操作未成功";
                if (responseData && responseData.message) { // 如果服务器返回了具体的错误信息
                    message += "：" + responseData.message;
                }
                alert(message);
                console.log("服务器响应指示操作未成功:", responseData);
            }
        },
        error: function (xhr, status, error) {
            //hideLoading(); // <--- 确保 hideLoading 函数已经定义且可用
            alert("提交失败，请联系系统管理员！");
            console.error("AJAX请求状态: ", status);
            console.error("抛出的错误信息: ", error);
            console.error("服务器响应内容 (XHR):", xhr.responseText); // 这在调试时此处会很有用
        }
    });
}

async function insertItem(event) { // <--- 1. 添加 event 参数
    if (event) { // 检查 event 是否存在，以防表单以传统方式提交
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
        // 确保所有字段都有值，才添加到数组中
        if (item.iid && item.iname && item.iflag) {
            items.push(item);
        } else if (item.iid || item.iname || item.iflag) { // 如果这一行有任何字段被写了，但不是全部填写，则忽略该行并显示警告
            console.warn("这一行数据不完整，已忽略该行:", item);
            alert("这一行数据不完整，已忽略该行。"+item.iid+" "+item.iname+" "+item.iflag);
        }
    }
    if (items.length === 0) {
        alert("没有有效的数据可以提交，请至少填写一行。");
        return;
    }
    AJAX('../item/insert','../item/queryAll',items);
}

async function insertStu(event) { // <--- 1. 添加 event 参数
    if (event) { // 检查 event 是否存在，以防表单以传统方式提交
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
        // 确保所有字段都有值，才添加到数组中
        if (stu.sid && stu.sname && stu.spassword && stu.sright && stu.stflag) {
            students.push(stu);
        } else if (stu.sid || stu.sname || stu.spassword || stu.sright || stu.stflag) { // 如果这一行有任何字段被写了，但不是全部填写，则忽略该行并显示警告
            console.warn("这一行数据不完整，已忽略该行:", stu);
            alert("这一行数据不完整，已忽略该行。"+stu.sid+" "+stu.sname+" "+stu.spassword+" "+stu.sright+" "+stu.stflag);
        }
    }
    if (students.length === 0) {
        alert("没有有效的数据可以提交，请至少填写一行。");
        return;
    }
    AJAX('../student/insert','../student/queryAll',students);
}

async function insertJob(event) { // <--- 1. 添加 event 参数
    if (event) { // 检查 event 是否存在，以防表单以传统方式提交
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
        // 确保所有字段都有值，才添加到数组中
        if (job.sid && job.iid && job.job  && job.scflag) {
            jobs.push(job);
        } else if (job.sid || job.iid || job.job  || job.scflag) { // 如果这一行有任何字段被写了，但不是全部填写，则忽略该行并显示警告
            console.warn("这一行数据不完整，已忽略该行:", job);
            alert("这一行数据不完整，已忽略该行。"+job.sid+" "+job.iid+" "+job.job+" "+job.scflag);
        }
    }
    if (jobs.length === 0) {
        alert("没有有效的数据可以提交，请至少填写一行。");
        return;
    }
    AJAX('../job/insert','../job/queryAll',jobs);
}

async function insertCourse(event) {
    if (event) {
        event.preventDefault();
    }

    // 读取表格，相当于2维数组
    let rows = document.querySelectorAll("#TableBody tr");
    let courses = [];

    for (let row of rows) {
        // 获取每一行输入框的值，1维数组
        let inputs = row.querySelectorAll("input");
        // 创建一个对象，将输入框的值保存起来
        let course = {
            courseId: inputs[0].value.trim(),
            courseName: inputs[1].value.trim(),
            courseCode: inputs[2].value.trim(),
            deptId: inputs[3].value.trim(),
            credit: inputs[4].value.trim(),
            courseHours: inputs[5].value.trim(),
            courseType: inputs[6].value.trim(),
            teacherName: inputs[7].value.trim(),
            semester: inputs[8].value.trim(),
            courseDesc: inputs[9].value.trim()
        };
        // 确保所有必填字段都有值，才添加到数组中
        if (course.courseId && course.courseName && course.courseCode) {
            courses.push(course);
        } else if (course.courseId || course.courseName || course.courseCode) {
            console.warn("这一行数据不完整，已忽略该行:", course);
            alert("这一行数据不完整，已忽略该行。课程ID、课程名称和课程编码为必填项。");
        }
    }
    if (courses.length === 0) {
        alert("没有有效的数据可以提交，请至少填写一行。");
        return;
    }
    AJAX('../course/insert','../course/queryAll',courses);
}

function addDepartmentRow() {
    var table = document.getElementById("TableBody");
    var row = table.insertRow();
    row.innerHTML =
        "<td><input type='text' name='deptId' value=''></td>" +
        "<td><input type='text' name='deptName' value=''></td>" +
        "<td><input type='text' name='deptCode' value=''></td>" +
        "<td><input type='text' name='deptHead' value=''></td>" +
        "<td><input type='text' name='deptPhone' value=''></td>" +
        "<td><input type='text' name='deptEmail' value=''></td>" +
        "<td><input type='text' name='deptDesc' value=''></td>";
}

async function insertDepartment(event) {
    if (event) {
        event.preventDefault();
    }
    let rows = document.querySelectorAll("#TableBody tr");
    let departments = [];
    for (let row of rows) {
        let inputs = row.querySelectorAll("input");
        let dept = {
            deptId: inputs[0].value.trim(),
            deptName: inputs[1].value.trim(),
            deptCode: inputs[2].value.trim(),
            deptHead: inputs[3].value.trim(),
            deptPhone: inputs[4].value.trim(),
            deptEmail: inputs[5].value.trim(),
            deptDesc: inputs[6].value.trim()
        };
        if (dept.deptId && dept.deptName && dept.deptCode) {
            departments.push(dept);
        } else if (dept.deptId || dept.deptName || dept.deptCode) {
            console.warn("这一行数据不完整，已忽略该行:", dept);
            alert("这一行数据不完整，已忽略该行。院系ID、院系名称和院系编码为必填项。");
        }
    }
    if (departments.length === 0) {
        alert("没有有效的数据可以提交，请至少填写一行。");
        return;
    }
    AJAX('../department/insert','../department/queryAll',departments);
}

function addScoreRow() {
    var table = document.getElementById("TableBody");
    var row = table.insertRow();
    row.innerHTML =
        "<td><input type='text' name='scoreId' value=''></td>" +
        "<td><input type='text' name='studentId' value=''></td>" +
        "<td><input type='text' name='courseId' value=''></td>" +
        "<td><input type='text' name='score' value=''></td>" +
        "<td><input type='text' name='semester' value=''></td>" +
        "<td><input type='text' name='examType' value=''></td>";
}

async function insertScore(event) {
    if (event) {
        event.preventDefault();
    }
    let rows = document.querySelectorAll("#TableBody tr");
    let scores = [];
    for (let row of rows) {
        let inputs = row.querySelectorAll("input");
        let score = {
            scoreId: inputs[0].value.trim(),
            studentId: inputs[1].value.trim(),
            courseId: inputs[2].value.trim(),
            score: inputs[3].value.trim(),
            semester: inputs[4].value.trim(),
            examType: inputs[5].value.trim()
        };
        if (score.scoreId && score.studentId && score.courseId) {
            scores.push(score);
        } else if (score.scoreId || score.studentId || score.courseId) {
            console.warn("这一行数据不完整，已忽略该行:", score);
            alert("这一行数据不完整，已忽略该行。成绩ID、学生ID和课程ID为必填项。");
        }
    }
    if (scores.length === 0) {
        alert("没有有效的数据可以提交，请至少填写一行。");
        return;
    }
    AJAX('../score/insert','../score/queryAll',scores);
}

function addTeacherRow() {
    var table = document.getElementById("TableBody");
    var row = table.insertRow();
    row.innerHTML =
        "<td><input type='text' name='teacherId' value=''></td>" +
        "<td><input type='text' name='teacherName' value=''></td>" +
        "<td><input type='text' name='gender' value=''></td>" +
        "<td><input type='text' name='age' value=''></td>" +
        "<td><input type='text' name='phone' value=''></td>" +
        "<td><input type='text' name='email' value=''></td>" +
        "<td><input type='text' name='deptId' value=''></td>" +
        "<td><input type='text' name='title' value=''></td>";
}

async function insertTeacher(event) {
    if (event) {
        event.preventDefault();
    }
    let rows = document.querySelectorAll("#TableBody tr");
    let teachers = [];
    for (let row of rows) {
        let inputs = row.querySelectorAll("input");
        let teacher = {
            teacherId: inputs[0].value.trim(),
            teacherName: inputs[1].value.trim(),
            gender: inputs[2].value.trim(),
            age: inputs[3].value.trim(),
            phone: inputs[4].value.trim(),
            email: inputs[5].value.trim(),
            deptId: inputs[6].value.trim(),
            title: inputs[7].value.trim()
        };
        if (teacher.teacherId && teacher.teacherName) {
            teachers.push(teacher);
        } else if (teacher.teacherId || teacher.teacherName) {
            console.warn("这一行数据不完整，已忽略该行:", teacher);
            alert("这一行数据不完整，已忽略该行。教师ID和教师姓名为必填项。");
        }
    }
    if (teachers.length === 0) {
        alert("没有有效的数据可以提交，请至少填写一行。");
        return;
    }
    AJAX('../teacher/insert','../teacher/queryAll',teachers);
}

function addTimeTableRow() {
    var table = document.getElementById("TableBody");
    var row = table.insertRow();
    row.innerHTML =
        "<td><input type='text' name='timetableId' value=''></td>" +
        "<td><input type='text' name='courseId' value=''></td>" +
        "<td><input type='text' name='teacherId' value=''></td>" +
        "<td><input type='text' name='classroomId' value=''></td>" +
        "<td><input type='text' name='dayOfWeek' value=''></td>" +
        "<td><input type='text' name='timeSlot' value=''></td>" +
        "<td><input type='text' name='semester' value=''></td>";
}

async function insertTimeTable(event) {
    if (event) {
        event.preventDefault();
    }
    let rows = document.querySelectorAll("#TableBody tr");
    let timeTables = [];
    for (let row of rows) {
        let inputs = row.querySelectorAll("input");
        let timeTable = {
            timetableId: inputs[0].value.trim(),
            courseId: inputs[1].value.trim(),
            teacherId: inputs[2].value.trim(),
            classroomId: inputs[3].value.trim(),
            dayOfWeek: inputs[4].value.trim(),
            timeSlot: inputs[5].value.trim(),
            semester: inputs[6].value.trim()
        };
        if (timeTable.timetableId && timeTable.courseId && timeTable.teacherId) {
            timeTables.push(timeTable);
        } else if (timeTable.timetableId || timeTable.courseId || timeTable.teacherId) {
            console.warn("这一行数据不完整，已忽略该行:", timeTable);
            alert("这一行数据不完整，已忽略该行。课表ID、课程ID和教师ID为必填项。");
        }
    }
    if (timeTables.length === 0) {
        alert("没有有效的数据可以提交，请至少填写一行。");
        return;
    }
    AJAX('../timetable/insert','../timetable/queryAll',timeTables);
}

// 单ID批量删除函数（用于teacher、item等只有一个主键的表）
function delcheckSingle(path, id) {
    alert("开始删除操作");
    var ids = new Array();
    var flag = false;
    let checks = document.getElementsByName("check");
    let count = 0;
    for (let i = 0; i < checks.length; i++) {
        if (checks[i].checked) {
            let val = checks[i].value;
            ids.push(val);
            count++;
            flag = true;
        }
    }
    if (flag) {
        if (confirm("确定要删除吗？")) {
            location.href = path + "?flag=all&" + id + "=" + encodeURIComponent(ids.join(","));
            alert("删除了" + count + "条记录");
        }
    } else {
        alert("没有选中记录");
    }
}