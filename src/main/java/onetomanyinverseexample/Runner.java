package onetomanyinverseexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Runner {

  public static void doLogic() {
    addManagerWith2Employee();
    printAllManagerWithEmployees();
  }


  private static void addManagerWith2Employee() {
    Employee2 employee1 = new Employee2();
    employee1.setName("Matan");
    Employee2 employee2 = new Employee2();
    employee2.setName("Dov");
    Manager2 manager = new Manager2();
    manager.setName("Tamar");
    manager.setReports(new HashSet<>(Arrays.asList(employee1, employee2)));
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

  static void printAllManagerWithEmployees() {
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Manager2> q = entityManager
        .createQuery("select m from Manager2 m", Manager2.class);
    List<Manager2> managers = q.getResultList();
    for (Manager2 m : managers) {
      System.out.println(m.getName());
      for (Employee2 e : m.getReports()) {
        System.out.println("--" + e.getName());
      }
    }
    entityManager.close();
    entityManagerFactory.close();
  }
}