package com.example.userservice.services;


import com.example.userservice.entity.Usuario;
import com.example.userservice.feignclients.BikeFeignClient;
import com.example.userservice.feignclients.CarFeignClient;
import com.example.userservice.models.Bike;
import com.example.userservice.models.Car;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    @Autowired
    RestTemplate restTemplate;

    public List<Usuario> getAll(){
        return  userRepository.findAll();
    }
    public Usuario getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public Usuario save(Usuario user){
        Usuario newUser = userRepository.save(user);
        return newUser;
    }
    //Con Resttemplate
    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
        return cars;
    }

    public List<Car> getCarsFeign(int userId){
        List<Car> cars = carFeignClient.listCarByUserId(userId);
        return cars;
    }
    public List<Bike> getBike(int userId){
        List<Bike> bikes = bikeFeignClient.listBikeByUserId(userId);
                //restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
        return bikes;
    }
    //FeignClient
    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return car;
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeNew =
                bikeFeignClient.saveBikeFeignClient(bike);
        return bikeNew;
    }
}
