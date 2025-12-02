<%@ page import="cn.edu.lingnan.pojo.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>项目管理 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>项目信息管理</h1>
            <div class="action-links">
                <a href="${pageContext.request.contextPath}/item/queryAll" class="btn btn-primary btn-sm <%=request.getParameter("flag")==null?"active":""%>">全部项目</a>
                <a href="${pageContext.request.contextPath}/admin/addItem.jsp" class="btn btn-primary">
                    <span style="font-size: 1.2rem; line-height: 1;">+</span> 新增项目
                </a>
            </div>
        </div>

        <div class="table-container">
            <div style="padding: 15px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; justify-content: flex-end;">
                <button class="btn btn-danger btn-sm" onclick="delcheckSingle('${pageContext.request.contextPath}/item/delete','iid');">
                    🗑️ 批量删除选中
                </button>
            </div>

            <table class="tech-table">
                <thead>
                    <tr>
                        <th style="width: 50px;"><input type="checkbox" onclick="allcheck(this);"></th>
                        <th>项目ID</th>
                        <th>项目名称</th>
                        <th>项目状态</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                </thead>
                <tbody>
    <%
        Object allItem = session.getAttribute("allItem");
        if(allItem == null) {
            allItem = request.getAttribute("allItem");
        }
        if(allItem != null){
            List<Item> list = (List<Item>) allItem;
            if(list != null && !list.isEmpty())
            {
                for(Item i : list) {      %>
                    <tr>
                        <td><input type="checkbox" name="check" value="<%=i.getIid()%>"></td>
                        <td><%=i.getIid() != null ? i.getIid() : ""%></td>
                        <td><%=i.getIname() != null ? i.getIname() : ""%></td>
                        <td>
                            <% if(i.getIflag() == 1) { %>
                                <span style="color: var(--success);">● 启用</span>
                            <% } else { %>
                                <span style="color: var(--danger);">○ 停用</span>
                            <% } %>
                        </td>
                        <td style="text-align: center;">
                            <a href="${pageContext.request.contextPath}/item/showUpdate?iid=<%=i.getIid()%>" class="btn-link" title="编辑">✏️</a>
                            <span style="color: var(--border-color); margin: 0 5px;">|</span>
                            <a href="${pageContext.request.contextPath}/item/delete?iid=<%=i.getIid()%>" 
                               class="btn-link danger" 
                               onclick="return confirm('确定要删除吗？')" title="删除">🗑️</a>
                        </td>
                    </tr>
    <%
                }
            } else {
    %>
                    <tr>
                        <td colspan="5" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无数据</td>
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