package model;

import java.util.List;

public interface EmployeeCallback {

    interface GetEmpById {
        void getEmployeeById(Employee emp);
    }

    interface GetListOfEmployees {
        void getListOfEmployees(List<Employee> list);
    }
}
