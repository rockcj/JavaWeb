<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
    <title>添加课程表信息</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———添加课程表信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="${pageContext.request.contextPath}/login">退出</a>
<a href="${pageContext.request.contextPath}/loginout">用户注销</a>
<a href="${pageContext.request.contextPath}/student/queryAll">查询学生信息</a>
<a href="${pageContext.request.contextPath}/item/queryAll">查询项目信息</a>
<a href="${pageContext.request.contextPath}/job/queryAll">查询职位信息</a>
<a href="${pageContext.request.contextPath}/course/queryAll">查询课程信息</a>
<a href="${pageContext.request.contextPath}/department/queryAll">查询院系信息</a>
<a href="${pageContext.request.contextPath}/score/queryAll">查询成绩信息</a>
<a href="${pageContext.request.contextPath}/teacher/queryAll">查询教师信息</a>
<a href="${pageContext.request.contextPath}/timetable/queryAll">查询课程表信息</a>
<hr>
<form >
    <table id="itemTable">
        <thead>
        <tr>
            <th>课表ID</th>
            <th>课程ID</th>
            <th>教师ID</th>
            <th>教室ID</th>
            <th>星期几</th>
            <th>时间段</th>
            <th>学期</th>
        </tr>
        </thead>
        <tbody id="TableBody">
        </tbody>
    </table>
    <button type="button" class="add-row-btn" onclick="addTimeTableRow()">+ 添加空白行</button>
    <br><br>
    <button type="submit" class="submit-btn" onclick="insertTimeTable(event)">提交</button>
</form>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="../js/check.js"></script>
</body>
</html>

