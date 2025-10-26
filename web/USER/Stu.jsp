<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.lingnan.pojo.Item" %>
<%@ page import="cn.edu.lingnan.pojo.Job" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="../js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
    <title>职位信息</title>
</head>
<body>
<h1>岭南师范学院学生管理系统个人信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="/login">退出</a>
<a href="/loginout">用户注销</a>
<a href="/oneStu">查询个人信息</a>
<a href="/details">查询详细信息</a>
<hr>
<table>
    <tr>
        <th>学生学号</th>
        <th>学生姓名</th>
        <th>学生密码</th>
        <th>学生权限</th>
        <th>备用信息</th>
    </tr>
    <%
        Object OneStu = session.getAttribute("stu");
    %>
    <%
        if(OneStu !=null){
            Student student = (Student) OneStu;
    %>
    <tr>
        <td><%=student.getSid()%></td>
        <td><%=student.getSname()%></td>
        <td><%=student.getSpassword()%></td>
        <td><%=student.getSright()%></td>
        <td><%=student.getStflag()%></td>
    </tr>
    <%
        }
    %>
</table>