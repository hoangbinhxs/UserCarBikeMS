package com.example.userservice.controller;

import com.example.userservice.entity.Usuario;
import com.example.userservice.models.Bike;
import com.example.userservice.models.Car;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> users = userService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id){
        Usuario user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario user){
        Usuario userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int id){
        Usuario user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = userService.getCars(id);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int id){
        Usuario user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = userService.getBike(id);
        return ResponseEntity.ok(bikes);
    }
}
