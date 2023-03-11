package com.example.bikeservice.controller;

import com.example.bikeservice.entity.Bike;
import com.example.bikeservice.services.BikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {


    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> users = bikeService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id){
        Bike bike = bikeService.getCarById(id);
        if(bike == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike bike){
        Bike bikeNew = bikeService.save(bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId){
        List<Bike> bikes = bikeService.byUserId(userId);
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

}
