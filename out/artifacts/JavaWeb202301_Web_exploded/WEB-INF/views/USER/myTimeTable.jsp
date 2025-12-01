<%@ page import="cn.edu.lingnan.pojo.TimeTable" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>我的课程表</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———我的课程表</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="${pageContext.request.contextPath}/login">退出</a>
<a href="${pageContext.request.contextPath}/loginout">用户注销</a>
<a href="${pageContext.request.contextPath}/oneStu">查询个人信息</a>
<a href="${pageContext.request.contextPath}/details">查询详细信息</a>
<a href="${pageContext.request.contextPath}/user/myScores">查询我的成绩</a>
<a href="${pageContext.request.contextPath}/user/myTimeTable">查询我的课程表</a>
<hr>
<table>
    <tr>
        <th>课表ID</th>
        <th>课程ID</th>
        <th>教师ID</th>
        <th>教室ID</th>
        <th>星期几</th>
        <th>时间段</th>
        <th>学期</th>
        <th>状态标志</th>
    </tr>
    <%
        Object myTimeTable = session.getAttribute("myTimeTable");
        if(myTimeTable == null) {
            myTimeTable = request.getAttribute("myTimeTable");
        }
    %>
    <%
        if(myTimeTable != null){
            List<TimeTable> list = (List<TimeTable>) myTimeTable;
            if(list != null && list.size() > 0)
            {
                for(TimeTable timeTable : list) {      %>
    <tr>
        <td><%=timeTable.getTimetableId() != null ? timeTable.getTimetableId() : ""%></td>
        <td><%=timeTable.getCourseId() != null ? timeTable.getCourseId() : ""%></td>
        <td><%=timeTable.getTeacherId() != null ? timeTable.getTeacherId() : ""%></td>
        <td><%=timeTable.getClassroomId() != null ? timeTable.getClassroomId() : ""%></td>
        <td><%=timeTable.getDayOfWeek() != null ? timeTable.getDayOfWeek() : ""%></td>
        <td><%=timeTable.getTimeSlot() != null ? timeTable.getTimeSlot() : ""%></td>
        <td><%=timeTable.getSemester() != null ? timeTable.getSemester() : ""%></td>
        <td><%=timeTable.getFlag() != null ? timeTable.getFlag() : 0%></td>
    </tr>
    <%
                }
            } else {
    %>
    <tr>
        <td colspan="8" style="text-align: center;">暂无课程表信息</td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="8" style="text-align: center;">暂无课程表信息</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

