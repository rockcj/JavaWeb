<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.lingnan.pojo.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="../js/check.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
    <title>项目管理</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———项目信息</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="/login">退出</a>
<a href="/loginout">用户注销</a>
<a href="/queryStuAll">查询学生信息</a>
<a href="/queryItemAll">查询项目信息</a>
<a href="/queryJobAll">查询职位信息</a>
<a href="/All">查询详细信息</a>
<hr>
<table>
    <tr>
        <th><input type="checkbox" onclick="allcheck(this);"></th>
        <th>项目id</th>
        <th>项目名称</th>
        <th>项目权限</th>
        <th><input  type="submit" value="批量删除" onclick="delcheck('/deleteItem','iid');"></th>
    </tr>
    <!-- Add new row button -->
    <%
        Object allItem = session.getAttribute("allItem");
    %>
    <%
        if(allItem !=null){
            List<Item> list = (List<Item>) allItem;
            if(list!=null)
            {
                for(Item item : list) {      %>
    <tr>
        <td><input type="checkbox" name="check" value="<%=item.getIid()%>"></td>
        <td><%=item.getIid()%></td>
        <td><%=item.getIname()%></td>
        <td><%=item.getIflag()%></td>
        <td>
            <a href="updateItem.jsp?iid=<%=item.getIid()%>">修改</a> &nbsp; | &nbsp;
            <a href="/deleteItem?iid=<%=item.getIid()%>"
               onclick="return confirm('确定要删除吗？')">删除</a>
        </td>
    </tr>
    <%
                }

            }
        }
    %>
</table>
<a href="addItem.jsp" class="add-item-btn">+</a>
</body>
