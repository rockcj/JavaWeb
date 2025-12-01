<%@ page import="cn.edu.lingnan.pojo.TimeTable" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>编辑课程表</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———编辑课程表信息</h1>
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

<form action="${pageContext.request.contextPath}/timetable/update" method="post">
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
            <th>操作选择</th>
        </tr>
        <%
            Object allTimeTable = session.getAttribute("allTimeTable");
            if(allTimeTable == null) {
                allTimeTable = request.getAttribute("allTimeTable");
            }
            String timetableId = request.getParameter("timetableId");
        %>
        <%
            if(allTimeTable != null && timetableId != null){
                List<TimeTable> list = (List<TimeTable>) allTimeTable;
                if(list != null)
                {
                    for(TimeTable timeTable : list) {
                        if(timeTable.getTimetableId() != null && timeTable.getTimetableId().equals(timetableId)){
        %>
        <tr>
            <td>
                <input type="hidden" name="timetableId" value="<%=timeTable.getTimetableId() != null ? timeTable.getTimetableId() : ""%>">
                <%=timeTable.getTimetableId() != null ? timeTable.getTimetableId() : ""%>
            </td>
            <td><input type="text" name="courseId" value="<%=timeTable.getCourseId() != null ? timeTable.getCourseId() : ""%>"></td>
            <td><input type="text" name="teacherId" value="<%=timeTable.getTeacherId() != null ? timeTable.getTeacherId() : ""%>"></td>
            <td><input type="text" name="classroomId" value="<%=timeTable.getClassroomId() != null ? timeTable.getClassroomId() : ""%>"></td>
            <td><input type="text" name="dayOfWeek" value="<%=timeTable.getDayOfWeek() != null ? timeTable.getDayOfWeek() : ""%>"></td>
            <td><input type="text" name="timeSlot" value="<%=timeTable.getTimeSlot() != null ? timeTable.getTimeSlot() : ""%>"></td>
            <td><input type="text" name="semester" value="<%=timeTable.getSemester() != null ? timeTable.getSemester() : ""%>"></td>
            <td><input type="text" name="flag" value="<%=timeTable.getFlag() != null ? timeTable.getFlag() : 0%>"></td>
            <td>
                <input type="submit" value="确认修改">
                <a href="${pageContext.request.contextPath}/timetable/queryAll">取消</a>
            </td>
        </tr>
        <%
                        }
                    }
                }
            } else {
        %>
        <tr>
            <td colspan="9" style="text-align: center; color: red;">
                未找到要编辑的课程表信息，请返回列表页面。
                <a href="${pageContext.request.contextPath}/timetable/queryAll">返回列表</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>

