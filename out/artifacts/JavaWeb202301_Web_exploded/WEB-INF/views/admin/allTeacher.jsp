<%@ page import="cn.edu.lingnan.pojo.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>教师管理</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———教师信息</h1>
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
<table>
    <tr>
        <th><input type="checkbox" onclick="allcheck(this);"></th>
        <th>教师ID</th>
        <th>教师姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>电话</th>
        <th>邮箱</th>
        <th>所属院系ID</th>
        <th>职称</th>
        <th>状态标志</th>
        <th><input type="submit" value="批量删除" onclick="delcheckSingle('${pageContext.request.contextPath}/teacher/delete','teacherId');"></th>
    </tr>
    <%
        Object allTeacher = session.getAttribute("allTeacher");
        if(allTeacher == null) {
            allTeacher = request.getAttribute("allTeacher");
        }
    %>
    <%
        if(allTeacher != null){
            List<Teacher> list = (List<Teacher>) allTeacher;
            if(list != null)
            {
                for(Teacher teacher : list) {      %>
    <tr>
        <td><input type="checkbox" name="check" value="<%=teacher.getTeacherId() != null ? teacher.getTeacherId() : ""%>"></td>
        <td><%=teacher.getTeacherId() != null ? teacher.getTeacherId() : ""%></td>
        <td><%=teacher.getTeacherName() != null ? teacher.getTeacherName() : ""%></td>
        <td><%=teacher.getGender() != null ? teacher.getGender() : ""%></td>
        <td><%=teacher.getAge() != null ? teacher.getAge() : ""%></td>
        <td><%=teacher.getPhone() != null ? teacher.getPhone() : ""%></td>
        <td><%=teacher.getEmail() != null ? teacher.getEmail() : ""%></td>
        <td><%=teacher.getDeptId() != null ? teacher.getDeptId() : ""%></td>
        <td><%=teacher.getTitle() != null ? teacher.getTitle() : ""%></td>
        <td><%=teacher.getFlag() != null ? teacher.getFlag() : 0%></td>
        <td>
            <a href="${pageContext.request.contextPath}/teacher/showUpdate?teacherId=<%=teacher.getTeacherId()%>">修改</a> &nbsp; | &nbsp;
            <a href="${pageContext.request.contextPath}/teacher/delete?teacherId=<%=teacher.getTeacherId()%>"
               onclick="return confirm('确定要删除吗？')">删除</a>
        </td>
    </tr>
    <%
                }
            }
        }
    %>
</table>
<a href="${pageContext.request.contextPath}/teacher/addTeacher" class="add-item-btn">+</a>
</body>

