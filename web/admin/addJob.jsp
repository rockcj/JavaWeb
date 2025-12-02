<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>添加职位 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="../js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>新增职位</h1>
            <a href="${pageContext.request.contextPath}/job/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <div class="form-panel">
            <div style="margin-bottom: 20px; color: var(--text-secondary);">
                💡 提示：点击“添加空白行”可一次性录入多条数据，确认无误后点击“提交保存”。
            </div>

            <form>
                <div class="table-container" style="margin-top: 0; margin-bottom: 30px;">
                    <table class="tech-table" id="itemTable">
                        <thead>
                            <tr>
                                <th>学生学号 <span style="color: var(--danger);">*</span></th>
                                <th>项目ID <span style="color: var(--danger);">*</span></th>
                                <th>职位 <span style="color: var(--danger);">*</span></th>
                                <th>状态 <span style="color: var(--danger);">*</span></th>
                            </tr>
                        </thead>
                        <tbody id="TableBody">
                            <!-- 默认显示一行 -->
                            <tr>
                                <td><input type='text' name='sid' placeholder="学号"></td>
                                <td><input type='text' name='iid' placeholder="项目ID"></td>
                                <td><input type='text' name='job' placeholder="职位"></td>
                                <td><input type='text' name='scflag' placeholder="1:启用, 0:停用"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div style="display: flex; gap: 15px;">
                    <button type="button" class="btn btn-primary" onclick="addJobRow()">
                        <span style="font-size: 1.2rem;">+</span> 添加空白行
                    </button>
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000;" onclick="insertJob(event)">
                        💾 提交保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>