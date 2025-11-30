<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring MVC 实验首页</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .link-section {
            margin: 20px 0;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }
        .link-section h2 {
            color: #555;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 10px;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #45a049;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Spring MVC 框架实验</h1>
        
        <div class="link-section">
            <h2>基础实验</h2>
            <ul>
                <li><a href="hello">Hello Spring MVC</a> - 基础控制器演示</li>
            </ul>
        </div>

        <div class="link-section">
            <h2>数据传递实验（注解方式Controller）</h2>
            <ul>
                <li><a href="user/form">用户表单页面</a> - 演示3种接收数据方式和3种传递数据方式</li>
                <li><a href="user/list">用户列表页面</a> - 演示页面跳转</li>
            </ul>
        </div>

        <div class="link-section">
            <h2>系统功能</h2>
            <ul>
                <li><a href="Login.html">登录页面</a> - 用户登录入口</li>
                <li><a href="register">注册页面</a> - 用户注册</li>
            </ul>
        </div>
    </div>
</body>
</html>

