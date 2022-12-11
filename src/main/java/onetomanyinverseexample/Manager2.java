package onetomanyinverseexample;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import onetomanyexample.Employee;

@Entity
public class Manager2 {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  public Manager2() {
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


  @OneToMany
  private Set<Employee2> reports;

  public Set<Employee2> getReports() {
    return reports;
  }

 public void setReports(Set<Employee2> reports) {
    this.reports = reports;
  }
}
