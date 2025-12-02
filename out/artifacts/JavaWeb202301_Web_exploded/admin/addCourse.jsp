<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>添加课程 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="../js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>新增课程</h1>
            <a href="${pageContext.request.contextPath}/course/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
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
                                <th>课程ID <span style="color: var(--danger);">*</span></th>
                                <th>课程名称 <span style="color: var(--danger);">*</span></th>
                                <th>课程代码 <span style="color: var(--danger);">*</span></th>
                                <th>所属院系</th>
                                <th>学分</th>
                                <th>学时</th>
                                <th>类型</th>
                                <th>授课教师</th>
                                <th>学期</th>
                                <th>描述</th>
                            </tr>
                        </thead>
                        <tbody id="TableBody">
                            <!-- 默认显示一行 -->
                            <tr>
                                <td><input type='text' name='courseId' placeholder="如: C001"></td>
                                <td><input type='text' name='courseName' placeholder="课程名"></td>
                                <td><input type='text' name='courseCode' placeholder="代码"></td>
                                <td><input type='text' name='deptId' placeholder="院系ID"></td>
                                <td><input type='number' name='credit' placeholder="学分"></td>
                                <td><input type='number' name='courseHours' placeholder="学时"></td>
                                <td><input type='text' name='courseType' placeholder="类型"></td>
                                <td><input type='text' name='teacherName' placeholder="教师名"></td>
                                <td><input type='text' name='semester' placeholder="学期"></td>
                                <td><input type='text' name='courseDesc' placeholder="描述"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div style="display: flex; gap: 15px;">
                    <button type="button" class="btn btn-primary" onclick="addCourseRow()">
                        <span style="font-size: 1.2rem;">+</span> 添加空白行
                    </button>
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000;" onclick="insertCourse(event)">
                        💾 提交保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>