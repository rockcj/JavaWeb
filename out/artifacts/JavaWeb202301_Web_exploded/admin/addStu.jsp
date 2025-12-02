<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>添加学生 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="../js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>新增学生</h1>
            <a href="${pageContext.request.contextPath}/student/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
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
                                <th>学生姓名 <span style="color: var(--danger);">*</span></th>
                                <th>学生密码 <span style="color: var(--danger);">*</span></th>
                                <th>学生权限 <span style="color: var(--danger);">*</span></th>
                                <th>状态标志</th>
                            </tr>
                        </thead>
                        <tbody id="TableBody">
                            <!-- 默认显示一行 -->
                            <tr>
                                <td><input type='text' name='sid' placeholder="如: 2023001"></td>
                                <td><input type='text' name='sname' placeholder="姓名"></td>
                                <td><input type='password' name='spassword' placeholder="密码"></td>
                                <td><input type='text' name='sright' placeholder="1:管理员, 0:普通用户"></td>
                                <td><input type='text' name='stflag' placeholder="1:通过, 0:待审核"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div style="display: flex; gap: 15px;">
                    <button type="button" class="btn btn-primary" onclick="addStuRow()">
                        <span style="font-size: 1.2rem;">+</span> 添加空白行
                    </button>
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000;" onclick="insertStu(event)">
                        💾 提交保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>