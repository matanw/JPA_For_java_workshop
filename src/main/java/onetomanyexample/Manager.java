package onetomanyexample;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Manager {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  public Manager() {
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


  @OneToMany(mappedBy="manager")
  private Set<Employee> reports;

  public Set<Employee> getReports() {
    return reports;
  }

 public void setReports(Set<Employee> reports) {
    this.reports = reports;
  }
}
