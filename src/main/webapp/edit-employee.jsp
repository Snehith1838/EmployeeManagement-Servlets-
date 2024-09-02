

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, employee.jsp.model.Employee" %>
<%
  Employee employee = (Employee) request.getAttribute("employee");
%>

<!DOCTYPE html>
<html>
<head>
      <meta charset="UTF-8">
      <title> Edit Employee </title>
</head>
<body>
      <h1> Edit Employee </h1>

      <form action="/Servlets/EmployeeServlet?action=update" method="post">
      <input type="hidden" name="id" value="<%= employee.getId() %>" />
      Name : <input type="text" name="name" value="<%=employee.getName() %>"/><br>
      Role : <input type="text" name="role" value="<%=employee.getRole() %>"/><br>
      Salary : <input type="text" name= "salary" value="<%=employee.getSalary() %>"/><br>
      City : <input type="text" name="city" value="<%=employee.getCity() %>"/><br>
      <input type="submit" value="Update Employee" />
      </form>

      <a href="index.jsp"> Back to Main </a>

</body>
</html>