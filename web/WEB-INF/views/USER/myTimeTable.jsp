<%@ page import="cn.edu.lingnan.pojo.TimeTable" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>我的课表 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="/USER/sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>我的课程表</h1>
            <div style="color: var(--text-secondary);">查看您的上课时间安排</div>
        </div>

        <div class="table-container">
            <table class="tech-table">
                <thead>
                    <tr>
                        <th>课程ID</th>
                        <th>教师工号</th>
                        <th>教室</th>
                        <th>星期</th>
                        <th>节次</th>
                        <th>学期</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Object myTimeTable = session.getAttribute("myTimeTable");
                        if(myTimeTable == null) {
                            myTimeTable = request.getAttribute("myTimeTable");
                        }
                        if(myTimeTable != null){
                            List<TimeTable> list = (List<TimeTable>) myTimeTable;
                            if(list != null && list.size() > 0)
                            {
                                for(TimeTable timeTable : list) {      %>
                    <tr>
                        <td><span style="color: var(--accent-color); font-weight: bold;"><%=timeTable.getCourseId() != null ? timeTable.getCourseId() : ""%></span></td>
                        <td><%=timeTable.getTeacherId() != null ? timeTable.getTeacherId() : ""%></td>
                        <td><%=timeTable.getClassroomId() != null ? timeTable.getClassroomId() : ""%></td>
                        <td><%=timeTable.getDayOfWeek() != null ? timeTable.getDayOfWeek() : ""%></td>
                        <td><%=timeTable.getTimeSlot() != null ? timeTable.getTimeSlot() : ""%></td>
                        <td><%=timeTable.getSemester() != null ? timeTable.getSemester() : ""%></td>
                    </tr>
                    <%
                                }
                            } else {
                    %>
                    <tr>
                        <td colspan="6" style="text-align: center; padding: 30px; color: var(--text-secondary);">暂无课表安排</td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" style="text-align: center; padding: 30px; color: var(--text-secondary);">暂无课表安排</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>