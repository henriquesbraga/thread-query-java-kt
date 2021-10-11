package dao.employee;

import model.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee findEmployeeById(Integer id);
    List<Employee> getAllEmployees();
}
