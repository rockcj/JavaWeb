<%@ page import="cn.edu.lingnan.pojo.Score" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>我的成绩 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="/USER/sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>我的成绩单</h1>
            <div style="color: var(--text-secondary);">查看您的各科考试成绩</div>
        </div>

        <div class="table-container">
            <table class="tech-table">
                <thead>
                    <tr>
                        <th>课程ID</th>
                        <th>学期</th>
                        <th>考试类型</th>
                        <th>成绩</th>
                        <th>状态</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Object myScores = session.getAttribute("myScores");
                        if(myScores == null) {
                            myScores = request.getAttribute("myScores");
                        }
                        if(myScores != null){
                            List<Score> list = (List<Score>) myScores;
                            if(list != null && list.size() > 0)
                            {
                                for(Score score : list) {      %>
                    <tr>
                        <td><%=score.getCourseId() != null ? score.getCourseId() : ""%></td>
                        <td><%=score.getSemester() != null ? score.getSemester() : ""%></td>
                        <td><%=score.getExamType() != null ? score.getExamType() : ""%></td>
                        <td>
                            <% 
                                String scoreStr = score.getScore() != null ? score.getScore() : "0";
                                double scoreVal = 0;
                                try { scoreVal = Double.parseDouble(scoreStr); } catch(Exception e) {}
                                if(scoreVal < 60) { 
                            %>
                                <span style="color: var(--danger); font-weight: bold; font-size: 1.1rem;"><%=scoreStr%></span>
                            <% } else if (scoreVal >= 90) { %>
                                <span style="color: var(--success); font-weight: bold; font-size: 1.1rem;"><%=scoreStr%></span>
                            <% } else { %>
                                <span style="font-weight: bold;"><%=scoreStr%></span>
                            <% } %>
                        </td>
                        <td>
                            <%-- 假设flag表示成绩状态，这里做个简单展示 --%>
                            <%=score.getFlag() != null ? score.getFlag() : 0%>
                        </td>
                    </tr>
                    <%
                                }
                            } else {
                    %>
                    <tr>
                        <td colspan="5" style="text-align: center; padding: 30px; color: var(--text-secondary);">暂无成绩记录</td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5" style="text-align: center; padding: 30px; color: var(--text-secondary);">暂无成绩记录</td>
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