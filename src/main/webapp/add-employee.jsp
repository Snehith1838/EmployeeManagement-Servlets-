

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
      <meta charset="UTF-8">
      <title>Add Employee</title>
</head>
<body>
      <h1> Add Employee </h1>
      <form action="EmployeeServlet?action=insert" method="post">
      Id : <input type="text" name="id" /><br>
      Name : <input type="text" name="name" /><br>
      Role : <input type="text" name="role" /><br>
      Salary : <input type="text" name= "salary" /><br>
      City : <input type="text" name="city" /><br>
      <input type="submit" value="Add Employee" />
      </form>

      <a href="index.jsp"> Back to Main </a>
</body>
</html>