import dao.employee.DaoFactory
import model.Employee
import model.EmployeeCallback
import java.util.Scanner
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val sc = Scanner(System.`in`)

    print("Find by id or find all? i/a: ")
    val op = sc.next()[0]

    if (op == 'i') {
        findById(sc)
    } else if (op == 'a') {
        getAllEmployees()
    }
}



// get by id
fun findById(sc: Scanner){

    // declaração de variáveis
    val id = AtomicInteger()

    // callback
    val callback = object : EmployeeCallback.getEmployeeBYid {
        override fun getEmployeeById(emp: Employee) {
            println(emp)
        }
    }

    // thread
    val thread = Thread {
        print("Enter an id: ")
        id.set(sc.nextInt())
        println("--- Thread: " + Thread.currentThread().name + " used to search in database ---")
        val dao = DaoFactory.getEmployeeDao()
        val emp = dao.findEmployeeById(id.get())
        callback.getEmployeeById(emp!!)
        sc.close()
    }
    thread.name = "Thread to find by id"
    thread.start()
}


fun getAllEmployees() {

    // callback
    val callback = object : EmployeeCallback.getListOfEmployees {
        override fun getListOfEmployees(list: List<Employee>) {
            for (x in list ) println(x)
        }
    }

    // thread
    val thread = Thread {
        println("--- Thread: " + Thread.currentThread().name + " used to search in database ---")

        val dao = DaoFactory.getEmployeeDao()
        //list = dao.getAllEmployees()!!
        callback.getListOfEmployees(dao.getAllEmployees()!!)
    }
    thread.name = "Thread to find find all employees"
    thread.start()
}

