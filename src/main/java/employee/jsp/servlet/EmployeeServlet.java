package employee.jsp.servlet;

import employee.jsp.dao.EmployeeDAO;
import employee.jsp.model.Employee;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/EmployeeServlet/*")
public class EmployeeServlet extends HttpServlet {

    private static final long serialVersion = 1L;
    private EmployeeDAO employeeDAO;

    public void init(){
        employeeDAO = new EmployeeDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try{
            switch (action){
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertEmployee(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateEmployee(request, response);
                    break;
                case "list":
                    listEmployees(request, response);
                    break;
                default:
                    break;
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Employee> listEmployees = employeeDAO.selectAllEmployees();
        request.setAttribute("employeeList",listEmployees);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list-employees.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-employee.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = employeeDAO.selectEmployee(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-employee.jsp");
        request.setAttribute("employee",existingEmployee);
        dispatcher.forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String city = request.getParameter("city");

        Employee newEmployee = new Employee();
        newEmployee.setId(id);
        newEmployee.setName(name);
        newEmployee.setRole(role);
        newEmployee.setSalary(salary);
        newEmployee.setCity(city);
        employeeDAO.insertEmployee(newEmployee);
        response.sendRedirect("EmployeeServlet?action=list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String city = request.getParameter("city");

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setRole(role);
        employee.setSalary(salary);
        employee.setCity(city);

        employeeDAO.updateEmployee(employee);
        response.sendRedirect("EmployeeServlet?action=list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException,IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDAO.deleteEmployee(id);
        response.sendRedirect("EmployeeServlet?action=list");
    }

}
