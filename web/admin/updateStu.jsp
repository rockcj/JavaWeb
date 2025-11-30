<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
    <title>编辑学生表</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———学生管理模块</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="../login">退出</a>
<a href="../loginout">用户注销</a>
<a href="../student/queryAll">查询学生信息</a>
<hr>

<form  action="../student/update">
<table>
    <tr>
        <th>学生学号</th>
        <th>学生姓名</th>
        <th>学生密码</th>
        <th>学生权限</th>
        <th>备用信息</th>
        <th>操作选择</th>
    </tr>
    <%
        Object allStu = session.getAttribute("allStu");
        String sid = request.getParameter("sid");
    %>
    <%
        if(allStu!=null){
            List<Student> list = (List<Student>) allStu;
            if(list!=null)
            {
                for(Student student : list) {
                    if(student.getSid().equals(sid)){%>
    <tr>
        <td><input type="hidden" name="sid" value="<%=student.getSid()%>"><%=student.getSid()%></td>
        <td><input type="text" name="sname" value="<%=student.getSname()%>"></td>
        <td><input type="text" name="spassword" value="<%=student.getSpassword()%>"></td>
        <td><input type="text" name="sright" value="<%=student.getSright()%>"></td>
        <td><input type="text" name="stflag" value="<%=student.getStflag()%>"></td>
        <td>
            <input  type="submit" value="确认修改">
        </td>
    </tr>
    <%
                    }
                }
            }
        }
    %>
</table>
</form>