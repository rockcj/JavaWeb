<%@ page import="cn.edu.lingnan.pojo.Score" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>我的成绩</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———我的成绩</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="${pageContext.request.contextPath}/login">退出</a>
<a href="${pageContext.request.contextPath}/loginout">用户注销</a>
<a href="${pageContext.request.contextPath}/oneStu">查询个人信息</a>
<a href="${pageContext.request.contextPath}/details">查询详细信息</a>
<a href="${pageContext.request.contextPath}/user/myScores">查询我的成绩</a>
<a href="${pageContext.request.contextPath}/user/myTimeTable">查询我的课程表</a>
<hr>
<table>
    <tr>
        <th>成绩ID</th>
        <th>学生ID</th>
        <th>课程ID</th>
        <th>成绩</th>
        <th>学期</th>
        <th>考试类型</th>
        <th>状态标志</th>
    </tr>
    <%
        Object myScores = session.getAttribute("myScores");
        if(myScores == null) {
            myScores = request.getAttribute("myScores");
        }
    %>
    <%
        if(myScores != null){
            List<Score> list = (List<Score>) myScores;
            if(list != null && list.size() > 0)
            {
                for(Score score : list) {      %>
    <tr>
        <td><%=score.getScoreId() != null ? score.getScoreId() : ""%></td>
        <td><%=score.getStudentId() != null ? score.getStudentId() : ""%></td>
        <td><%=score.getCourseId() != null ? score.getCourseId() : ""%></td>
        <td><%=score.getScore() != null ? score.getScore() : ""%></td>
        <td><%=score.getSemester() != null ? score.getSemester() : ""%></td>
        <td><%=score.getExamType() != null ? score.getExamType() : ""%></td>
        <td><%=score.getFlag() != null ? score.getFlag() : 0%></td>
    </tr>
    <%
                }
            } else {
    %>
    <tr>
        <td colspan="7" style="text-align: center;">暂无成绩信息</td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="7" style="text-align: center;">暂无成绩信息</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

