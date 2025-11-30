<%@ page import="cn.edu.lingnan.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>课程管理</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———课程信息</h1>
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
        <th>课程ID</th>
        <th>课程名称</th>
        <th>课程编码</th>
        <th>所属院系ID</th>
        <th>学分</th>
        <th>学时</th>
        <th>课程类型</th>
        <th>任课教师</th>
        <th>学期</th>
        <th>课程描述</th>
        <th>状态标志</th>
        <th><input type="submit" value="批量删除" onclick="delcheckSingle('${pageContext.request.contextPath}/course/delete','courseId');"></th>
    </tr>
    <%
        Object allCourse = session.getAttribute("allCourse");
        if(allCourse == null) {
            allCourse = request.getAttribute("allCourse");
        }
    %>
    <%
        if(allCourse != null){
            List<Course> list = (List<Course>) allCourse;
            if(list != null)
            {
                for(Course course : list) {      %>
    <tr>
        <td><input type="checkbox" name="check" value="<%=course.getCourseId()%>"></td>
        <td><%=course.getCourseId() != null ? course.getCourseId() : ""%></td>
        <td><%=course.getCourseName() != null ? course.getCourseName() : ""%></td>
        <td><%=course.getCourseCode() != null ? course.getCourseCode() : ""%></td>
        <td><%=course.getDeptId() != null ? course.getDeptId() : ""%></td>
        <td><%=course.getCredit() != null ? course.getCredit() : ""%></td>
        <td><%=course.getCourseHours() != null ? course.getCourseHours() : ""%></td>
        <td><%=course.getCourseType() != null ? course.getCourseType() : ""%></td>
        <td><%=course.getTeacherName() != null ? course.getTeacherName() : ""%></td>
        <td><%=course.getSemester() != null ? course.getSemester() : ""%></td>
        <td><%=course.getCourseDesc() != null ? (course.getCourseDesc().length() > 20 ? course.getCourseDesc().substring(0, 20) + "..." : course.getCourseDesc()) : ""%></td>
        <td><%=course.getCflag() != null ? course.getCflag() : 0%></td>
        <td>
            <a href="updateCourse.jsp?courseId=<%=course.getCourseId()%>">修改</a> &nbsp; | &nbsp;
            <a href="${pageContext.request.contextPath}/course/delete?courseId=<%=course.getCourseId()%>"
               onclick="return confirm('确定要删除吗？')">删除</a>
        </td>
    </tr>
    <%
                }
            }
        }
    %>
</table>
<a href="addCourse.jsp" class="add-item-btn">+</a>
</body>

