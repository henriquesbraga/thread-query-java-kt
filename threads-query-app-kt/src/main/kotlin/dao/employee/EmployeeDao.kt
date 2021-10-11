package dao.employee

import model.Employee

interface EmployeeDao {
    fun findEmployeeById(id: Int): Employee?
    fun getAllEmployees(): List<Employee>?
}