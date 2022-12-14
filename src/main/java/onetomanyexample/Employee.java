package onetomanyexample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  public Employee() {
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

  @ManyToOne
  private Manager manager;

  public Manager getManager() {
    return manager;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }
}
