<%@ page import="cn.edu.lingnan.pojo.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改项目 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>修改项目信息</h1>
            <a href="${pageContext.request.contextPath}/item/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <%
            String iid = request.getParameter("iid");
            Item targetItem = null;
            Object allItem = session.getAttribute("allItem");
            if(allItem == null) {
                allItem = request.getAttribute("allItem");
            }
            if(allItem != null && iid != null){
                List<Item> list = (List<Item>) allItem;
                for(Item i : list) {
                    if(i.getIid().equals(iid)) {
                        targetItem = i;
                        break;
                    }
                }
            }
        %>

        <div class="form-panel">
            <% if (targetItem != null) { %>
            <form action="${pageContext.request.contextPath}/item/update" method="post">
                <!-- 隐藏域传递主键 -->
                <input type="hidden" name="iid" value="<%=targetItem.getIid()%>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                    <div class="input-group">
                        <label>项目ID (不可修改)</label>
                        <input type="text" value="<%=targetItem.getIid()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>
                    
                    <div class="input-group">
                        <label>项目名称 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="iname" value="<%=targetItem.getIname()%>" required>
                    </div>

                    <div class="input-group">
                        <label>项目状态 <span style="color: var(--danger);">*</span></label>
                        <select name="iflag">
                            <option value="1" <%=targetItem.getIflag() == 1 ? "selected" : ""%>>启用</option>
                            <option value="0" <%=targetItem.getIflag() == 0 ? "selected" : ""%>>停用</option>
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
                    未找到相关项目信息，请返回重试。
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>