<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>学生管理 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>学生信息管理</h1>
            <div class="action-links">
                <a href="${pageContext.request.contextPath}/student/queryAll" class="btn btn-primary btn-sm <%=request.getParameter("flag")==null?"active":""%>">全部学生</a>
                <a href="${pageContext.request.contextPath}/student/queryAll?flag=1" class="btn btn-primary btn-sm <%= "1".equals(request.getParameter("flag"))?"active":""%>">审核通过</a>
                <a href="${pageContext.request.contextPath}/student/queryAll?flag=0" class="btn btn-primary btn-sm <%= "0".equals(request.getParameter("flag"))?"active":""%>">待审核</a>
                <a href="${pageContext.request.contextPath}/admin/addStu.jsp" class="btn btn-primary">
                    <span style="font-size: 1.2rem; line-height: 1;">+</span> 新增学生
                </a>
            </div>
        </div>

        <div class="table-container">
             <div style="padding: 15px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; justify-content: flex-end; gap: 10px;">
                <button class="btn btn-primary btn-sm" onclick="OKcheck();" style="background-color: var(--success); border-color: var(--success); color: #000;">
                    ✓ 批量审核通过
                </button>
                <button class="btn btn-danger btn-sm" onclick="delcheck('${pageContext.request.contextPath}/student/delete','sid');">
                    🗑️ 批量删除选中
                </button>
            </div>

            <table class="tech-table">
                <thead>
                    <tr>
                        <th style="width: 50px;"><input type="checkbox" onclick="allcheck(this);"></th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>密码</th>
                        <th>权限</th>
                        <th>状态标志</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                </thead>
                <tbody>
    <%
        Object allStu = session.getAttribute("allStu");
        if(allStu == null) {
            allStu = request.getAttribute("allStu");
        }
        if(allStu != null){
            List<Student> list = (List<Student>) allStu;
            if(list != null && !list.isEmpty())
            {
                for(Student s : list) {      %>
                    <tr>
                        <td><input type="checkbox" name="check" value="<%=s.getSid()%>"></td>
                        <td><%=s.getSid() != null ? s.getSid() : ""%></td>
                        <td><%=s.getSname() != null ? s.getSname() : ""%></td>
                        <td><span style="font-family: monospace;">******</span></td>
                        <td>
                            <% if(s.getSright() == 1) { %>
                                <span style="color: var(--accent-color);">管理员</span>
                            <% } else { %>
                                <span>普通用户</span>
                            <% } %>
                        </td>
                        <td>
                            <% if(s.getStflag() == 1) { %>
                                <span style="color: var(--success);">● 审核通过</span>
                            <% } else { %>
                                <span style="color: var(--danger);">○ 待审核</span>
                            <% } %>
                        </td>
                        <td style="text-align: center;">
                            <a href="${pageContext.request.contextPath}/student/queryById?sid=<%=s.getSid()%>" class="btn-link" title="编辑">✏️</a>
                            <span style="color: var(--border-color); margin: 0 5px;">|</span>
                            <a href="${pageContext.request.contextPath}/student/delete?sid=<%=s.getSid()%>" 
                               class="btn-link danger" 
                               onclick="return confirm('确定要删除吗？')" title="删除">🗑️</a>
                        </td>
                    </tr>
    <%
                }
            } else {
    %>
                    <tr>
                        <td colspan="7" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无数据</td>
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