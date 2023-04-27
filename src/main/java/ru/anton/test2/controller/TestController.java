package ru.anton.test2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.anton.test2.facade.User;
import ru.anton.test2.facade.TestConn;

@Controller
public class TestController {

    @GetMapping("/test")
    public String get(@RequestParam String name, @ModelAttribute("user") User user) {
        user.setFirstName(name);
        System.out.println("Hi");
        return "test";
    }
    @GetMapping("/greet")
    public String greet() {
        System.out.println("Hi");
        return "greet";
    }

    @GetMapping("/bdtest")
    public String Lul() throws ClassNotFoundException {
        TestConn.testcon();
        return "greet";
    }


}
