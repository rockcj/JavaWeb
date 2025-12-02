<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar">
    <div class="logo-container">
        <div class="logo">SCHOOL SYS</div>
        <div style="font-size: 0.8rem; color: var(--text-secondary); letter-spacing: 4px; margin-top: 5px;">MANAGEMENT</div>
    </div>
    
    <ul class="nav-menu">
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/main.jsp" class="nav-link <%=request.getRequestURI().contains("main.jsp")?"active":""%>">
                <span class="nav-icon">🏠</span> 仪表盘
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/student/queryAll" class="nav-link <%=request.getRequestURI().contains("Stu")?"active":""%>">
                <span class="nav-icon">👨‍🎓</span> 学生管理
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/department/queryAll" class="nav-link <%=request.getRequestURI().contains("Department")?"active":""%>">
                <span class="nav-icon">🏢</span> 院系管理
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/teacher/queryAll" class="nav-link <%=request.getRequestURI().contains("Teacher")?"active":""%>">
                <span class="nav-icon">👨‍🏫</span> 教师管理
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/course/queryAll" class="nav-link <%=request.getRequestURI().contains("Course")?"active":""%>">
                <span class="nav-icon">📚</span> 课程管理
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/score/queryAll" class="nav-link <%=request.getRequestURI().contains("Score")?"active":""%>">
                <span class="nav-icon">📊</span> 成绩管理
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/timetable/queryAll" class="nav-link <%=request.getRequestURI().contains("TimeTable")?"active":""%>">
                <span class="nav-icon">📅</span> 课表管理
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/item/queryAll" class="nav-link <%=request.getRequestURI().contains("Item")?"active":""%>">
                <span class="nav-icon">🧪</span> 项目管理
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/job/queryAll" class="nav-link <%=request.getRequestURI().contains("Job")?"active":""%>">
                <span class="nav-icon">💼</span> 职位管理
            </a>
        </li>
    </ul>

    <div class="user-info">
        <span class="user-name">
            <span style="color: var(--accent-color);">●</span> 
            ${sessionScope.user != null ? sessionScope.user : "未登录"}
        </span>
        <div class="user-actions">
            <a href="${pageContext.request.contextPath}/login" class="btn-link danger">退出</a>
            <span style="color: var(--border-color)">|</span>
            <a href="${pageContext.request.contextPath}/loginout" class="btn-link danger">注销</a>
        </div>
    </div>
</div>