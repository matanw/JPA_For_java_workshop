import entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
public class Main {
  public static void main(String[] args) {

    printAllEmployees();
  }
  private static void addOneEmployee(){
    Employee employee = new Employee();
    employee.setfName("Dalia");
    employee.setlName("Abo Sheasha");
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
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    TypedQuery<Employee> q = entityManager.createQuery("select e from Employee e", Employee.class);
    List<Employee> employees = q.getResultList();
    entityManager.close();
    entityManagerFactory.close();

    for (Employee e: employees){
      System.out.println("e -"+e.getfName());
    }
    System.out.println("ok!");
  }
}