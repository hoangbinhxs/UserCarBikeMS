package com.example.carservice.controller;

import com.example.carservice.entity.Car;
import com.example.carservice.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {


    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> users = carService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id){
        Car car = carService.getCarById(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car){
        Car carNew = carService.save(car);
        return ResponseEntity.ok(carNew);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId){
        List<Car> cars = carService.byUserId(userId);
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

}
