<%@ page import="cn.edu.lingnan.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改课程 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="/admin/sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>修改课程信息</h1>
            <a href="${pageContext.request.contextPath}/course/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <%
            String courseId = request.getParameter("courseId");
            Course targetCourse = null;
            Object allCourse = session.getAttribute("allCourse");
            if(allCourse == null) {
                allCourse = request.getAttribute("allCourse");
            }
            if(allCourse != null && courseId != null){
                List<Course> list = (List<Course>) allCourse;
                for(Course c : list) {
                    if(c.getCourseId().equals(courseId)) {
                        targetCourse = c;
                        break;
                    }
                }
            }
        %>

        <div class="form-panel">
            <% if (targetCourse != null) { %>
            <form action="${pageContext.request.contextPath}/course/update" method="post">
                <!-- 隐藏域传递主键 -->
                <input type="hidden" name="courseId" value="<%=targetCourse.getCourseId()%>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                    <div class="input-group">
                        <label>课程ID (不可修改)</label>
                        <input type="text" value="<%=targetCourse.getCourseId()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>
                    
                    <div class="input-group">
                        <label>课程名称 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="courseName" value="<%=targetCourse.getCourseName()%>" required>
                    </div>

                    <div class="input-group">
                        <label>课程代码 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="courseCode" value="<%=targetCourse.getCourseCode()%>" required>
                    </div>

                    <div class="input-group">
                        <label>所属院系</label>
                        <input type="text" name="deptId" value="<%=targetCourse.getDeptId() != null ? targetCourse.getDeptId() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>学分</label>
                        <input type="number" name="credit" value="<%=targetCourse.getCredit() != null ? targetCourse.getCredit() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>学时</label>
                        <input type="number" name="courseHours" value="<%=targetCourse.getCourseHours() != null ? targetCourse.getCourseHours() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>课程类型</label>
                        <input type="text" name="courseType" value="<%=targetCourse.getCourseType() != null ? targetCourse.getCourseType() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>授课教师</label>
                        <input type="text" name="teacherName" value="<%=targetCourse.getTeacherName() != null ? targetCourse.getTeacherName() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>学期</label>
                        <input type="text" name="semester" value="<%=targetCourse.getSemester() != null ? targetCourse.getSemester() : ""%>">
                    </div>
                </div>

                <div class="input-group">
                    <label>课程描述</label>
                    <textarea name="courseDesc" rows="4"><%=targetCourse.getCourseDesc() != null ? targetCourse.getCourseDesc() : ""%></textarea>
                </div>

                <div style="margin-top: 20px; display: flex; justify-content: flex-end;">
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000; padding: 12px 40px;">
                        💾 保存修改
                    </button>
                </div>
            </form>
            <% } else { %>
                <div style="text-align: center; padding: 40px; color: var(--danger);">
                    未找到相关课程信息，请返回重试。
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>