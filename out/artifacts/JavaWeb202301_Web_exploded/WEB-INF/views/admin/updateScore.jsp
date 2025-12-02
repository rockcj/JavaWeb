<%@ page import="cn.edu.lingnan.pojo.Score" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改成绩 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="/admin/sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>修改成绩信息</h1>
            <a href="${pageContext.request.contextPath}/score/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <%
            String scoreId = request.getParameter("scoreId");
            Score targetScore = null;
            Object allScore = session.getAttribute("allScore");
            if(allScore == null) {
                allScore = request.getAttribute("allScore");
            }
            if(allScore != null && scoreId != null){
                List<Score> list = (List<Score>) allScore;
                for(Score s : list) {
                    if(s.getScoreId().equals(scoreId)) {
                        targetScore = s;
                        break;
                    }
                }
            }
        %>

        <div class="form-panel">
            <% if (targetScore != null) { %>
            <form action="${pageContext.request.contextPath}/score/update" method="post">
                <!-- 隐藏域传递主键 -->
                <input type="hidden" name="scoreId" value="<%=targetScore.getScoreId()%>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                    <div class="input-group">
                        <label>成绩ID (不可修改)</label>
                        <input type="text" value="<%=targetScore.getScoreId()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>
                    
                    <div class="input-group">
                        <label>学生学号 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="studentId" value="<%=targetScore.getStudentId()%>" required>
                    </div>

                    <div class="input-group">
                        <label>课程ID <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="courseId" value="<%=targetScore.getCourseId()%>" required>
                    </div>

                    <div class="input-group">
                        <label>分数</label>
                        <input type="number" name="score" value="<%=targetScore.getScore() != null ? targetScore.getScore() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>学期</label>
                        <input type="text" name="semester" value="<%=targetScore.getSemester() != null ? targetScore.getSemester() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>考试类型</label>
                        <input type="text" name="examType" value="<%=targetScore.getExamType() != null ? targetScore.getExamType() : ""%>">
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
                    未找到相关成绩信息，请返回重试。
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>