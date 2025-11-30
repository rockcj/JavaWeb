<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.lingnan.Dto.JobDto" %>
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
        <th>学生id</th>
        <th>项目id</th>
        <th>学生名称</th>
        <th>项目名称</th>
        <th>职位名称</th>
    </tr>
    <%
        Object allJobDto = session.getAttribute("StuJobDto");
    %>
    <%
        if(allJobDto !=null){
            List<JobDto> list = (List<JobDto>) allJobDto;
            if(list!=null)
            {
                for(JobDto job : list) {      %>
    <tr>
        <td><%=job.getSid()%></td>
        <td><%=job.getIid()%></td>
        <td><%=job.getSname()%></td>
        <td><%=job.getIname()%></td>
        <td><%=job.getJob()%></td>
    </tr>
    <%
                }
            }
        }
    %>
</table>