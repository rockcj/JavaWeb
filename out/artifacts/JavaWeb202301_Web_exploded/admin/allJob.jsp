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
<h1>岭南师范学院学生管理系统———职位信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="../login">退出</a>
<a href="../loginout">用户注销</a>
<a href="../student/queryAll">查询学生信息</a>
<a href="../item/queryAll">查询项目信息</a>
<a href="../job/queryAll">查询职位信息</a>
<a href="../course/queryAll">查询课程信息</a>
<a href="../department/queryAll">查询院系信息</a>
<a href="../score/queryAll">查询成绩信息</a>
<a href="../teacher/queryAll">查询教师信息</a>
<a href="../timetable/queryAll">查询课程表信息</a>
<hr>
<table>
    <tr>
        <th><input type="checkbox" onclick="allcheck(this);"></th>
        <th>学生id</th>
        <th>项目id</th>
        <th>职位名称</th>
        <th>职位权限</th>
        <th><input  type="submit" value="批量删除" onclick="delcheck('../job/delete','sid','iid');"></th>
    </tr>
    <%
        Object allJob = session.getAttribute("allJob");
    %>
    <%
        if(allJob !=null){
            List<Job> list = (List<Job>) allJob;
            if(list!=null)
            {
                for(Job job : list) {      %>
    <tr>
        <td><input type="checkbox" name="check" value="<%=job.getSid()%>,<%=job.getIid()%>"></td>
        <td><%=job.getSid()%></td>
        <td><%=job.getIid()%></td>
        <td><%=job.getJob()%></td>
        <td><%=job.getScflag()%></td>
        <td>
            <a href="updateJob.jsp?sid=<%=job.getSid()%>&iid=<%=job.getIid()%>">修改</a> &nbsp; | &nbsp;
            <a href="../job/delete?sid=<%=job.getSid()%>&iid=<%=job.getIid()%>"
               onclick="return confirm('确定要删除吗？')">删除</a>
        </td>
    </tr>
    <%
                }
            }
        }
    %>
</table>
<a href="addJob.jsp" class="add-item-btn">+</a>