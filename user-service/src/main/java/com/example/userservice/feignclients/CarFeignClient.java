package com.example.userservice.feignclients;


import com.example.userservice.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name="car-service", url="http://localhost:8002/car")
public interface CarFeignClient {

    @PostMapping
    Car save(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.GET, value = "/byuser/{userId}")
    List<Car> listCarByUserId(@PathVariable("userId") int userId);
}
