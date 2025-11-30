<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.lingnan.pojo.Item" %>
<%@ page import="cn.edu.lingnan.pojo.Job" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
    <title>编辑职位表</title>
</head>

<body>
<h1>岭南师范学院学生管理系统———学生管理模块</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="../login">退出</a>
<a href="../loginout">用户注销</a>
<a href="../job/queryAll">查询职位信息</a>
<hr>

<form  action="../job/update">
    <table>
        <tr>
            <th>学生id</th>
            <th>项目id</th>
            <th>职位名称</th>
            <th>职位权限</th>
            <th>操作选择</th>
        </tr>
        <%
            Object allJob = session.getAttribute("allJob");
            String iid = request.getParameter("iid");
            String sid = request.getParameter("sid");
        %>
        <%
            if(allJob !=null){
                List<Job> list = (List<Job>) allJob;
                if(list!=null)
                {
                    for(Job job : list) {
                        if(job.getIid().equals(iid)&&job.getSid().equals(sid)){%>
        <tr>
            <td><input type="hidden" name="sid" value="<%=job.getSid()%>"><%=job.getSid()%></td>
            <td><input type="hidden" name="iid" value="<%=job.getIid()%>"><%=job.getIid()%></td>
            <td><input type="text" name="job" value="<%=job.getJob()%>"></td>
            <td><input type="text" name="scflag" value="<%=job.getScflag()%>"></td>
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