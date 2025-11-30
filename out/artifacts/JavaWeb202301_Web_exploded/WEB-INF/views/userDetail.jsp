<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户详情</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .detail-container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #4CAF50;
        }
        .message {
            padding: 15px;
            background-color: #e7f3ff;
            border-radius: 5px;
            margin: 20px 0;
        }
        a {
            display: inline-block;
            margin-top: 20px;
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
    <div class="detail-container">
        <h2>用户详情</h2>
        <div class="message">
            <p>${message}</p>
            <p><strong>说明：</strong>这是通过ModelAndView方式跳转的页面</p>
        </div>
        <a href="${pageContext.request.contextPath}/user/list">返回列表</a>
        <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
    </div>
</body>
</html>

