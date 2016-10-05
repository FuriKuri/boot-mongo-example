package net.furikuri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

  @Autowired
  private CustomerRepository repository;

  @RequestMapping("/")
  public List<Customer> get() {
    return repository.findAll();
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Customer add(Customer customer) {
    return repository.save(customer);
  }

}
