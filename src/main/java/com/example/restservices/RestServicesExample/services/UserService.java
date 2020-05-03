package com.example.restservices.RestServicesExample.services;

import com.example.restservices.RestServicesExample.beans.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserService {

    private static List<User> users = new ArrayList<>();
    private static int userId = 3;
    static {
        //Add users
        users.add(new User("Ankita",1,new Date("11-September-1993")));
        users.add(new User("Udit",2,new Date("13-July-1995")));
    }
    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(userId++);
        }
        users.add(user);
        return user;
    }

    public User findUser(int id){
        for(User user: users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public User deleteUser(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
