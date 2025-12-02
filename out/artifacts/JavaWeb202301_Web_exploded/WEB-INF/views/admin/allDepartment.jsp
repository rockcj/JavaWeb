<%@ page import="cn.edu.lingnan.pojo.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>院系管理 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="/admin/sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>院系信息管理</h1>
            <div class="action-links">
                <a href="${pageContext.request.contextPath}/department/addDepartment" class="btn btn-primary">
                    <span style="font-size: 1.2rem; line-height: 1;">+</span> 新增院系
                </a>
            </div>
        </div>

        <div class="table-container">
            <!-- 批量删除按钮 -->
            <div style="padding: 15px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; justify-content: flex-end;">
                <button class="btn btn-danger btn-sm" onclick="delcheckSingle('${pageContext.request.contextPath}/department/delete','deptId');">
                    🗑️ 批量删除选中
                </button>
            </div>

            <table class="tech-table">
                <thead>
                    <tr>
                        <th style="width: 50px;"><input type="checkbox" onclick="allcheck(this);"></th>
                        <th>院系ID</th>
                        <th>院系名称</th>
                        <th>院系编码</th>
                        <th>院系负责人</th>
                        <th>联系电话</th>
                        <th>邮箱</th>
                        <th>院系描述</th>
                        <th>状态</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Object allDepartment = session.getAttribute("allDepartment");
                        if(allDepartment == null) {
                            allDepartment = request.getAttribute("allDepartment");
                        }
                    %>
                    <%
                        if(allDepartment != null){
                            List<Department> list = (List<Department>) allDepartment;
                            if(list != null && !list.isEmpty()) {
                                for(Department department : list) {      %>
                    <tr>
                        <td><input type="checkbox" name="check" value="<%=department.getDeptId()%>"></td>
                        <td><%=department.getDeptId() != null ? department.getDeptId() : ""%></td>
                        <td><%=department.getDeptName() != null ? department.getDeptName() : ""%></td>
                        <td><span style="background: rgba(0, 210, 255, 0.1); padding: 2px 6px; border-radius: 4px; color: var(--accent-color); font-family: monospace;"><%=department.getDeptCode() != null ? department.getDeptCode() : ""%></span></td>
                        <td><%=department.getDeptHead() != null ? department.getDeptHead() : ""%></td>
                        <td><%=department.getDeptPhone() != null ? department.getDeptPhone() : ""%></td>
                        <td><%=department.getDeptEmail() != null ? department.getDeptEmail() : ""%></td>
                        <td title="<%=department.getDeptDesc()%>">
                            <%=department.getDeptDesc() != null ? (department.getDeptDesc().length() > 15 ? department.getDeptDesc().substring(0, 15) + "..." : department.getDeptDesc()) : ""%>
                        </td>
                        <td>
                            <% if(department.getDflag() != null && department.getDflag() == 0) { %>
                                <span style="color: var(--success);">● 正常</span>
                            <% } else { %>
                                <span style="color: var(--danger);">● 停用</span>
                            <% } %>
                        </td>
                        <td style="text-align: center;">
                            <a href="${pageContext.request.contextPath}/department/showUpdate?deptId=<%=department.getDeptId()%>" class="btn-link" title="编辑">✏️</a>
                            <span style="color: var(--border-color); margin: 0 5px;">|</span>
                            <a href="${pageContext.request.contextPath}/department/delete?deptId=<%=department.getDeptId()%>" 
                               class="btn-link danger" 
                               onclick="return confirm('确定要删除吗？')" title="删除">🗑️</a>
                        </td>
                    </tr>
                    <%
                                }
                            } else {
                    %>
                        <tr>
                            <td colspan="10" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无数据</td>
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