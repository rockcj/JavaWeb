<%@ page import="cn.edu.lingnan.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>课程管理 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>课程信息管理</h1>
            <div class="action-links">
                <a href="${pageContext.request.contextPath}/course/queryAll" class="btn btn-primary btn-sm <%=request.getParameter("flag")==null?"active":""%>">全部课程</a>
                <a href="${pageContext.request.contextPath}/admin/addCourse.jsp" class="btn btn-primary">
                    <span style="font-size: 1.2rem; line-height: 1;">+</span> 新增课程
                </a>
            </div>
        </div>

        <div class="table-container">
            <div style="padding: 15px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; justify-content: flex-end;">
                <button class="btn btn-danger btn-sm" onclick="delcheckSingle('${pageContext.request.contextPath}/course/delete','courseId');">
                    🗑️ 批量删除选中
                </button>
            </div>

            <table class="tech-table">
                <thead>
                    <tr>
                        <th style="width: 50px;"><input type="checkbox" onclick="allcheck(this);"></th>
                        <th>课程ID</th>
                        <th>课程名称</th>
                        <th>课程代码</th>
                        <th>所属院系</th>
                        <th>学分</th>
                        <th>学时</th>
                        <th>类型</th>
                        <th>授课教师</th>
                        <th>学期</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                </thead>
                <tbody>
    <%
        Object allCourse = session.getAttribute("allCourse");
        if(allCourse == null) {
            allCourse = request.getAttribute("allCourse");
        }
        if(allCourse != null){
            List<Course> list = (List<Course>) allCourse;
            if(list != null && !list.isEmpty())
            {
                for(Course c : list) {      %>
                    <tr>
                        <td><input type="checkbox" name="check" value="<%=c.getCourseId()%>"></td>
                        <td><%=c.getCourseId() != null ? c.getCourseId() : ""%></td>
                        <td><%=c.getCourseName() != null ? c.getCourseName() : ""%></td>
                        <td><span style="background: rgba(0, 210, 255, 0.1); padding: 2px 6px; border-radius: 4px; color: var(--accent-color); font-family: monospace;"><%=c.getCourseCode() != null ? c.getCourseCode() : ""%></span></td>
                        <td><%=c.getDeptId() != null ? c.getDeptId() : ""%></td>
                        <td><%=c.getCredit() != null ? c.getCredit() : ""%></td>
                        <td><%=c.getCourseHours() != null ? c.getCourseHours() : ""%></td>
                        <td><%=c.getCourseType() != null ? c.getCourseType() : ""%></td>
                        <td><%=c.getTeacherName() != null ? c.getTeacherName() : ""%></td>
                        <td><%=c.getSemester() != null ? c.getSemester() : ""%></td>
                        <td style="text-align: center;">
                            <a href="${pageContext.request.contextPath}/course/showUpdate?courseId=<%=c.getCourseId()%>" class="btn-link" title="编辑">✏️</a>
                            <span style="color: var(--border-color); margin: 0 5px;">|</span>
                            <a href="${pageContext.request.contextPath}/course/delete?courseId=<%=c.getCourseId()%>" 
                               class="btn-link danger" 
                               onclick="return confirm('确定要删除吗？')" title="删除">🗑️</a>
                        </td>
                    </tr>
    <%
                }
            } else {
    %>
                    <tr>
                        <td colspan="11" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无数据</td>
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
