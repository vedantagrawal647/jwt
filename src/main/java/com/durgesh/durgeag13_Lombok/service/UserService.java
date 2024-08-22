package com.durgesh.durgeag13_Lombok.service;



import com.durgesh.durgeag13_Lombok.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store = new ArrayList<>();

    public  UserService(){
        store.add(new User(UUID.randomUUID().toString(),"Vedant","Vedant@03"));
        store.add(new User(UUID.randomUUID().toString(),"yash","yash@03"));
        store.add(new User(UUID.randomUUID().toString(),"ramanuj","ramanuj@03"));
        store.add(new User(UUID.randomUUID().toString(),"kartik","kartik@03"));
    }

    public List<User> getUsers(){
        return this.store;
    }
}
