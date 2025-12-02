<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar">
    <div class="logo-container">
        <div class="logo">USER CENTER</div>
        <div style="font-size: 0.8rem; color: var(--text-secondary); letter-spacing: 4px; margin-top: 5px;">STUDENT</div>
    </div>
    
    <ul class="nav-menu">
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/oneStu" class="nav-link <%=request.getRequestURI().contains("Stu.jsp")?"active":""%>">
                <span class="nav-icon">👤</span> 个人信息
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/details" class="nav-link <%=request.getRequestURI().contains("Details.jsp")?"active":""%>">
                <span class="nav-icon">ℹ️</span> 详细信息
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/user/myScores" class="nav-link <%=request.getRequestURI().contains("myScores.jsp")?"active":""%>">
                <span class="nav-icon">📊</span> 我的成绩
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/user/myTimeTable" class="nav-link <%=request.getRequestURI().contains("myTimeTable.jsp")?"active":""%>">
                <span class="nav-icon">📅</span> 我的课表
            </a>
        </li>
    </ul>

    <div class="user-info">
        <span class="user-name">
            <span style="color: var(--success);">●</span> 
            ${sessionScope.user != null ? sessionScope.user : "未登录"}
        </span>
        <div class="user-actions">
            <a href="${pageContext.request.contextPath}/login" class="btn-link danger">退出</a>
            <span style="color: var(--border-color)">|</span>
            <a href="${pageContext.request.contextPath}/loginout" class="btn-link danger">注销</a>
        </div>
    </div>
</div>
