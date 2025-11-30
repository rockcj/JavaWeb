<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 900px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .list-container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .nav-links {
            margin-top: 20px;
            text-align: center;
        }
        a {
            display: inline-block;
            margin: 5px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="list-container">
        <h2>用户列表</h2>
        
        <table>
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/detail/${user.name}">查看详情</a>
                            <a href="${pageContext.request.contextPath}/user/edit/${user.name}">编辑</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/user/redirect">方式3-重定向跳转</a>
            <a href="${pageContext.request.contextPath}/user/forward">方式4-转发跳转</a>
            <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
        </div>
    </div>
</body>
</html>

