<%--
  Created by IntelliJ IDEA.
  User: 26818
  Date: 2025/5/21
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <script src="./js/check.js"></script>
    <meta charset="UTF-8">
    <!-- Design by foolishdeveloper.com -->
    <title>地球人登录页面</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <!--Stylesheet-->
    <style media="screen">
        *,
        *:before,
        *:after{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
        body{
            background-color: #080710;
        }
        .background{
            width: 360px;
            height: 500px;
            position: absolute;
            transform: translate(-50%,-50%);
            left: 50%;
            top: 50%;
        }
        .background .shape{
            height: 150px;
            width: 150px;
            position: absolute;
            border-radius: 50%;
        }
        .shape:first-child{
            background: linear-gradient(
                    #1845ad,
                    #23a2f6
            );
            left: -50px;
            top: -22%;
        }
        .shape:last-child{
            background: linear-gradient(
                    to right,
                    #ff512f,
                    #f09819
            );
            right: -56px;
            bottom: -22%;
        }
        form{
            height: 580px;
            width: 350px;
            background-color: rgba(255,255,255,0.13);
            position: absolute;
            transform: translate(-50%,-50%);
            top: 50%;
            left: 50%;
            border-radius: 10px;
            backdrop-filter: blur(10px);
            border: 2px solid rgba(255,255,255,0.1);
            box-shadow: 0 0 40px rgba(8,7,16,0.6);
            padding: 32px 32px;
        }
        form *{
            font-family: 'Poppins',sans-serif;
            color: #ffffff;
            letter-spacing: 0.5px;
            outline: none;
            border: none;
        }
        form h3{
            font-size: 32px;
            font-weight: 500;
            line-height: 35px;
            text-align: center;
        }

        label{
            display: block;
            margin-top: 30px;
            font-size: 16px;
            font-weight: 500;
        }
        input{
            display: block;
            height: 35px;
            width: 100%;
            background-color: rgba(255,255,255,0.07);
            border-radius: 3px;
            padding: 0 10px;
            margin-top: 8px;
            font-size: 14px;
            font-weight: 300;
        }
        ::placeholder{
            color: #e5e5e5;
        }
        button{
            margin-top: 30px;
            width: 100%;
            background-color: #ffffff;
            color: #080710;
            padding: 10px 0;
            font-size: 18px;
            font-weight: 600;
            border-radius: 5px;
            cursor: pointer;
        }
        .social{
            margin-top: 25px;
            display: flex;
        }
        .social div{
            background: red;
            width: 150px;
            border-radius: 3px;
            padding: 5px 10px 10px 5px;
            background-color: rgba(255,255,255,0.27);
            color: #eaf0fb;
            text-align: center;
        }
        .social div:hover{
            background-color: rgba(255,255,255,0.47);
        }
        .social .fb{
            margin-left: 25px;
        }
        .social i{
            margin-right: 4px;
        }
    </style>
</head>
<body>
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>
<form action="register" method="post" onsubmit="return checkForm()">
    <h3>注册</h3>
    <%-- 显示错误信息 --%>
    <%
        String errorId = (String) request.getAttribute("errorId");
        String errorName = (String) request.getAttribute("errorName");
        if (errorId != null && !errorId.isEmpty()) {
    %>
        <p style="color:red;"><%= errorId %></p>
    <%
        }
        if (errorName != null && !errorName.isEmpty()) {
    %>
        <p style="color:red;"><%= errorName %></p>
    <%
        }
    %>
    <div class="input-group">
        <label for="userId">用户ID</label>
        <input type="text" placeholder="用户ID如007" id="userId" name="userId" required>
    </div>

    <div class="input-group">
        <label for="username">用户名</label>
        <input type="text" placeholder="请输入用户名" id="username" name="username" required>
    </div>

    <div class="input-group">
        <label for="password">密码</label>
        <input type="password" placeholder="请输入密码" id="password" required>
    </div>

    <div class="input-group">
        <label for="OKPassword">确认密码</label>
        <input type="password" placeholder="请再次输入密码" id="OKPassword" name="OKPassword" required>
    </div>

    <button type="submit">注册</button>

<%--    <div class="social">--%>
<%--        <div class="go"><i class="fab fa-google"></i>  Google</div>--%>
<%--        <div class="fb"><i class="fab fa-facebook"></i>  Facebook</div>--%>
<%--    </div>--%>
</form>
<style>
.input-group {
    margin-bottom: 20px;
}
.input-group label {
    display: block;
    margin-bottom: 8px;
    font-weight: 500;
}
.input-group input {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    font-size: 14px;
}
</style>

</body>
</html>
