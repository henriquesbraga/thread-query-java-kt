package application;

import dao.employee.DaoFactory;
import dao.employee.EmployeeDao;
import model.Employee;
import model.EmployeeCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static Employee emp = null;
    static List<Employee> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Find by id or find all? i/a: ");
        char op = sc.next().charAt(0);

        if(op == 'i'){
            findById(sc);
        }
        else if (op == 'a'){
            getAllEmployees();
        }
    }

    public static void findById(Scanner sc){

        // declaração de variáveis
        AtomicInteger id = new AtomicInteger();

        // callback
        EmployeeCallback.GetEmpById callback = new EmployeeCallback.GetEmpById() {
            @Override
            public void getEmployeeById(Employee emp) {
                // print final de employee
                System.out.println(emp);
            }
        };

        Thread thread = new Thread(() -> {
            System.out.print("Enter an id: ");
            id.set(sc.nextInt());
            System.out.println("--- From Thread: " + Thread.currentThread().getName() + " ---");
            EmployeeDao dao = DaoFactory.createEmployeeDao();
            emp = dao.findEmployeeById(id.get());
            callback.getEmployeeById(emp);
            sc.close();

        });
        thread.setName("Thread to find by id");
        thread.start();
    }

    static void getAllEmployees(){

        // callback
        EmployeeCallback.GetListOfEmployees callback = new EmployeeCallback.GetListOfEmployees() {
            @Override
            public void getListOfEmployees(List<Employee> list) {
                for(Employee emp : list){
                    System.out.println(emp);
                }
            }
;        };

        // thread
        Thread thread = new Thread(() -> {
            System.out.println("--- From Thread: " + Thread.currentThread().getName() + " ---");
            EmployeeDao dao = DaoFactory.createEmployeeDao();
            list = dao.getAllEmployees();
            callback.getListOfEmployees(list);
        });
        thread.setName("Thread to find all employees");
        thread.start();
    }
}
