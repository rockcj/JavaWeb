<%@ page import="cn.edu.lingnan.pojo.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>教师管理 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>教师信息管理</h1>
            <div class="action-links">
                <a href="${pageContext.request.contextPath}/teacher/queryAll" class="btn btn-primary btn-sm <%=request.getParameter("flag")==null?"active":""%>">全部教师</a>
                <a href="${pageContext.request.contextPath}/admin/addTeacher.jsp" class="btn btn-primary">
                    <span style="font-size: 1.2rem; line-height: 1;">+</span> 新增教师
                </a>
            </div>
        </div>

        <div class="table-container">
            <div style="padding: 15px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; justify-content: flex-end;">
                <button class="btn btn-danger btn-sm" onclick="delcheckSingle('${pageContext.request.contextPath}/teacher/delete','teacherId');">
                    🗑️ 批量删除选中
                </button>
            </div>

            <table class="tech-table">
                <thead>
                    <tr>
                        <th style="width: 50px;"><input type="checkbox" onclick="allcheck(this);"></th>
                        <th>工号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>职称</th>
                        <th>所属院系</th>
                        <th>联系电话</th>
                        <th>邮箱</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                </thead>
                <tbody>
    <%
        Object allTeacher = session.getAttribute("allTeacher");
        if(allTeacher == null) {
            allTeacher = request.getAttribute("allTeacher");
        }
        if(allTeacher != null){
            List<Teacher> list = (List<Teacher>) allTeacher;
            if(list != null && !list.isEmpty())
            {
                for(Teacher t : list) {      %>
                    <tr>
                        <td><input type="checkbox" name="check" value="<%=t.getTeacherId()%>"></td>
                        <td><%=t.getTeacherId() != null ? t.getTeacherId() : ""%></td>
                        <td><%=t.getTeacherName() != null ? t.getTeacherName() : ""%></td>
                        <td><%=t.getGender() != null ? t.getGender() : ""%></td>
                        <td><span style="background: rgba(0, 210, 255, 0.1); padding: 2px 6px; border-radius: 4px; color: var(--accent-color);"><%=t.getTitle() != null ? t.getTitle() : ""%></span></td>
                        <td><%=t.getDeptId() != null ? t.getDeptId() : ""%></td>
                        <td><%=t.getPhone() != null ? t.getPhone() : ""%></td>
                        <td><%=t.getEmail() != null ? t.getEmail() : ""%></td>
                        <td style="text-align: center;">
                            <a href="${pageContext.request.contextPath}/teacher/showUpdate?teacherId=<%=t.getTeacherId()%>" class="btn-link" title="编辑">✏️</a>
                            <span style="color: var(--border-color); margin: 0 5px;">|</span>
                            <a href="${pageContext.request.contextPath}/teacher/delete?teacherId=<%=t.getTeacherId()%>" 
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