package manytomanyexample;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Runner {
  public static void doLogic() {
    //add();
    printAllCoursesWithStudent();
  }


  private static void add(){
    Student s1= new Student();
    s1.setName("Matan");

    Student s2= new Student();
    s2.setName("Dov");
    Course c =new Course();
    c.setName("Java");
    c.setStudents(new HashSet<Student>(Arrays.asList(s1, s2)));
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(s1);
    entityManager.persist(s2);
    entityManager.persist(c);
    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }


  static void printAllCoursesWithStudent(){
    EntityManagerFactory entityManagerFactory = Persistence.
        createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Course> q = entityManager
        .createQuery("select c from Course c", Course.class);
    List<Course> courses = q.getResultList();
    for(Course c: courses){
      System.out.println(c.getName());
      for(Student s: c.getStudents()) {
        System.out.println("--"+s.getName());
      }
    }
    entityManager.close();
    entityManagerFactory.close();
  }
}