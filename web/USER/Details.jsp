<%@ page import="cn.edu.lingnan.Dto.JobDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>详细信息 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>项目职位信息</h1>
            <div style="color: var(--text-secondary);">您参与的项目及担任的职位</div>
        </div>

        <div class="table-container">
            <table class="tech-table">
                <thead>
                    <tr>
                        <th>项目ID</th>
                        <th>项目名称</th>
                        <th>担任职位</th>
                        <th>学生姓名</th>
                        <th>学号</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Object allJobDto = session.getAttribute("StuJobDto");
                        if(allJobDto !=null){
                            List<JobDto> list = (List<JobDto>) allJobDto;
                            if(list!=null && !list.isEmpty())
                            {
                                for(JobDto job : list) {      %>
                    <tr>
                        <td><%=job.getIid()%></td>
                        <td><%=job.getIname()%></td>
                        <td><span style="background: rgba(0, 210, 255, 0.1); padding: 2px 6px; border-radius: 4px; color: var(--accent-color);"><%=job.getJob()%></span></td>
                        <td><%=job.getSname()%></td>
                        <td><%=job.getSid()%></td>
                    </tr>
                    <%
                                }
                            } else {
                    %>
                        <tr>
                            <td colspan="5" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无项目信息</td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="5" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无项目信息</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>