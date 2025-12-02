<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>添加院系 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="../js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>新增院系</h1>
            <a href="${pageContext.request.contextPath}/department/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
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
                                <th>院系ID <span style="color: var(--danger);">*</span></th>
                                <th>院系名称 <span style="color: var(--danger);">*</span></th>
                                <th>院系编码 <span style="color: var(--danger);">*</span></th>
                                <th>院系负责人</th>
                                <th>联系电话</th>
                                <th>邮箱</th>
                                <th>院系描述</th>
                            </tr>
                        </thead>
                        <tbody id="TableBody">
                            <!-- 默认显示一行 -->
                            <tr>
                                <td><input type='text' name='deptId' placeholder="如: D001"></td>
                                <td><input type='text' name='deptName' placeholder="如: 计算机学院"></td>
                                <td><input type='text' name='deptCode' placeholder="如: CS"></td>
                                <td><input type='text' name='deptHead' placeholder="负责人姓名"></td>
                                <td><input type='text' name='deptPhone' placeholder="联系电话"></td>
                                <td><input type='text' name='deptEmail' placeholder="电子邮箱"></td>
                                <td><input type='text' name='deptDesc' placeholder="简短描述"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div style="display: flex; gap: 15px;">
                    <button type="button" class="btn btn-primary" onclick="addDepartmentRow()">
                        <span style="font-size: 1.2rem;">+</span> 添加空白行
                    </button>
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000;" onclick="insertDepartment(event)">
                        💾 提交保存
                    </button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>