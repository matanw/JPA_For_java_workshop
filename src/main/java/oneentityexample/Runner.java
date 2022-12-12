package oneentityexample;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Runner {
  public static void doLogic() {
    printAllEmployees();
    System.out.println("---");
    addOneEmployee();
    printAllEmployees();
  }
  private static void addOneEmployee(){
    Employee0 employee = new Employee0();
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
    Employee0 employee=entityManager.find(Employee0.class, id);
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
    Employee0 employee=entityManager.find(Employee0.class, id);
    employee.setName(newName);
    entityManager.getTransaction().begin();
    entityManager.persist(employee);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }
  private static void printAllEmployees(){
    for (Employee0 e: getAllEmployees2()){
      System.out.println("emp -"+e.getId()+"-"+e.getName());
    }
  }

  static List<Employee0> getAllEmployees(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Employee0> q = entityManager.
        createQuery("select e from Employee0 e", Employee0.class);
    List<Employee0> result = q.getResultList();
    entityManager.close();
    entityManagerFactory.close();
    return result;
  }
  static List<Employee0> getAllEmployees2(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Employee0> cq = cb.createQuery(Employee0.class);
    Root<Employee0> from = cq.from(Employee0.class);
    cq.select(from);
    TypedQuery<Employee0> q = entityManager.createQuery(cq);


    List<Employee0> result = q.getResultList();
    entityManager.close();
    entityManagerFactory.close();
    return result;
  }
}