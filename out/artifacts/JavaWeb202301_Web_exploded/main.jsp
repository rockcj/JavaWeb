<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系统仪表盘 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="admin/sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>系统仪表盘</h1>
            <div style="color: var(--text-secondary);">欢迎回来，开启高效管理的一天</div>
        </div>

        <div class="dashboard-grid">
            <div class="card">
                <div class="card-title">系统状态</div>
                <div class="card-value" style="color: var(--success);">运行正常</div>
                <div style="margin-top: 10px; font-size: 0.8rem; color: var(--text-secondary);">所有服务在线</div>
            </div>
            
            <div class="card">
                <div class="card-title">当前用户</div>
                <div class="card-value" style="font-size: 1.5rem;">${sessionScope.user != null ? sessionScope.user : "访客"}</div>
                <div style="margin-top: 10px; font-size: 0.8rem; color: var(--text-secondary);">
                    <span style="color: var(--accent-color);">管理员权限</span>
                </div>
            </div>

            <div class="card">
                <div class="card-title">快捷操作</div>
                <div style="margin-top: 15px; display: flex; gap: 10px; flex-wrap: wrap;">
                    <a href="${pageContext.request.contextPath}/student/queryAll" class="btn btn-primary btn-sm">学生查询</a>
                    <a href="${pageContext.request.contextPath}/department/queryAll" class="btn btn-primary btn-sm">院系查询</a>
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-title">系统公告</div>
            <div style="color: var(--text-secondary); line-height: 1.6;">
                <p>欢迎使用岭南师范学院学生管理系统 V2.0（科技版）。</p>
                <p>本系统采用最新的 Java Web 技术构建，界面经过全新升级，提供更好的交互体验。</p>
                <ul style="margin-top: 10px; list-style: disc; padding-left: 20px;">
                    <li>支持全模块数据的增删改查</li>
                    <li>新增批量数据导入功能</li>
                    <li>优化了数据校验和错误提示</li>
                </ul>
            </div>
        </div>
    </div>

</body>
</html>