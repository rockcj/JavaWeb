<%@ page import="cn.edu.lingnan.pojo.Score" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>成绩管理</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———成绩信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="${pageContext.request.contextPath}/login">退出</a>
<a href="${pageContext.request.contextPath}/loginout">用户注销</a>
<a href="${pageContext.request.contextPath}/student/queryAll">查询学生信息</a>
<a href="${pageContext.request.contextPath}/item/queryAll">查询项目信息</a>
<a href="${pageContext.request.contextPath}/job/queryAll">查询职位信息</a>
<a href="${pageContext.request.contextPath}/course/queryAll">查询课程信息</a>
<a href="${pageContext.request.contextPath}/department/queryAll">查询院系信息</a>
<a href="${pageContext.request.contextPath}/score/queryAll">查询成绩信息</a>
<a href="${pageContext.request.contextPath}/teacher/queryAll">查询教师信息</a>
<a href="${pageContext.request.contextPath}/timetable/queryAll">查询课程表信息</a>
<hr>
<table>
    <tr>
        <th><input type="checkbox" onclick="allcheck(this);"></th>
        <th>成绩ID</th>
        <th>学生ID</th>
        <th>课程ID</th>
        <th>成绩</th>
        <th>学期</th>
        <th>考试类型</th>
        <th>状态标志</th>
        <th><input type="submit" value="批量删除" onclick="delcheckSingle('${pageContext.request.contextPath}/score/delete','scoreId');"></th>
    </tr>
    <%
        Object allScore = session.getAttribute("allScore");
        if(allScore == null) {
            allScore = request.getAttribute("allScore");
        }
    %>
    <%
        if(allScore != null){
            List<Score> list = (List<Score>) allScore;
            if(list != null)
            {
                for(Score score : list) {      %>
    <tr>
        <td><input type="checkbox" name="check" value="<%=score.getScoreId() != null ? score.getScoreId() : ""%>"></td>
        <td><%=score.getScoreId() != null ? score.getScoreId() : ""%></td>
        <td><%=score.getStudentId() != null ? score.getStudentId() : ""%></td>
        <td><%=score.getCourseId() != null ? score.getCourseId() : ""%></td>
        <td><%=score.getScore() != null ? score.getScore() : ""%></td>
        <td><%=score.getSemester() != null ? score.getSemester() : ""%></td>
        <td><%=score.getExamType() != null ? score.getExamType() : ""%></td>
        <td><%=score.getFlag() != null ? score.getFlag() : 0%></td>
        <td>
            <a href="${pageContext.request.contextPath}/score/showUpdate?scoreId=<%=score.getScoreId()%>">修改</a> &nbsp; | &nbsp;
            <a href="${pageContext.request.contextPath}/score/delete?scoreId=<%=score.getScoreId()%>"
               onclick="return confirm('确定要删除吗？')">删除</a>
        </td>
    </tr>
    <%
                }
            }
        }
    %>
</table>
<a href="addScore.jsp" class="add-item-btn">+</a>
</body>

