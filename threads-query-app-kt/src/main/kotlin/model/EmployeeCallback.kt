package model

interface EmployeeCallback {

    interface getEmployeeBYid {
        fun getEmployeeById(emp: Employee)
    }

    interface getListOfEmployees {
        fun getListOfEmployees(list: List<Employee>)
    }


}