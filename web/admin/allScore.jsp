<%@ page import="cn.edu.lingnan.pojo.Score" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>成绩管理 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>成绩信息管理</h1>
            <div class="action-links">
                <a href="${pageContext.request.contextPath}/score/queryAll" class="btn btn-primary btn-sm <%=request.getParameter("flag")==null?"active":""%>">全部成绩</a>
                <a href="${pageContext.request.contextPath}/admin/addScore.jsp" class="btn btn-primary">
                    <span style="font-size: 1.2rem; line-height: 1;">+</span> 新增成绩
                </a>
            </div>
        </div>

        <div class="table-container">
            <div style="padding: 15px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; justify-content: flex-end;">
                <button class="btn btn-danger btn-sm" onclick="delcheckSingle('${pageContext.request.contextPath}/score/delete','scoreId');">
                    🗑️ 批量删除选中
                </button>
            </div>

            <table class="tech-table">
                <thead>
                    <tr>
                        <th style="width: 50px;"><input type="checkbox" onclick="allcheck(this);"></th>
                        <th>成绩ID</th>
                        <th>学生学号</th>
                        <th>课程ID</th>
                        <th>分数</th>
                        <th>学期</th>
                        <th>考试类型</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                </thead>
                <tbody>
    <%
        Object allScore = session.getAttribute("allScore");
        if(allScore == null) {
            allScore = request.getAttribute("allScore");
        }
        if(allScore != null){
            List<Score> list = (List<Score>) allScore;
            if(list != null && !list.isEmpty())
            {
                for(Score s : list) {      %>
                    <tr>
                        <td><input type="checkbox" name="check" value="<%=s.getScoreId()%>"></td>
                        <td><%=s.getScoreId() != null ? s.getScoreId() : ""%></td>
                        <td><%=s.getStudentId() != null ? s.getStudentId() : ""%></td>
                        <td><%=s.getCourseId() != null ? s.getCourseId() : ""%></td>
                        <td>
                            <% 
                                String scoreStr = s.getScore() != null ? s.getScore() : "0";
                                double scoreVal = 0;
                                try { scoreVal = Double.parseDouble(scoreStr); } catch(Exception e) {}
                                if(scoreVal < 60) { 
                            %>
                                <span style="color: var(--danger); font-weight: bold;"><%=scoreStr%></span>
                            <% } else if (scoreVal >= 90) { %>
                                <span style="color: var(--success); font-weight: bold;"><%=scoreStr%></span>
                            <% } else { %>
                                <%=scoreStr%>
                            <% } %>
                        </td>
                        <td><%=s.getSemester() != null ? s.getSemester() : ""%></td>
                        <td><%=s.getExamType() != null ? s.getExamType() : ""%></td>
                        <td style="text-align: center;">
                            <a href="${pageContext.request.contextPath}/score/showUpdate?scoreId=<%=s.getScoreId()%>" class="btn-link" title="编辑">✏️</a>
                            <span style="color: var(--border-color); margin: 0 5px;">|</span>
                            <a href="${pageContext.request.contextPath}/score/delete?scoreId=<%=s.getScoreId()%>" 
                               class="btn-link danger" 
                               onclick="return confirm('确定要删除吗？')" title="删除">🗑️</a>
                        </td>
                    </tr>
    <%
                }
            } else {
    %>
                    <tr>
                        <td colspan="8" style="text-align: center; color: var(--text-secondary); padding: 30px;">暂无数据</td>
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
