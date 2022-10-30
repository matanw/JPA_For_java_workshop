import entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Main {
  public static void main(String[] args) {
    printAllEmployees();
    System.out.println("---");
    updateById(2, "tamar");
    updateById(6, "tamahr");
    printAllEmployees();
  }
  private static void addOneEmployee(){
    Employee employee = new Employee();
    employee.setName("Matan");
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(employee);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }
  private static void deleteById(long id){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Employee employee=entityManager.find(Employee.class, id);
    entityManager.getTransaction().begin();
    entityManager.remove(employee);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }
  private static void updateById(long id,String newName){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Employee employee=entityManager.find(Employee.class, id);
    employee.setName(newName);
    entityManager.getTransaction().begin();
    entityManager.persist(employee);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }
  private static void printAllEmployees(){
    for (Employee e: getAllEmployees2()){
      System.out.println("emp -"+e.getId()+"-"+e.getName());
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

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    Root<Employee> from = cq.from(Employee.class);
    cq.select(from);
    TypedQuery<Employee> q = entityManager.createQuery(cq);


    List<Employee> result = q.getResultList();
    entityManager.close();
    entityManagerFactory.close();
    return result;
  }
}