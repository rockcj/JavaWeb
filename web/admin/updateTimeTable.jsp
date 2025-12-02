<%@ page import="cn.edu.lingnan.pojo.TimeTable" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改课表 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>修改课表信息</h1>
            <a href="${pageContext.request.contextPath}/timetable/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <%
            String timetableId = request.getParameter("timetableId");
            TimeTable targetTimeTable = null;
            Object allTimeTable = session.getAttribute("allTimeTable");
            if(allTimeTable == null) {
                allTimeTable = request.getAttribute("allTimeTable");
            }
            if(allTimeTable != null && timetableId != null){
                List<TimeTable> list = (List<TimeTable>) allTimeTable;
                for(TimeTable t : list) {
                    if(t.getTimetableId().equals(timetableId)) {
                        targetTimeTable = t;
                        break;
                    }
                }
            }
        %>

        <div class="form-panel">
            <% if (targetTimeTable != null) { %>
            <form action="${pageContext.request.contextPath}/timetable/update" method="post">
                <!-- 隐藏域传递主键 -->
                <input type="hidden" name="timetableId" value="<%=targetTimeTable.getTimetableId()%>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                    <div class="input-group">
                        <label>课表ID (不可修改)</label>
                        <input type="text" value="<%=targetTimeTable.getTimetableId()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>
                    
                    <div class="input-group">
                        <label>课程ID <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="courseId" value="<%=targetTimeTable.getCourseId()%>" required>
                    </div>

                    <div class="input-group">
                        <label>教师工号 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="teacherId" value="<%=targetTimeTable.getTeacherId()%>" required>
                    </div>

                    <div class="input-group">
                        <label>教室</label>
                        <input type="text" name="classroomId" value="<%=targetTimeTable.getClassroomId() != null ? targetTimeTable.getClassroomId() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>星期</label>
                        <input type="text" name="dayOfWeek" value="<%=targetTimeTable.getDayOfWeek() != null ? targetTimeTable.getDayOfWeek() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>节次</label>
                        <input type="text" name="timeSlot" value="<%=targetTimeTable.getTimeSlot() != null ? targetTimeTable.getTimeSlot() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>学期</label>
                        <input type="text" name="semester" value="<%=targetTimeTable.getSemester() != null ? targetTimeTable.getSemester() : ""%>">
                    </div>
                </div>

                <div style="margin-top: 20px; display: flex; justify-content: flex-end;">
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000; padding: 12px 40px;">
                        💾 保存修改
                    </button>
                </div>
            </form>
            <% } else { %>
                <div style="text-align: center; padding: 40px; color: var(--danger);">
                    未找到相关课表信息，请返回重试。
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>
