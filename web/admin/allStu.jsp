<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <head>
     <script src="../js/check.js"></script>
     <link rel="stylesheet" type="text/css" href="../css/list.css">
     <title>岭南师范学院学生管理系统———学生信息</title>
 </head>
<body>
<h1>岭南师范学院学生管理系统———学生信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="../student/queryAll">查询所有学生信息</a>
<a href="../student/queryAll?flag=1">审核通过学生信息</a>
<a href="../student/queryAll?flag=0">待审核学生信息</a>
<hr>
<div class="sidebar">
    <a href="../student/queryAll">查询学生信息</a>
    <a href="../item/queryAll">查询项目信息</a>
    <a href="../job/queryAll">查询职位信息</a>
    <a href="../course/queryAll">查询课程信息</a>
    <a href="../department/queryAll">查询院系信息</a>
    <a href="../score/queryAll">查询成绩信息</a>
    <a href="../teacher/queryAll">查询教师信息</a>
    <a href="../timetable/queryAll">查询课程表信息</a>
    <a href="../login">退出</a>
    <a href="../loginout">用户注销</a>
</div>
    <%
        Object allStu = session.getAttribute("allStu");
        Object  check = session.getAttribute("ok");
    %>
<div class="main-content">
    <table>
        <tr>
            <th><input type="checkbox" onclick="allcheck(this);"></th>
            <th>学生学号</th>
            <th>学生姓名</th>
            <th>学生密码</th>
            <th>学生权限</th>
            <th>备用信息</th>
            <th>
            <%
                if(check!=null){
                    if((Integer) check == 0){
            %>
            <input  type="submit" value="批量通过" onclick="OKcheck();">&nbsp; | &nbsp;
            <%}
            }%>
            <input  type="submit" value="批量删除" onclick="delcheck('${pageContext.request.contextPath}/student/delete', 'sid');">
            </th>
        </tr>
        <%
            if(allStu!=null){
                List<Student> list = (List<Student>) allStu;
                if(list!=null)
                {
                    for(Student student : list) {      %>
        <tr>
            <td><input type="checkbox" name="check" value="<%=student.getSid()%>"></td>
            <td><%=student.getSid()%></td>
            <td><%=student.getSname()%></td>
            <td><%=student.getSpassword()%></td>
            <td><%=student.getSright()%></td>
            <td><%=student.getStflag()%></td>
            <%
                if(check==null||(Integer) check == 1){
                    %>
            <td>
                <a href="updateStu.jsp?sid=<%=student.getSid()%>">修改</a> &nbsp; | &nbsp;
                <a href="../student/delete?sid=<%=student.getSid()%>"
                   onclick="return confirm('确定要删除吗？')">删除</a>
            </td>
                    <%
                }
                else{
                    %>
            <td>
                <a href="../student/check?sid=<%=student.getSid()%>">通过</a> &nbsp; | &nbsp;
                <a href="../student/delete?sid=<%=student.getSid()%>"
                   onclick="return confirm('确定要删除吗？')">删除</a>
            </td>
                    <%
                }%>
        </tr>
        <%
                    }
                }
            }
        %>
    </table>
    <a href="addStu.jsp" class="add-item-btn">+</a>
</div>
