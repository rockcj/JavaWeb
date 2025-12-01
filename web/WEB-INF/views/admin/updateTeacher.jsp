<%@ page import="cn.edu.lingnan.pojo.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>编辑教师</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———编辑教师信息</h1>
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

<form action="${pageContext.request.contextPath}/teacher/update" method="post">
    <table>
        <tr>
            <th>教师ID</th>
            <th>教师姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>电话</th>
            <th>邮箱</th>
            <th>所属院系ID</th>
            <th>职称</th>
            <th>状态标志</th>
            <th>操作选择</th>
        </tr>
        <%
            Object allTeacher = session.getAttribute("allTeacher");
            if(allTeacher == null) {
                allTeacher = request.getAttribute("allTeacher");
            }
            String teacherId = request.getParameter("teacherId");
        %>
        <%
            if(allTeacher != null && teacherId != null){
                List<Teacher> list = (List<Teacher>) allTeacher;
                if(list != null)
                {
                    for(Teacher teacher : list) {
                        if(teacher.getTeacherId() != null && teacher.getTeacherId().equals(teacherId)){
        %>
        <tr>
            <td>
                <input type="hidden" name="teacherId" value="<%=teacher.getTeacherId() != null ? teacher.getTeacherId() : ""%>">
                <%=teacher.getTeacherId() != null ? teacher.getTeacherId() : ""%>
            </td>
            <td><input type="text" name="teacherName" value="<%=teacher.getTeacherName() != null ? teacher.getTeacherName() : ""%>"></td>
            <td><input type="text" name="gender" value="<%=teacher.getGender() != null ? teacher.getGender() : ""%>"></td>
            <td><input type="text" name="age" value="<%=teacher.getAge() != null ? teacher.getAge() : ""%>"></td>
            <td><input type="text" name="phone" value="<%=teacher.getPhone() != null ? teacher.getPhone() : ""%>"></td>
            <td><input type="text" name="email" value="<%=teacher.getEmail() != null ? teacher.getEmail() : ""%>"></td>
            <td><input type="text" name="deptId" value="<%=teacher.getDeptId() != null ? teacher.getDeptId() : ""%>"></td>
            <td><input type="text" name="title" value="<%=teacher.getTitle() != null ? teacher.getTitle() : ""%>"></td>
            <td><input type="text" name="flag" value="<%=teacher.getFlag() != null ? teacher.getFlag() : 0%>"></td>
            <td>
                <input type="submit" value="确认修改">
                <a href="${pageContext.request.contextPath}/teacher/queryAll">取消</a>
            </td>
        </tr>
        <%
                        }
                    }
                }
            } else {
        %>
        <tr>
            <td colspan="10" style="text-align: center; color: red;">
                未找到要编辑的教师信息，请返回列表页面。
                <a href="${pageContext.request.contextPath}/teacher/queryAll">返回列表</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>

