<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>添加教师 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="../js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>新增教师</h1>
            <a href="${pageContext.request.contextPath}/teacher/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
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
                                <th>教师工号 <span style="color: var(--danger);">*</span></th>
                                <th>教师姓名 <span style="color: var(--danger);">*</span></th>
                                <th>性别</th>
                                <th>年龄</th>
                                <th>联系电话</th>
                                <th>邮箱</th>
                                <th>所属院系</th>
                                <th>职称</th>
                            </tr>
                        </thead>
                        <tbody id="TableBody">
                            <!-- 默认显示一行 -->
                            <tr>
                                <td><input type='text' name='teacherId' placeholder="如: T001"></td>
                                <td><input type='text' name='teacherName' placeholder="姓名"></td>
                                <td><input type='text' name='gender' placeholder="男/女"></td>
                                <td><input type='number' name='age' placeholder="年龄"></td>
                                <td><input type='text' name='phone' placeholder="电话"></td>
                                <td><input type='email' name='email' placeholder="邮箱"></td>
                                <td><input type='text' name='deptId' placeholder="院系ID"></td>
                                <td><input type='text' name='title' placeholder="如: 教授"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div style="display: flex; gap: 15px;">
                    <button type="button" class="btn btn-primary" onclick="addTeacherRow()">
                        <span style="font-size: 1.2rem;">+</span> 添加空白行
                    </button>
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000;" onclick="insertTeacher(event)">
                        💾 提交保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>