<%@ page import="cn.edu.lingnan.pojo.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    <title>院系管理</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———院系信息</h1>
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
<table>
    <tr>
        <th><input type="checkbox" onclick="allcheck(this);"></th>
        <th>院系ID</th>
        <th>院系名称</th>
        <th>院系编码</th>
        <th>院系负责人</th>
        <th>联系电话</th>
        <th>邮箱</th>
        <th>院系描述</th>
        <th>创建时间</th>
        <th>更新时间</th>
        <th>状态标志</th>
        <th><input type="submit" value="批量删除" onclick="delcheckSingle('${pageContext.request.contextPath}/department/delete','deptId');"></th>
    </tr>
    <%
        Object allDepartment = session.getAttribute("allDepartment");
        if(allDepartment == null) {
            allDepartment = request.getAttribute("allDepartment");
        }
    %>
    <%
        if(allDepartment != null){
            List<Department> list = (List<Department>) allDepartment;
            if(list != null)
            {
                for(Department department : list) {      %>
    <tr>
        <td><input type="checkbox" name="check" value="<%=department.getDeptId()%>"></td>
        <td><%=department.getDeptId() != null ? department.getDeptId() : ""%></td>
        <td><%=department.getDeptName() != null ? department.getDeptName() : ""%></td>
        <td><%=department.getDeptCode() != null ? department.getDeptCode() : ""%></td>
        <td><%=department.getDeptHead() != null ? department.getDeptHead() : ""%></td>
        <td><%=department.getDeptPhone() != null ? department.getDeptPhone() : ""%></td>
        <td><%=department.getDeptEmail() != null ? department.getDeptEmail() : ""%></td>
        <td><%=department.getDeptDesc() != null ? (department.getDeptDesc().length() > 20 ? department.getDeptDesc().substring(0, 20) + "..." : department.getDeptDesc()) : ""%></td>
        <td><%=department.getCreateTime() != null ? department.getCreateTime() : ""%></td>
        <td><%=department.getUpdateTime() != null ? department.getUpdateTime() : ""%></td>
        <td><%=department.getDflag() != null ? department.getDflag() : 0%></td>
        <td>
            <a href="updateDepartment.jsp?deptId=<%=department.getDeptId()%>">修改</a> &nbsp; | &nbsp;
            <a href="${pageContext.request.contextPath}/department/delete?deptId=<%=department.getDeptId()%>"
               onclick="return confirm('确定要删除吗？')">删除</a>
        </td>
    </tr>
    <%
                }
            }
        }
    %>
</table>
<a href="addDepartment.jsp" class="add-item-btn">+</a>
</body>

