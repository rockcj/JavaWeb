<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
    <title>添加项目信息</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———添加项目信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="../login">退出</a>
<a href="../loginout">用户注销</a>
<a href="../student/queryAll">查询学生信息</a>
<a href="../item/queryAll">查询项目信息</a>
<a href="../job/queryAll">查询职位信息</a>
<a href="../course/queryAll">查询课程信息</a>
<a href="../department/queryAll">查询院系信息</a>
<a href="../score/queryAll">查询成绩信息</a>
<a href="../teacher/queryAll">查询教师信息</a>
<a href="../timetable/queryAll">查询课程表信息</a>
<hr>
<form >
    <table id="itemTable">
        <thead>
            <tr>
                <th>项目ID</th>
                <th>项目名称</th>
                <th>项目权限</th>
            </tr>
        </thead>
        <tbody id="TableBody">
        </tbody>
    </table>
    <button type="button" class="add-row-btn" onclick="addItemRow()">+ 添加空白行</button>
    <br><br>
    <button type="submit" class="submit-btn" onclick="insertItem(event)">提交</button>
</form>
<!-- 将脚本移到页面底部，避免阻塞页面渲染，使用更快的CDN -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="../js/check.js"></script>
</body>
</html>