package dao.employee

class DaoFactory {
    companion object {
        fun getEmployeeDao() = EmployeeDaoImpl()
    }
}