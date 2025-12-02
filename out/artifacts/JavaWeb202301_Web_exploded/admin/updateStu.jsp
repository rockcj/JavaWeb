<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改学生 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>修改学生信息</h1>
            <a href="${pageContext.request.contextPath}/student/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <%
            String sid = request.getParameter("sid");
            Student targetStudent = null;
            Object allStu = session.getAttribute("allStu");
            if(allStu == null) {
                allStu = request.getAttribute("allStu");
            }
            if(allStu != null && sid != null){
                List<Student> list = (List<Student>) allStu;
                for(Student s : list) {
                    if(s.getSid().equals(sid)) {
                        targetStudent = s;
                        break;
                    }
                }
            }
        %>

        <div class="form-panel">
            <% if (targetStudent != null) { %>
            <form action="${pageContext.request.contextPath}/student/update" method="post">
                <!-- 隐藏域传递主键 -->
                <input type="hidden" name="sid" value="<%=targetStudent.getSid()%>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                    <div class="input-group">
                        <label>学号 (不可修改)</label>
                        <input type="text" value="<%=targetStudent.getSid()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>
                    
                    <div class="input-group">
                        <label>姓名 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="sname" value="<%=targetStudent.getSname()%>" required>
                    </div>

                    <div class="input-group">
                        <label>密码 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="spassword" value="<%=targetStudent.getSpassword()%>" required>
                    </div>

                    <div class="input-group">
                        <label>权限</label>
                        <select name="sright">
                            <option value="0" <%=targetStudent.getSright() == 0 ? "selected" : ""%>>普通用户</option>
                            <option value="1" <%=targetStudent.getSright() == 1 ? "selected" : ""%>>管理员</option>
                        </select>
                    </div>

                    <div class="input-group">
                        <label>审核状态</label>
                        <select name="stflag">
                            <option value="0" <%=targetStudent.getStflag() == 0 ? "selected" : ""%>>待审核</option>
                            <option value="1" <%=targetStudent.getStflag() == 1 ? "selected" : ""%>>审核通过</option>
                        </select>
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
                    未找到相关学生信息，请返回重试。
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>