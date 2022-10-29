import entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
public class Main {
  public static void main(String[] args) {
    addOneEmployee();
    printAllEmployees();
  }
  private static void addOneEmployee(){
    Employee employee = new Employee();
    employee.setName("Matan");
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(employee);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
    System.out.println("ok!");
  }
  private static void printAllEmployees(){
    for (Employee e: getAllEmployees()){
      System.out.println("emp -"+e.getName());
    }
  }

  static List<Employee> getAllEmployees(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Employee> q = entityManager.createQuery("select e from Employee e", Employee.class);
    List<Employee> result = q.getResultList();
    entityManager.close();
    entityManagerFactory.close();
    return result;
  }
  static List<Employee> getAllEmployees2(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Employee> q = entityManager.createQuery("select e from Employee e", Employee.class);
    List<Employee> result = q.getResultList();
    entityManager.close();
    entityManagerFactory.close();
    return result;
  }
}