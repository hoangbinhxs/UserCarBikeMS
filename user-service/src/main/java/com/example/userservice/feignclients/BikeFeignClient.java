package com.example.userservice.feignclients;

import com.example.userservice.models.Bike;
import com.example.userservice.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service")
public interface BikeFeignClient {

    @PostMapping
    Bike saveBikeFeignClient(@RequestBody Bike bike);

    @GetMapping( value = "/byuser/{userId}")
    List<Bike> listBikeByUserId(@PathVariable("userId") int userId);

}
