<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>添加课表 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="../js/check.js"></script>
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>新增课表</h1>
            <a href="${pageContext.request.contextPath}/timetable/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
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
                                <th>课表ID <span style="color: var(--danger);">*</span></th>
                                <th>课程ID <span style="color: var(--danger);">*</span></th>
                                <th>教师工号 <span style="color: var(--danger);">*</span></th>
                                <th>教室</th>
                                <th>星期</th>
                                <th>节次</th>
                                <th>学期</th>
                            </tr>
                        </thead>
                        <tbody id="TableBody">
                            <!-- 默认显示一行 -->
                            <tr>
                                <td><input type='text' name='timetableId' placeholder="如: TT001"></td>
                                <td><input type='text' name='courseId' placeholder="课程ID"></td>
                                <td><input type='text' name='teacherId' placeholder="教师工号"></td>
                                <td><input type='text' name='classroomId' placeholder="教室"></td>
                                <td><input type='text' name='dayOfWeek' placeholder="星期几"></td>
                                <td><input type='text' name='timeSlot' placeholder="节次"></td>
                                <td><input type='text' name='semester' placeholder="学期"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div style="display: flex; gap: 15px;">
                    <button type="button" class="btn btn-primary" onclick="addTimeTableRow()">
                        <span style="font-size: 1.2rem;">+</span> 添加空白行
                    </button>
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000;" onclick="insertTimeTable(event)">
                        💾 提交保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>