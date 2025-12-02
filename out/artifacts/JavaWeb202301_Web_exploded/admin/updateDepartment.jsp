<%@ page import="cn.edu.lingnan.pojo.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>修改院系 - 岭南师范学院学生管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tech-style.css">
</head>
<body>

    <!-- 引入侧边栏 -->
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="page-header">
            <h1>修改院系信息</h1>
            <a href="${pageContext.request.contextPath}/department/queryAll" class="btn btn-primary btn-sm">← 返回列表</a>
        </div>

        <%
            String deptId = request.getParameter("deptId");
            Department targetDept = null;
            Object allDepartment = session.getAttribute("allDepartment");
            if(allDepartment == null) {
                allDepartment = request.getAttribute("allDepartment");
            }
            if(allDepartment != null && deptId != null){
                List<Department> list = (List<Department>) allDepartment;
                for(Department dept : list) {
                    if(dept.getDeptId().equals(deptId)) {
                        targetDept = dept;
                        break;
                    }
                }
            }
        %>

        <div class="form-panel">
            <% if (targetDept != null) { %>
            <form action="${pageContext.request.contextPath}/department/update" method="post">
                <!-- 隐藏域传递主键 -->
                <input type="hidden" name="deptId" value="<%=targetDept.getDeptId()%>">
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                    <div class="input-group">
                        <label>院系ID (不可修改)</label>
                        <input type="text" value="<%=targetDept.getDeptId()%>" disabled style="opacity: 0.7; cursor: not-allowed;">
                    </div>
                    
                    <div class="input-group">
                        <label>院系名称 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="deptName" value="<%=targetDept.getDeptName()%>" required>
                    </div>

                    <div class="input-group">
                        <label>院系编码 <span style="color: var(--danger);">*</span></label>
                        <input type="text" name="deptCode" value="<%=targetDept.getDeptCode()%>" required>
                    </div>

                    <div class="input-group">
                        <label>院系负责人</label>
                        <input type="text" name="deptHead" value="<%=targetDept.getDeptHead() != null ? targetDept.getDeptHead() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>联系电话</label>
                        <input type="text" name="deptPhone" value="<%=targetDept.getDeptPhone() != null ? targetDept.getDeptPhone() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>电子邮箱</label>
                        <input type="text" name="deptEmail" value="<%=targetDept.getDeptEmail() != null ? targetDept.getDeptEmail() : ""%>">
                    </div>

                    <div class="input-group">
                        <label>状态</label>
                         <select name="dflag">
                            <option value="0" <%=targetDept.getDflag() != null && targetDept.getDflag() == 0 ? "selected" : ""%>>正常</option>
                            <option value="1" <%=targetDept.getDflag() != null && targetDept.getDflag() == 1 ? "selected" : ""%>>停用</option>
                        </select>
                    </div>
                </div>

                <div class="input-group">
                    <label>院系描述</label>
                    <textarea name="deptDesc" rows="4"><%=targetDept.getDeptDesc() != null ? targetDept.getDeptDesc() : ""%></textarea>
                </div>

                <div style="margin-top: 20px; display: flex; justify-content: flex-end;">
                    <button type="submit" class="btn btn-primary" style="background: var(--accent-color); color: #000; padding: 12px 40px;">
                        💾 保存修改
                    </button>
                </div>
            </form>
            <% } else { %>
                <div style="text-align: center; padding: 40px; color: var(--danger);">
                    未找到相关院系信息，请返回重试。
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>
