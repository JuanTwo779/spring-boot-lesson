package com.juan;

import com.juan.model.Customer;
import com.juan.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }




//    @GetMapping("/")
//    public String greet() {
//        return "Hello";
//    }
//
//    @GetMapping("/greet") //API
//    public String mate() {
//        return "mate";
//    }
//
//    @GetMapping("/goodbye")
//    public ByeResponse bye(){
//        return new ByeResponse(
//                "Goodbye",
//                List.of("Java", "Python", "JavaScript"),
//                new Person("Juan", 22));
//    }
//    public record ByeResponse(
//            String bye,
//            List<String> favProgrammingLanguages,
//            Person person
//    ){}
//
//    public record Person(
//            String name,
//            int age
//    ){}


    //READ CUSTOMERS API ENDPOINT
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    //Should be a seperate class
    public static record NewCustomerRequest(
        String name,
        String email,
        Integer age
    ){}

    //CREATE CUSTOMER API ENDPOINT
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        customerRepository.save(customer);
    }

    //DELETE CUSTOMER API ENDPOINT
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    //UPDATE CUSTOMER API ENDPOINT
    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestBody NewCustomerRequest request){

        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        customerRepository.save(customer);
    }

}
