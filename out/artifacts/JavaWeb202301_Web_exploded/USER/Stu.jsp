<%@ page import="cn.edu.lingnan.pojo.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>个人信息 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>个人基本信息</h1>
            <div style="color: var(--text-secondary);">查看您的基本学籍信息</div>
        </div>

        <div class="dashboard-grid">
            <%
                Object OneStu = session.getAttribute("stu");
                if(OneStu !=null){
                    Student student = (Student) OneStu;
            %>
            
            <div class="card" style="grid-column: span 2;">
                <div style="display: flex; align-items: center; margin-bottom: 20px;">
                    <div style="width: 80px; height: 80px; border-radius: 50%; background: var(--accent-color); display: flex; align-items: center; justify-content: center; font-size: 2rem; color: #000; margin-right: 20px; font-weight: bold;">
                        <%=student.getSname() != null && student.getSname().length() > 0 ? student.getSname().substring(0,1) : "学"%>
                    </div>
                    <div>
                        <h2 style="margin: 0; color: #fff;"><%=student.getSname()%></h2>
                        <div style="color: var(--accent-color); margin-top: 5px;">学号：<%=student.getSid()%></div>
                    </div>
                </div>

                <div class="table-container" style="margin-top: 0;">
                    <table class="tech-table">
                        <tr>
                            <th style="width: 150px;">学生学号</th>
                            <td><%=student.getSid()%></td>
                        </tr>
                        <tr>
                            <th>学生姓名</th>
                            <td><%=student.getSname()%></td>
                        </tr>
                        <tr>
                            <th>用户权限</th>
                            <td>
                                <% if(student.getSright() == 1) { %>
                                    <span style="color: var(--accent-color);">管理员</span>
                                <% } else { %>
                                    <span>普通用户</span>
                                <% } %>
                            </td>
                        </tr>
                        <tr>
                            <th>审核状态</th>
                            <td>
                                <% if(student.getStflag() == 1) { %>
                                    <span style="color: var(--success);">● 审核通过</span>
                                <% } else { %>
                                    <span style="color: var(--danger);">○ 待审核</span>
                                <% } %>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <% } else { %>
                <div class="card">
                    <div style="text-align: center; padding: 40px; color: var(--danger);">
                        暂无个人信息，请联系管理员或重新登录。
                    </div>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>