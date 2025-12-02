<%@ page import="cn.edu.lingnan.pojo.Job" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>职位管理 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>职位信息管理</h1>
            <div class="action-links">
                <a href="${pageContext.request.contextPath}/job/queryAll" class="btn btn-primary btn-sm <%=request.getParameter("flag")==null?"active":""%>">全部职位</a>
                <a href="${pageContext.request.contextPath}/admin/addJob.jsp" class="btn btn-primary">
                    <span style="font-size: 1.2rem; line-height: 1;">+</span> 新增职位
                </a>
            </div>
        </div>

        <div class="table-container">
            <div style="padding: 15px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; justify-content: flex-end;">
                <button class="btn btn-danger btn-sm" onclick="delcheck('${pageContext.request.contextPath}/job/delete','sid','iid');">
                    🗑️ 批量删除选中
                </button>
            </div>

            <table class="tech-table">
                <thead>
                    <tr>
                        <th style="width: 50px;"><input type="checkbox" onclick="allcheck(this);"></th>
                        <th>学号</th>
                        <th>项目ID</th>
                        <th>职位</th>
                        <th>状态</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                </thead>
                <tbody>
    <%
        Object allJob = session.getAttribute("allJob");
        if(allJob == null) {
            allJob = request.getAttribute("allJob");
        }
        if(allJob != null){
            List<Job> list = (List<Job>) allJob;
            if(list != null && !list.isEmpty())
            {
                for(Job j : list) {      %>
                    <tr>
                        <td><input type="checkbox" name="check" value="<%=j.getSid()%>,<%=j.getIid()%>"></td>
                        <td><%=j.getSid() != null ? j.getSid() : ""%></td>
                        <td><%=j.getIid() != null ? j.getIid() : ""%></td>
                        <td><%=j.getJob() != null ? j.getJob() : ""%></td>
                        <td>
                            <% if(j.getScflag() == 1) { %>
                                <span style="color: var(--success);">● 启用</span>
                            <% } else { %>
                                <span style="color: var(--danger);">○ 停用</span>
                            <% } %>
                        </td>
                        <td style="text-align: center;">
                            <a href="${pageContext.request.contextPath}/job/showUpdate?sid=<%=j.getSid()%>&iid=<%=j.getIid()%>" class="btn-link" title="编辑">✏️</a>
                            <span style="color: var(--border-color); margin: 0 5px;">|</span>
                            <a href="${pageContext.request.contextPath}/job/delete?sid=<%=j.getSid()%>&iid=<%=j.getIid()%>" 
                               class="btn-link danger" 
                               onclick="return confirm('确定要删除吗？')" title="删除">🗑️</a>
                        </td>
                    </tr>
    <%
                }
            } else {
    %>
                    <tr>
                        <td colspan="6" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无数据</td>
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