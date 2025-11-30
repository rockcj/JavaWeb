<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户表单</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .form-container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            text-align: center;
        }
        .form-group {
            margin: 20px 0;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .info {
            background-color: #e7f3ff;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        a {
            display: inline-block;
            margin-top: 10px;
            color: #4CAF50;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>用户信息表单</h2>
        
        <div class="info">
            <strong>说明：</strong>本表单演示Spring MVC的3种接收数据方式：
            <ul>
                <li>@RequestParam - 接收请求参数</li>
                <li>HttpServletRequest - 从请求对象获取参数</li>
                <li>@ModelAttribute - 绑定对象属性</li>
            </ul>
        </div>

        <form action="${pageContext.request.contextPath}/user/submit" method="post">
            <div class="form-group">
                <label for="name">姓名：</label>
                <input type="text" id="name" name="name" required placeholder="请输入姓名">
            </div>
            
            <div class="form-group">
                <label for="age">年龄：</label>
                <input type="number" id="age" name="age" required placeholder="请输入年龄" min="1" max="150">
            </div>
            
            <button type="submit">提交</button>
        </form>
        
        <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
    </div>
</body>
</html>

