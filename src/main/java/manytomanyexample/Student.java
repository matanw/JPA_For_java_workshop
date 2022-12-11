package manytomanyexample;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import java.util.Set;

@Entity
public class Student {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  public Student() {
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @ManyToMany
  Set<Course> learnCourses;

  public Set<Course> getLearnCourses() {
    return learnCourses;
  }

  public void setLearnCourses(Set<Course> learnCourses) {
    this.learnCourses = learnCourses;
  }
}