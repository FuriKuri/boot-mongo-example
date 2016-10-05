package net.furikuri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MongoLoader {

  @Autowired
  private CustomerRepository repository;

  @PostConstruct
  public void initData() {
    repository.deleteAll();
    repository.save(new Customer("Alice", "Smith"));
    repository.save(new Customer("Bob", "Smith"));
  }
}
