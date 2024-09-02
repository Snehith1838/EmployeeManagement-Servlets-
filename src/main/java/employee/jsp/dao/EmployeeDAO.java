package employee.jsp.dao;

import employee.jsp.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private String url = "jdbc:mysql://localhost:3306/company";
    private String user = "root";
    private String password = "root1838";

    private static final String insertQuery = "INSERT INTO employee(id, name, role, salary, city) VALUES(?,?,?,?,?)";
    private static final String selectQuery = "SELECT id, name, role, salary, city FROM employee WHERE id=?";
    private static final String selectAllQuery = "SELECT id, name, role, salary, city FROM employee";
    private static final String deleteQuery = "DELETE FROM employee WHERE id=?";
    private static final String updateQuery = "UPDATE employee SET name=?, role=?, salary=?, city=? WHERE id=?";

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void insertEmployee(Employee employee){
        try(Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(insertQuery)){

            psmt.setInt(1,employee.getId());
            psmt.setString(2,employee.getName());
            psmt.setString(3,employee.getRole());
            psmt.setDouble(4,employee.getSalary());
            psmt.setString(5,employee.getCity());
            psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Employee selectEmployee(int id){
        Employee employee = new Employee();
        try(Connection connection = getConnection();
           PreparedStatement psmt = connection.prepareStatement(selectQuery)){
            psmt.setInt(1,id);
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                double salary = resultSet.getDouble("salary");
                String city = resultSet.getString("city");
                employee = new Employee();
                employee.setId(id);
                employee.setName(name);
                employee.setRole(role);
                employee.setSalary(salary);
                employee.setCity(city);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> selectAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(selectAllQuery)){
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                double salary = resultSet.getDouble("salary");
                String city = resultSet.getString("city");

                Employee employee = new Employee();
                employee.setId(id);
                employee.setName(name);
                employee.setRole(role);
                employee.setSalary(salary);
                employee.setCity(city);
                employees.add(employee);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employees;
    }

    public boolean deleteEmployee(int id){
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(deleteQuery)){
            psmt.setInt(1,id);
            rowDeleted = psmt.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee){
        boolean rowUpdated = false;
        try(Connection connection = getConnection();
           PreparedStatement psmt = connection.prepareStatement(updateQuery)){
            psmt.setString(1,employee.getName());
            psmt.setString(2,employee.getRole());
            psmt.setDouble(3,employee.getSalary());
            psmt.setString(4,employee.getCity());
            psmt.setInt(5,employee.getId());
            rowUpdated = psmt.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex){
        for(Throwable e:ex){
            if(e instanceof SQLException){
                System.err.println("SQLState : " + ((SQLException) e).getSQLState());
                System.err.println("Error code : " + ((SQLException) e).getErrorCode());
                System.err.println("Message : " + e.getMessage());

                Throwable t = ex.getCause();
                while (t != null){
                    System.out.println("Cause : " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
