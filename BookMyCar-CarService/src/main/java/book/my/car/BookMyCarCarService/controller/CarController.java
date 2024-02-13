package book.my.car.BookMyCarCarService.controller;

import book.my.car.BookMyCarCarService.request.CarRequest;
import book.my.car.BookMyCarCarService.response.CarResponse;
import book.my.car.BookMyCarCarService.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin
@Slf4j
public class CarController {
    @Autowired
    CarService carService;
    @PostMapping
    public ResponseEntity<String> createCar(@RequestBody CarRequest carRequest){
        return new ResponseEntity<>(carService.saveCar(carRequest), HttpStatus.OK);
    }
    @GetMapping("/allCars")
    public ResponseEntity<List<CarResponse>> findAllCars(){
        log.info("Entered Controller");
        return new ResponseEntity<>(carService.getAllCars(),HttpStatus.FOUND);
    }
    @GetMapping("/{carId}")
    public ResponseEntity<CarResponse> findCarById(@PathVariable String carId){
        log.info("entered controller by id" + carId);
        return new ResponseEntity<>(carService.getCarById(carId),HttpStatus.FOUND);
    }
    @PatchMapping("/book/{carModel}")
    public void bookDriver(@PathVariable String carModel ){
        log.info("entered book car controller:"+carModel);
        carService.changeStatusOfCar(carModel);
    }
}


