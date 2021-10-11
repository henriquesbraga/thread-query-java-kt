package dao.employee;

public class DaoFactory {
    public static EmployeeDao createEmployeeDao(){
        return new EmployeeDaoImpl();
    }
}
