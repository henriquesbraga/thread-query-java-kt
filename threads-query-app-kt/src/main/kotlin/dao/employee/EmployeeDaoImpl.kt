package dao.employee

import connection.ConnectionClass
import model.Employee
import java.sql.SQLException

class EmployeeDaoImpl: EmployeeDao {

    override fun findEmployeeById(id: Int): Employee? {

        val sql = "select employee_id, first_name, last_name, title from employees where employee_id = ?"

        try {
            ConnectionClass.getConnection().use { conn ->
                conn.prepareStatement(sql).use { st ->
                    st.setInt(1, id)
                    st.executeQuery().use { rs ->
                        return if (rs.next()) {
                            val emp = Employee (
                                id = id,
                                firstName = rs.getString("first_name"),
                                lastName = rs.getString("last_name"),
                                title = rs.getString("title")
                            )
                            emp
                        }
                        else null
                    }
                }
            }
        }
        catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    override fun getAllEmployees(): List<Employee>? {
        val list: MutableList<Employee> = ArrayList()
        val sql = "select employee_id, first_name, last_name, title from employees"

        try{
            ConnectionClass.getConnection().use { conn ->
                conn.prepareStatement(sql).use { st ->
                    st.executeQuery().use { rs ->
                        while (rs.next()) {
                            list.add(
                                Employee(
                                    id = rs.getInt("employee_id"),
                                    firstName = rs.getString("first_name"),
                                    lastName = rs.getString("last_name"),
                                    title = rs.getString("title")
                                )
                            )
                        }
                        return list
                    }
                }
            }
        }
        catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }
}