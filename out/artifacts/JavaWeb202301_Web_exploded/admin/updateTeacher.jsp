<%@ page import="cn.edu.lingnan.pojo.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改教师 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>修改教师信息</h1>
            <a href="${pageContext.request.contextPath}/teacher/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <%
            String teacherId = request.getParameter("teacherId");
            Teacher targetTeacher = null;
            Object allTeacher = session.getAttribute("allTeacher");
            if(allTeacher == null) {
                allTeacher = request.getAttribute("allTeacher");
            }
            if(allTeacher != null && teacherId != null){
                List<Teacher> list = (List<Teacher>) allTeacher;
                for(Teacher t : list) {
                    if(t.getTeacherId().equals(teacherId)) {
                        targetTeacher = t;
                        break;
                    }
                }
            }
        %>

        <div class="form-panel">
            <% if (targetTeacher != null) { %>
            <form action="${pageContext.request.contextPath}/teacher/update" method="post">
                <!-- 隐藏域传递主键 -->
                <input type="hidden" name="teacherId" value="<%=targetTeacher.getTeacherId()%>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                    <div class="input-group">
                        <label>教师工号 (不可修改)</label>
                        <input type="text" value="<%=targetTeacher.getTeacherId()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>
                    
                    <div class="input-group">
                        <label>教师姓名 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="teacherName" value="<%=targetTeacher.getTeacherName()%>" required>
                    </div>

                    <div class="input-group">
                        <label>性别</label>
                         <select name="gender">
                            <option value="男" <%=targetTeacher.getGender() != null && targetTeacher.getGender().equals("男") ? "selected" : ""%>>男</option>
                            <option value="女" <%=targetTeacher.getGender() != null && targetTeacher.getGender().equals("女") ? "selected" : ""%>>女</option>
                        </select>
                    </div>

                    <div class="input-group">
                        <label>年龄</label>
                        <input type="number" name="age" value="<%=targetTeacher.getAge() != null ? targetTeacher.getAge() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>联系电话</label>
                        <input type="text" name="phone" value="<%=targetTeacher.getPhone() != null ? targetTeacher.getPhone() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>电子邮箱</label>
                        <input type="email" name="email" value="<%=targetTeacher.getEmail() != null ? targetTeacher.getEmail() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>所属院系</label>
                        <input type="text" name="deptId" value="<%=targetTeacher.getDeptId() != null ? targetTeacher.getDeptId() : ""%>">
                    </div>

                     <div class="input-group">
                        <label>职称</label>
                        <input type="text" name="title" value="<%=targetTeacher.getTitle() != null ? targetTeacher.getTitle() : ""%>">
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
                    未找到相关教师信息，请返回重试。
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>
