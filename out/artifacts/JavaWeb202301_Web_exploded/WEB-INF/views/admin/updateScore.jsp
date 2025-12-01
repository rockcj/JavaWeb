<%@ page import="cn.edu.lingnan.pojo.Score" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>编辑成绩</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———编辑成绩信息</h1>
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

<form action="${pageContext.request.contextPath}/score/update" method="post">
    <table>
        <tr>
            <th>成绩ID</th>
            <th>学生ID</th>
            <th>课程ID</th>
            <th>成绩</th>
            <th>学期</th>
            <th>考试类型</th>
            <th>状态标志</th>
            <th>操作选择</th>
        </tr>
        <%
            Object allScore = session.getAttribute("allScore");
            if(allScore == null) {
                allScore = request.getAttribute("allScore");
            }
            String scoreId = request.getParameter("scoreId");
        %>
        <%
            if(allScore != null && scoreId != null){
                List<Score> list = (List<Score>) allScore;
                if(list != null)
                {
                    for(Score score : list) {
                        if(score.getScoreId() != null && score.getScoreId().equals(scoreId)){
        %>
        <tr>
            <td>
                <input type="hidden" name="scoreId" value="<%=score.getScoreId() != null ? score.getScoreId() : ""%>">
                <%=score.getScoreId() != null ? score.getScoreId() : ""%>
            </td>
            <td><input type="text" name="studentId" value="<%=score.getStudentId() != null ? score.getStudentId() : ""%>"></td>
            <td><input type="text" name="courseId" value="<%=score.getCourseId() != null ? score.getCourseId() : ""%>"></td>
            <td><input type="text" name="score" value="<%=score.getScore() != null ? score.getScore() : ""%>"></td>
            <td><input type="text" name="semester" value="<%=score.getSemester() != null ? score.getSemester() : ""%>"></td>
            <td><input type="text" name="examType" value="<%=score.getExamType() != null ? score.getExamType() : ""%>"></td>
            <td><input type="text" name="flag" value="<%=score.getFlag() != null ? score.getFlag() : 0%>"></td>
            <td>
                <input type="submit" value="确认修改">
                <a href="${pageContext.request.contextPath}/score/queryAll">取消</a>
            </td>
        </tr>
        <%
                        }
                    }
                }
            } else {
        %>
        <tr>
            <td colspan="8" style="text-align: center; color: red;">
                未找到要编辑的成绩信息，请返回列表页面。
                <a href="${pageContext.request.contextPath}/score/queryAll">返回列表</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>

