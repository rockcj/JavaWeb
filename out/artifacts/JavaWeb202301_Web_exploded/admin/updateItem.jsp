<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.lingnan.pojo.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
    <title>编辑项目表</title>
</head>
<body>
<h1>岭南师范学院学生管理系统———学生管理模块</h1>
欢迎您：${user}&nbsp;先生/女士
<br><br>
<a href="../login">退出</a>
<a href="../loginout">用户注销</a>
<a href="../item/queryAll">查询项目信息</a>
<hr>

<form  action="../item/update">
    <table>
        <tr>
            <th>项目id</th>
            <th>项目名称</th>
            <th>项目权限</th>
            <th>操作选择</th>
        </tr>
        <%
            Object allItem = session.getAttribute("allItem");
            String iid = request.getParameter("iid");
        %>
        <%
            if(allItem !=null){
                List<Item> list = (List<Item>) allItem;
                if(list!=null)
                {
                    for(Item item : list) {
                        if(item.getIid().equals(iid)){%>
        <tr>
            <td><input type="hidden" name="iid" value="<%=item.getIid()%>"><%=item.getIid()%></td>
            <td><input type="text" name="iname" value="<%=item.getIname()%>"></td>
            <td><input type="text" name="iflag" value="<%=item.getIflag()%>"></td>
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