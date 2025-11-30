<%--
  Created by IntelliJ IDEA.
  User: 26818
  Date: 2025/4/22
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #333;
            font-size: 36px;
            margin-bottom: 20px;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<%
    if(session.getAttribute("user")==null)
    {
        response.sendRedirect("login");
    }
%>
<%
    if(session.getAttribute("flag")=="正常")
    {%>
    <h1>岭南师范学院</h1>
    欢迎您：${user}&nbsp;先生/女士
    <br><br>
    <a href="login">退出</a>
    <a href="loginout">用户注销</a>
    <a href="student/queryAll">管理员界面</a>
<%
    }
    else
    {%>
<h1>岭南师范学院</h1>
    ${user}&nbsp;${flag}&nbsp;请耐心等待
    <br><br>
    <a href="login">退出</a>
<%
    }
%>
</body>
</html>