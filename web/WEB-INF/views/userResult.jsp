<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>提交结果</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .result-container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #4CAF50;
            text-align: center;
        }
        .data-section {
            margin: 20px 0;
            padding: 15px;
            background-color: #f9f9f9;
            border-radius: 5px;
            border-left: 4px solid #4CAF50;
        }
        .data-section h3 {
            color: #333;
            margin-top: 0;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 5px 0;
            border-bottom: 1px solid #eee;
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
    <div class="result-container">
        <h2>数据传递演示结果</h2>
        
        <!-- 方式1：Model传递数据 -->
        <div class="data-section">
            <h3>方式1 - Model传递数据</h3>
            <p><strong>消息：</strong>${message}</p>
            <p><strong>姓名：</strong>${name}</p>
            <p><strong>年龄：</strong>${age}</p>
        </div>

        <!-- 方式2：Model传递对象 -->
        <div class="data-section">
            <h3>方式2 - Model传递对象</h3>
            <p><strong>用户对象：</strong></p>
            <ul>
                <li>姓名：${user.name}</li>
                <li>年龄：${user.age}</li>
            </ul>
        </div>

        <!-- 方式3：HttpSession传递数据 -->
        <div class="data-section">
            <h3>方式3 - HttpSession传递数据</h3>
            <p><strong>Session消息：</strong>${sessionScope.sessionMessage}</p>
            <p><strong>Session用户名：</strong>${sessionScope.userName}</p>
        </div>

        <!-- 方式4：Model传递集合 -->
        <div class="data-section">
            <h3>方式4 - Model传递集合</h3>
            <p><strong>爱好列表：</strong></p>
            <ul>
                <c:if test="${not empty hobbies}">
                    <c:forEach var="hobby" items="${hobbies}">
                        <li>${hobby}</li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>

        <!-- 方式5：Model传递Map -->
        <div class="data-section">
            <h3>方式5 - Model传递Map</h3>
            <ul>
                <c:if test="${not empty info}">
                    <c:forEach var="entry" items="${info}">
                        <li><strong>${entry.key}：</strong>${entry.value}</li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>

        <a href="${pageContext.request.contextPath}/user/form">返回表单</a>
        <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
    </div>
</body>
</html>

