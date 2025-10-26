<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.7.1/jquery.js"></script>
    <script src="../js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
    <title>添加项目信息</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———添加项目信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="/login">退出</a>
<a href="/loginout">用户注销</a>
<a href="/queryStuAll">查询学生信息</a>
<a href="/queryItemAll">查询项目信息</a>
<a href="/queryJobAll">查询职位信息</a>
<a href="/All">查询详细信息</a>
<hr>
<form >
    <table id="itemTable">
        <thead>
        <tr>
            <th>学生学号</th>
            <th>学生姓名</th>
            <th>学生密码</th>
            <th>学生权限</th>
            <th>备用信息</th>
        </tr>
        </thead>
        <tbody id="TableBody">
        </tbody>
    </table>
    <button type="button" class="add-row-btn" onclick="addStuRow()">+ 添加空白行</button>
    <br><br>
    <button type="submit" class="submit-btn" onclick="insertStu(event)">提交</button>
</form>