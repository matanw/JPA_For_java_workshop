package onetomanyinverseexample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import onetomanyexample.Manager;

@Entity
public class Employee2 {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  public Employee2() {
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
  private Manager2 manager;

  public Manager2 getManager() {
    return manager;
  }

  public void setManager(Manager2 manager) {
    this.manager = manager;
  }
}
