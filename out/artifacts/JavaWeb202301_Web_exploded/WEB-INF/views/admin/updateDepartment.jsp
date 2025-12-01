<%@ page import="cn.edu.lingnan.pojo.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>编辑院系</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———编辑院系信息</h1>
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

<form action="${pageContext.request.contextPath}/department/update" method="post">
    <table>
        <tr>
            <th>院系ID</th>
            <th>院系名称</th>
            <th>院系编码</th>
            <th>院系负责人</th>
            <th>联系电话</th>
            <th>邮箱</th>
            <th>院系描述</th>
            <th>状态标志</th>
            <th>操作选择</th>
        </tr>
        <%
            Object allDepartment = session.getAttribute("allDepartment");
            if(allDepartment == null) {
                allDepartment = request.getAttribute("allDepartment");
            }
            String deptId = request.getParameter("deptId");
        %>
        <%
            if(allDepartment != null && deptId != null){
                List<Department> list = (List<Department>) allDepartment;
                if(list != null)
                {
                    for(Department department : list) {
                        if(department.getDeptId() != null && department.getDeptId().equals(deptId)){
        %>
        <tr>
            <td>
                <input type="hidden" name="deptId" value="<%=department.getDeptId() != null ? department.getDeptId() : ""%>">
                <%=department.getDeptId() != null ? department.getDeptId() : ""%>
            </td>
            <td><input type="text" name="deptName" value="<%=department.getDeptName() != null ? department.getDeptName() : ""%>"></td>
            <td><input type="text" name="deptCode" value="<%=department.getDeptCode() != null ? department.getDeptCode() : ""%>"></td>
            <td><input type="text" name="deptHead" value="<%=department.getDeptHead() != null ? department.getDeptHead() : ""%>"></td>
            <td><input type="text" name="deptPhone" value="<%=department.getDeptPhone() != null ? department.getDeptPhone() : ""%>"></td>
            <td><input type="text" name="deptEmail" value="<%=department.getDeptEmail() != null ? department.getDeptEmail() : ""%>"></td>
            <td><textarea name="deptDesc" rows="2" cols="20"><%=department.getDeptDesc() != null ? department.getDeptDesc() : ""%></textarea></td>
            <td><input type="text" name="dflag" value="<%=department.getDflag() != null ? department.getDflag() : 0%>"></td>
            <td>
                <input type="submit" value="确认修改">
                <a href="${pageContext.request.contextPath}/department/queryAll">取消</a>
            </td>
        </tr>
        <%
                        }
                    }
                }
            } else {
        %>
        <tr>
            <td colspan="9" style="text-align: center; color: red;">
                未找到要编辑的院系信息，请返回列表页面。
                <a href="${pageContext.request.contextPath}/department/queryAll">返回列表</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>

