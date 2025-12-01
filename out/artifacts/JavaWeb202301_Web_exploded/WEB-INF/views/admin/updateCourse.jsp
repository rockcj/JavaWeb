<%@ page import="cn.edu.lingnan.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>编辑课程</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———编辑课程信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="${pageContext.request.contextPath}/login">退出</a>
<a href="${pageContext.request.contextPath}/loginout">用户注销</a>
<a href="${pageContext.request.contextPath}/student/queryAll">查询学生信息</a>
<a href="${pageContext.request.contextPath}/item/queryAll">查询项目信息</a>
<a href="${pageContext.request.contextPath}/job/queryAll">查询职位信息</a>
<a href="${pageContext.request.contextPath}/course/queryAll">查询课程信息</a>
<a href="${pageContext.request.contextPath}/department/queryAll">查询院系信息</a>
<a href="${pageContext.request.contextPath}/score/queryAll">查询成绩信息</a>
<a href="${pageContext.request.contextPath}/teacher/queryAll">查询教师信息</a>
<a href="${pageContext.request.contextPath}/timetable/queryAll">查询课程表信息</a>
<hr>

<form action="${pageContext.request.contextPath}/course/update" method="post">
    <table>
        <tr>
            <th>课程ID</th>
            <th>课程名称</th>
            <th>课程编码</th>
            <th>所属院系ID</th>
            <th>学分</th>
            <th>学时</th>
            <th>课程类型</th>
            <th>任课教师</th>
            <th>学期</th>
            <th>课程描述</th>
            <th>状态标志</th>
            <th>操作选择</th>
        </tr>
        <%
            Object allCourse = session.getAttribute("allCourse");
            if(allCourse == null) {
                allCourse = request.getAttribute("allCourse");
            }
            String courseId = request.getParameter("courseId");
        %>
        <%
            if(allCourse != null && courseId != null){
                List<Course> list = (List<Course>) allCourse;
                if(list != null)
                {
                    for(Course course : list) {
                        if(course.getCourseId() != null && course.getCourseId().equals(courseId)){
        %>
        <tr>
            <td>
                <input type="hidden" name="courseId" value="<%=course.getCourseId() != null ? course.getCourseId() : ""%>">
                <%=course.getCourseId() != null ? course.getCourseId() : ""%>
            </td>
            <td><input type="text" name="courseName" value="<%=course.getCourseName() != null ? course.getCourseName() : ""%>"></td>
            <td><input type="text" name="courseCode" value="<%=course.getCourseCode() != null ? course.getCourseCode() : ""%>"></td>
            <td><input type="text" name="deptId" value="<%=course.getDeptId() != null ? course.getDeptId() : ""%>"></td>
            <td><input type="text" name="credit" value="<%=course.getCredit() != null ? course.getCredit() : ""%>"></td>
            <td><input type="text" name="courseHours" value="<%=course.getCourseHours() != null ? course.getCourseHours() : ""%>"></td>
            <td><input type="text" name="courseType" value="<%=course.getCourseType() != null ? course.getCourseType() : ""%>"></td>
            <td><input type="text" name="teacherName" value="<%=course.getTeacherName() != null ? course.getTeacherName() : ""%>"></td>
            <td><input type="text" name="semester" value="<%=course.getSemester() != null ? course.getSemester() : ""%>"></td>
            <td><textarea name="courseDesc" rows="2" cols="20"><%=course.getCourseDesc() != null ? course.getCourseDesc() : ""%></textarea></td>
            <td><input type="text" name="cflag" value="<%=course.getCflag() != null ? course.getCflag() : 0%>"></td>
            <td>
                <input type="submit" value="确认修改">
                <a href="${pageContext.request.contextPath}/course/queryAll">取消</a>
            </td>
        </tr>
        <%
                        }
                    }
                }
            } else {
        %>
        <tr>
            <td colspan="12" style="text-align: center; color: red;">
                未找到要编辑的课程信息，请返回列表页面。
                <a href="${pageContext.request.contextPath}/course/queryAll">返回列表</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>

