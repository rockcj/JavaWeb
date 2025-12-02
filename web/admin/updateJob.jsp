<%@ page import="cn.edu.lingnan.pojo.Job" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改职位 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>修改职位信息</h1>
            <a href="${pageContext.request.contextPath}/job/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <%
            String sid = request.getParameter("sid");
            String iid = request.getParameter("iid");
            Job targetJob = null;
            Object allJob = session.getAttribute("allJob");
            if(allJob == null) {
                allJob = request.getAttribute("allJob");
            }
            if(allJob != null && sid != null && iid != null){
                List<Job> list = (List<Job>) allJob;
                for(Job j : list) {
                    if(j.getSid().equals(sid) && j.getIid().equals(iid)) {
                        targetJob = j;
                        break;
                    }
                }
            }
        %>

        <div class="form-panel">
            <% if (targetJob != null) { %>
            <form action="${pageContext.request.contextPath}/job/update" method="post">
                <!-- 隐藏域传递主键 -->
                <input type="hidden" name="sid" value="<%=targetJob.getSid()%>">
                <input type="hidden" name="iid" value="<%=targetJob.getIid()%>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                    <div class="input-group">
                        <label>学生学号 (不可修改)</label>
                        <input type="text" value="<%=targetJob.getSid()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>
                    
                    <div class="input-group">
                        <label>项目ID (不可修改)</label>
                        <input type="text" value="<%=targetJob.getIid()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>

                    <div class="input-group">
                        <label>职位 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="job" value="<%=targetJob.getJob()%>" required>
                    </div>

                    <div class="input-group">
                        <label>状态</label>
                         <select name="scflag">
                            <option value="1" <%=targetJob.getScflag() == 1 ? "selected" : ""%>>启用</option>
                            <option value="0" <%=targetJob.getScflag() == 0 ? "selected" : ""%>>停用</option>
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
                    未找到相关职位信息，请返回重试。
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>