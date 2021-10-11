package connection

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionClass {

    companion object {
        private lateinit var conn: Connection
        private const val driver = "org.postgresql.Driver";
        private const val URL = "jdbc:postgresql://192.168.1.120/postgres?currentSchema=henrique_northwind"
        private const val USER = "henrique"
        private const val PASSWORD = "1234"

        fun getConnection(): Connection {
            try{
                Class.forName(driver)
                conn = DriverManager.getConnection(URL, USER, PASSWORD)
            }
            catch (se: SQLException){
                se.printStackTrace()
            }
            return conn
        }
    }
}