package com.example.restservices.RestServicesExample.controllers;

import com.example.restservices.RestServicesExample.beans.User;
import com.example.restservices.RestServicesExample.services.UserService;
import com.example.restservices.RestServicesExample.utilities.exceptions.UserNotFoundException;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable int id){
        User user = service.findUser(id);
        if(user==null){
            throw new UserNotFoundException(String.format("No user found with id %s. Please search with a different id", id));
        }
        return user;
    }

    @PostMapping(path="/users/")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add("location", location.toString()); // location of new resource
        //Use below statement if only new resource's location is to be returned in the header
        //return new ResponseEntity.created(location).build();
        //Use below statement to send success message with custom headers and response status. Headers is an optional parameter
        return new ResponseEntity<>("User is created successfully", headers, HttpStatus.OK); //.created(location).build();
    }

    @DeleteMapping(path="/users/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = service.deleteUser(id);
        if(user==null){
            throw new UserNotFoundException(String.format("No user found with id %s. Please try again with a different id", id));
        }
        return String.format("User with id %s deleted successfully !!", id);
    }

    /*
       * HATEOAS stands for “Hypermedia as the engine of application state.”
       * When some details of a resource are requested, you will provide the resource details as well as
       * details of related resources and the possible actions you can perform on the resource.
       * For example, when requesting information about a Facebook user, a REST service can return the
       * following
       * User details
       * Links to get his recent posts
       * Links to get his recent comments
       * Links to retrieve his friend’s list.
    */
    /*
        @GetMapping(path = "/users/hateoas/{id}")
        public EntityModel<User> getUserByIdHateOas(@PathVariable int id){
        User user = service.findUser(id);
        if(user==null){
            throw new UserNotFoundException(String.format("No user found with id %s. Please search with a different id", id));
        }
        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder getUsersLink = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
        model.add(getUsersLink.withRel("all-users"));
        return model;
        }
    */
    @GetMapping(path = "/users/hateoas/{id}")
    public Resource<User> getUserByIdHateOas(@PathVariable int id){
        User user = service.findUser(id);
        if(user==null){
            throw new UserNotFoundException(String.format("No user found with id %s. Please search with a different id", id));
        }
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }
    @GetMapping(path="/message/I18/goodMorning")
    public String getI18GMMessage(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.text", null, locale);
    }
    @GetMapping(path="/message/I18/hello")
    public String getI18HelloMessage() {
        return messageSource.getMessage("hi.text", null, LocaleContextHolder.getLocale());
    }

}