<%@ page import="cn.edu.lingnan.pojo.TimeTable" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>课表管理 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>课表信息管理</h1>
            <div class="action-links">
                <a href="${pageContext.request.contextPath}/timetable/queryAll" class="btn btn-primary btn-sm <%=request.getParameter("flag")==null?"active":""%>">全部课表</a>
                <a href="${pageContext.request.contextPath}/admin/addTimeTable.jsp" class="btn btn-primary">
                    <span style="font-size: 1.2rem; line-height: 1;">+</span> 新增课表
                </a>
            </div>
        </div>

        <div class="table-container">
            <div style="padding: 15px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; justify-content: flex-end;">
                <button class="btn btn-danger btn-sm" onclick="delcheckSingle('${pageContext.request.contextPath}/timetable/delete','timetableId');">
                    🗑️ 批量删除选中
                </button>
            </div>

            <table class="tech-table">
                <thead>
                    <tr>
                        <th style="width: 50px;"><input type="checkbox" onclick="allcheck(this);"></th>
                        <th>课表ID</th>
                        <th>课程ID</th>
                        <th>教师工号</th>
                        <th>教室</th>
                        <th>星期</th>
                        <th>节次</th>
                        <th>学期</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                </thead>
                <tbody>
    <%
        Object allTimeTable = session.getAttribute("allTimeTable");
        if(allTimeTable == null) {
            allTimeTable = request.getAttribute("allTimeTable");
        }
        if(allTimeTable != null){
            List<TimeTable> list = (List<TimeTable>) allTimeTable;
            if(list != null && !list.isEmpty())
            {
                for(TimeTable t : list) {      %>
                    <tr>
                        <td><input type="checkbox" name="check" value="<%=t.getTimetableId()%>"></td>
                        <td><%=t.getTimetableId() != null ? t.getTimetableId() : ""%></td>
                        <td><%=t.getCourseId() != null ? t.getCourseId() : ""%></td>
                        <td><%=t.getTeacherId() != null ? t.getTeacherId() : ""%></td>
                        <td><%=t.getClassroomId() != null ? t.getClassroomId() : ""%></td>
                        <td><%=t.getDayOfWeek() != null ? t.getDayOfWeek() : ""%></td>
                        <td><%=t.getTimeSlot() != null ? t.getTimeSlot() : ""%></td>
                        <td><%=t.getSemester() != null ? t.getSemester() : ""%></td>
                        <td style="text-align: center;">
                            <a href="${pageContext.request.contextPath}/timetable/showUpdate?timetableId=<%=t.getTimetableId()%>" class="btn-link" title="编辑">✏️</a>
                            <span style="color: var(--border-color); margin: 0 5px;">|</span>
                            <a href="${pageContext.request.contextPath}/timetable/delete?timetableId=<%=t.getTimetableId()%>" 
                               class="btn-link danger" 
                               onclick="return confirm('确定要删除吗？')" title="删除">🗑️</a>
                        </td>
                    </tr>
    <%
                }
            } else {
    %>
                    <tr>
                        <td colspan="9" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无数据</td>
                    </tr>
    <%
            }
        }
    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
