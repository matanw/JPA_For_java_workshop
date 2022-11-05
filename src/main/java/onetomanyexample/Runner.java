package onetomanyexample;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Runner {
  public static void doLogic() {
    addManagerWith2Employee();
 printAllManagerWithEmployeesTryFixIllegal();
  }


  private static void addManagerWith2Employee(){
    Employee employee1 = new Employee();
    employee1.setName("Matan");
    Employee employee2 = new Employee();
    employee2.setName("Dov");
    Manager manager = new Manager();
    manager.setName("Tamar");
    employee1.setManager(manager);
    employee2.setManager(manager);
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(employee1);
    entityManager.persist(employee2);
    entityManager.persist(manager);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }

  static void printAllEmployeesWithManager(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Employee> q = entityManager
        .createQuery("select e from Employee e", Employee.class);
    List<Employee> employees = q.getResultList();
    for(Employee e: employees){
      System.out.println(e.getName());
      if (e.getManager()!=null){
        System.out.println("--"+e.getManager().getName());
      }
    }
    entityManager.close();
    entityManagerFactory.close();
  }
  static void printAllManagerWithEmployees(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Manager> q = entityManager
        .createQuery("select m from Manager m", Manager.class);
    List<Manager> managers = q.getResultList();
    for(Manager m: managers){
      System.out.println(m.getName());
      for(Employee e: m.getReports()) {
        System.out.println("--"+e.getName());
      }
    }
    entityManager.close();
    entityManagerFactory.close();
  }
  static void printAllManagerWithEmployeesIllegal(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Manager> q = entityManager
        .createQuery("select m from Manager m", Manager.class);
    List<Manager> managers = q.getResultList();
    entityManager.close();
    entityManagerFactory.close();
    for(Manager m: managers){
      System.out.println(m.getName());
      for(Employee e: m.getReports()) {
        System.out.println("--"+e.getName());
      }
    }
  }
  static void printAllManagerWithEmployeesTryFixIllegal(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Manager> q = entityManager
        .createQuery("select m from Manager m join fetch m.reports", Manager.class);
    List<Manager> managers = q.getResultList();
    entityManager.close();
    entityManagerFactory.close();
    for(Manager m: managers){
      System.out.println(m.getName()+m.getId());
      for(Employee e: m.getReports()) {
        System.out.println("--"+e.getName()+e.getId());
      }
    }
  }
}