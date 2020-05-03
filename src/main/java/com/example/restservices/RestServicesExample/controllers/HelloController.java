package com.example.restservices.RestServicesExample.controllers;


import com.example.restservices.RestServicesExample.beans.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path = "/helloWorld")
    public String getHelloWorld() {

        return "Hello!! from HelloController";
    }

    @GetMapping(path = "/helloWorldBean")
    public HelloWorldBean getHelloWorldBean() {

        return new HelloWorldBean("Hello World from Bean");
    }

    @GetMapping(path = "/helloWorldBean/{pathVar}")
    public HelloWorldBean getPathVariable(@PathVariable String pathVar) {

        return new HelloWorldBean(String.format("Hello %s from Bean ", pathVar));
    }
}
