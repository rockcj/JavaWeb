
function allcheck(checkbox) {
    //alert("ȫѡ");
    let checks = document.getElementsByName("check");
    for (let i = 0; i < checks.length; i++) {
        checks[i].checked = checkbox.checked; //ʹ�����Ĺ�ѡ�����ͷ��ȫѡ�򱣳�һ��
    }
}

function delcheck(path, id, id2) {
    alert("����ɾ������");
    //�ȼ�¼����������������ˣ�����ɾ��
    var ids = new Array();
    var ids2 = new Array();
    var flag = false;
    let checks = document.getElementsByName("check");
    let count = 0;
    for (let i = 0; i < checks.length; i++) {
        if (checks[i].checked) //���checks[i]��ѡ��
        {
            let val = checks[i].value.split(",");
            ids.push(val[0]);  // sid
            ids2.push(val[1]);  // iid
            count++;
            flag = true;
        }
    }
    if (flag) {
        if (confirm("��ȷ��ɾ����")) {
            location.href = path + "?flag=all&" + id + "="
                + encodeURIComponent(ids.join(",")) + "&" + id2 + "="
                + encodeURIComponent(ids2.join(","));
            alert("ɾ����" + count + "������");
            alert(ids);
        }
    } else {
        alert("û��ѡ������");
    }
}
function OKcheck() {
    var ids = new Array();
    var flag = false;
    let checks = document.getElementsByName("check");
    let count = 0;
    for (let i = 0; i < checks.length; i++) {
        if (checks[i].checked) //���checks[i]��ѡ��
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
            alert("ͨ��" + count + "������");
            alert(ids);
    } else {
        alert("û��ѡ������");
    }
}
function checkForm() {
    var userId = document.getElementById("userId").value;
    var password = document.getElementById("password").value;
    var OKPassword = document.getElementById("OKPassword").value;

    var reg = /^[0-9][0-9][0-9]$/;
    if (userId == "") {
        alert("�û�������Ϊ��");
        return false;
    } else if (!userId.match(reg)) {
        alert("�û�����ʽ����");
        return false;
    }

    if (password == "") {
        alert("���벻��Ϊ��");
        return false;
    }

    if (password != OKPassword) {
        alert("������������벻һ��");
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
        url: InsertUrl, // ��˽ӿڵ�ַ
        data: JSON.stringify(Object), // �� JavaScript ��������ת��Ϊ JSON �ַ���
        contentType: "application/json", // ��֪�������������� JSON ��ʽ

        success: function (data) {
            console.log("��Ӧ����:", data);
            console.log("666"); // ��ĵ�����Ϣ

            if (data.status) { // ȷ�� data ����������� status ����
                alert("�ύ�ɹ���");
                location.href = path; // �ύ�ɹ���ת��/queryItemAllҳ��
            } else {
                // ������˷��� status Ϊ false ��������Ԥ�ڳɹ�״̬
                let message = "�ύ����δ�ɹ�";
                if (data && data.message) { // �����˷����˾���Ĵ�����Ϣ
                    message += "��" + data.message;
                }
                alert(message);
                console.log("�����Ӧָʾ����δ�ɹ�:", data);
            }
        },
        error: function (xhr, status, error) {
            //hideLoading(); // <--- ȷ�� hideLoading �����Ѷ����ҿ���
            alert("�ύʧ�ܣ������������ϵ����Ա��");
            console.error("AJAX�������״̬: ", status);
            console.error("�׳��Ĵ�������: ", error);
            console.error("��������Ӧ�� (XHR):", xhr.responseText); // ����ڵ��Ժ�˴���ǳ�����
        }
    });
}

async function insertItem(event) { // <--- 1. ���� event ����
    if (event) { // ��� event �Ƿ��룬�Է�������������ʽ����
        event.preventDefault(); // <--- 2. ��ֹ������Ĭ���ύ��Ϊ
    }

    //��ȡ�����൱��2ά����
    let rows = document.querySelectorAll("#TableBody tr");
    let items = [];

    for (let row of rows) {
        //��ȡÿһ��������ֵ��1ά����
        let inputs = row.querySelectorAll("input");
        //  ����һ�����󣬽�������ֵ��������
        let item = {
            iid: inputs[0].value.trim(),
            iname: inputs[1].value.trim(),
            iflag: inputs[2].value.trim()
        };
        // ȷ�������ֶζ���ֵ�����ӵ�������
        if (item.iid && item.iname && item.iflag) {
            items.push(item);
        } else if (item.iid || item.iname || item.iflag) { // ����������κ�һ���������д�ˣ�������ȫ�������Ը�����ʾ�����
            console.warn("��������һ�����ݲ��������Ѻ��Ը���:", item);
            alert("��������һ�����ݲ��������Ѻ��Ը��С�"+item.iid+" "+item.iname+" "+item.iflag);
        }
    }
    if (items.length === 0) {
        alert("û����Ч�����ݿ����ύ��������������дһ�С�");
        return;
    }
    AJAX('../item/insert','../item/queryAll',items);
}

async function insertStu(event) { // <--- 1. ���� event ����
    if (event) { // ��� event �Ƿ��룬�Է�������������ʽ����
        event.preventDefault(); // <--- 2. ��ֹ������Ĭ���ύ��Ϊ
    }

    //��ȡ�����൱��2ά����
    let rows = document.querySelectorAll("#TableBody tr");
    let students = [];

    for (let row of rows) {
        //��ȡÿһ��������ֵ��1ά����
        let inputs = row.querySelectorAll("input");
        //  ����һ�����󣬽�������ֵ��������
        let stu = {
            sid: inputs[0].value.trim(),
            sname: inputs[1].value.trim(),
            spassword: inputs[2].value.trim(),
            sright: inputs[3].value.trim(),
            stflag: inputs[4].value.trim()
        };
        // ȷ�������ֶζ���ֵ�����ӵ�������
        if (stu.sid && stu.sname && stu.spassword && stu.sright && stu.stflag) {
            students.push(stu);
        } else if (stu.sid || stu.sname || stu.spassword || stu.sright || stu.stflag) { // ����������κ�һ���������д�ˣ�������ȫ�������Ը�����ʾ�����
            console.warn("��������һ�����ݲ��������Ѻ��Ը���:", stu);
            alert("��������һ�����ݲ��������Ѻ��Ը��С�"+stu.sid+" "+stu.sname+" "+stu.spassword+" "+stu.sright+" "+stu.stflag);
        }
    }
    if (students.length === 0) {
        alert("û����Ч�����ݿ����ύ��������������дһ�С�");
        return;
    }
    AJAX('../student/insert','../student/queryAll',students);
}

async function insertJob(event) { // <--- 1. ���� event ����
    if (event) { // ��� event �Ƿ��룬�Է�������������ʽ����
        event.preventDefault(); // <--- 2. ��ֹ������Ĭ���ύ��Ϊ
    }

    //��ȡ�����൱��2ά����
    let rows = document.querySelectorAll("#TableBody tr");
    let jobs = [];

    for (let row of rows) {
        //��ȡÿһ��������ֵ��1ά����
        let inputs = row.querySelectorAll("input");
        //  ����һ�����󣬽�������ֵ��������
        let job = {
            sid: inputs[0].value.trim(),
            iid: inputs[1].value.trim(),
            job: inputs[2].value.trim(),
            scflag: inputs[3].value.trim()
        };
        // ȷ�������ֶζ���ֵ�����ӵ�������
        if (job.sid && job.iid && job.job  && job.scflag) {
            jobs.push(job);
        } else if (job.sid || job.iid || job.job  || job.scflag) { // ����������κ�һ���������д�ˣ�������ȫ�������Ը�����ʾ�����
            console.warn("��������һ�����ݲ��������Ѻ��Ը���:", job);
            alert("��������һ�����ݲ��������Ѻ��Ը��С�"+job.sid+" "+job.iid+" "+job.job+" "+job.scflag);
        }
    }
    if (jobs.length === 0) {
        alert("û����Ч�����ݿ����ύ��������������дһ�С�");
        return;
    }
    AJAX('../job/insert','../job/queryAll',jobs);
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