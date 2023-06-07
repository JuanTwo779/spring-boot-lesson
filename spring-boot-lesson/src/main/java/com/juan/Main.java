package com.juan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public String greet() {
        return "Hello";
    }

    @GetMapping("/greet") //API
    public String mate() {
        return "mate";
    }

    @GetMapping("/goodbye")
    public ByeResponse bye(){
        return new ByeResponse(
                "Goodbye",
                List.of("Java", "Python", "JavaScript"),
                new Person("Juan", 22));
    }

    //Immutable class
    //final field, constructor, getter, toString
    public record ByeResponse(
            String bye,
            List<String> favProgrammingLanguages,
            Person person
    ){}

    public record Person(
            String name,
            int age
    ){}
}
