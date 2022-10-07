package org.example.controller;

import org.example.database.DB;
import org.example.database.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

@RestController
public class CarController
{
    @GetMapping("/car/list")
    public List<Car> getAll()
    {
        return DB.getRoot().getCars();
    }

    @GetMapping("/car/get/{id}")
    public Car getCar(@PathVariable("id") Long id)
    {
        return DB.getRoot().getCars().stream()
                .filter(car -> car.getId().equals(id)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Car %s Not Found", id)));
    }

    @DeleteMapping("/car/delete/{id}")
    public void delete(@PathVariable("id") Long id)
    {
        List<Car> cars = DB.getRoot().getCars();
        cars.remove(getCar(id));

        //Store cars vs store Company
        DB.getInstance().store(cars);
    }

    @PostMapping("/car/insert")
    public Car insert(@RequestParam String model, @RequestParam double price, @RequestParam String brand)
    {
        List<Car> cars = DB.getRoot().getCars();

        Car car = new Car(Long.valueOf(new Random().nextInt(Integer.SIZE - 1)));
        car.setPrice(price);
        car.setModel(model);

        DB.getRoot().getBrands()
                .stream()
                .filter(brandObj -> brandObj.getName().equals(brand))
                .findAny().ifPresent( brandObj -> car.setBrand(brandObj));

        cars.add(car);

        //Test Store Company

        //Test Store Car-Collection
        DB.getInstance().store(cars);

        return car;
    }
}
