package dao.employee;

import connection.ConnectionClass;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public Employee findEmployeeById(Integer id) {

        final String SQL = "select employee_id, first_name, last_name, title from employees where employee_id = ?";
        
        try(Connection conn = ConnectionClass.getConnection()){
            try(PreparedStatement st = conn.prepareStatement(SQL)){
                st.setInt(1, id);
                try(ResultSet rs = st.executeQuery()){
                    if(rs.next()){
                        Employee emp = new Employee();
                        emp.setId(id);
                        emp.setFirstName(rs.getString("first_name"));
                        emp.setLastName(rs.getString("last_name"));
                        emp.setTitle(rs.getString("title"));
                        return emp;
                    }
                    else {
                        return null;
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        final String SQL = "select employee_id, first_name, last_name, title from employees";

        try(Connection conn = ConnectionClass.getConnection()){
            try(PreparedStatement st = conn.prepareStatement(SQL)){
                try(ResultSet rs = st.executeQuery()){
                    while(rs.next()){
                        Employee emp = new Employee();
                        emp.setId(rs.getInt("employee_id"));
                        emp.setFirstName(rs.getString("first_name"));
                        emp.setLastName(rs.getString("last_name"));
                        emp.setTitle(rs.getString("title"));
                        list.add(emp);
                    }
                    return list;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
