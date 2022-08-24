package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return "Saved!";
    }

    @PutMapping(value = "update/{id}")
    public String updatedUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        if(user.getFirstName() != null)
            updatedUser.setFirstName(user.getFirstName());
        if(user.getLastName() != null)
            updatedUser.setLastName(user.getLastName());
        if(user.getAge() != 0)
            updatedUser.setAge(user.getAge());
        if(user.getOccupation() != null)
            updatedUser.setOccupation(user.getOccupation());
        userRepo.save(updatedUser);
        return "Updated!";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Deleted id:"+id;
    }
}
