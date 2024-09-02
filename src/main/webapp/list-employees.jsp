

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="employee.jsp.model.Employee" %>

<%
    List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
%>
<!DOCTYPE html>
<html>
<head>
      <meta charset="UTF-8">
      <title>List Employees</title>
</head>
<body>
       <h1>Employees</h1>
       <table border="1">
               <tr>
                   <th>ID</th>
                   <th>Name</th>
                   <th>Salary</th>
                   <th>Role</th>
                   <th>City</th>
                   <th>Actions</th>
               </tr>

               <% for (Employee emp : employeeList) { %>
               <tr>
                   <td><%=emp.getId()%></td>
                   <td><%=emp.getName()%></td>
                   <td><%=emp.getSalary()%></td>
                   <td><%=emp.getRole()%></td>
                   <td><%=emp.getCity()%></td>
                   <td>
                       <a href="${pageContext.request.contextPath}/EmployeeServlet?action=edit&id=<%=emp.getId()%>">Edit</a>
                       <a href="${pageContext.request.contextPath}/EmployeeServlet?action=delete&id=<%=emp.getId()%>">Delete</a>
                   </td>
               </tr>
               <% } %>
       </table>
       <a href="index.jsp">Back to Main</a>
</body>
</html>
