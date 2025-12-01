<%@ page import="cn.edu.lingnan.pojo.TimeTable" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>课程表管理</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———课程表信息</h1>
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
        <th>课表ID</th>
        <th>课程ID</th>
        <th>教师ID</th>
        <th>教室ID</th>
        <th>星期几</th>
        <th>时间段</th>
        <th>学期</th>
        <th>状态标志</th>
        <th><input type="submit" value="批量删除" onclick="delcheckSingle('${pageContext.request.contextPath}/timetable/delete','timetableId');"></th>
    </tr>
    <%
        Object allTimeTable = session.getAttribute("allTimeTable");
        if(allTimeTable == null) {
            allTimeTable = request.getAttribute("allTimeTable");
        }
    %>
    <%
        if(allTimeTable != null){
            List<TimeTable> list = (List<TimeTable>) allTimeTable;
            if(list != null)
            {
                for(TimeTable timeTable : list) {      %>
    <tr>
        <td><input type="checkbox" name="check" value="<%=timeTable.getTimetableId() != null ? timeTable.getTimetableId() : ""%>"></td>
        <td><%=timeTable.getTimetableId() != null ? timeTable.getTimetableId() : ""%></td>
        <td><%=timeTable.getCourseId() != null ? timeTable.getCourseId() : ""%></td>
        <td><%=timeTable.getTeacherId() != null ? timeTable.getTeacherId() : ""%></td>
        <td><%=timeTable.getClassroomId() != null ? timeTable.getClassroomId() : ""%></td>
        <td><%=timeTable.getDayOfWeek() != null ? timeTable.getDayOfWeek() : ""%></td>
        <td><%=timeTable.getTimeSlot() != null ? timeTable.getTimeSlot() : ""%></td>
        <td><%=timeTable.getSemester() != null ? timeTable.getSemester() : ""%></td>
        <td><%=timeTable.getFlag() != null ? timeTable.getFlag() : 0%></td>
        <td>
            <a href="${pageContext.request.contextPath}/timetable/showUpdate?timetableId=<%=timeTable.getTimetableId()%>">修改</a> &nbsp; | &nbsp;
            <a href="${pageContext.request.contextPath}/timetable/delete?timetableId=<%=timeTable.getTimetableId()%>"
               onclick="return confirm('确定要删除吗？')">删除</a>
        </td>
    </tr>
    <%
                }
            }
        }
    %>
</table>
<a href="addTimeTable.jsp" class="add-item-btn">+</a>
</body>

