package book.my.car.BookMyCarCarService.service;

import book.my.car.BookMyCarCarService.entity.Car;
import book.my.car.BookMyCarCarService.request.CarRequest;
import book.my.car.BookMyCarCarService.response.CarResponse;

import java.util.List;
import java.util.Optional;

public interface CarService {
    String saveCar(CarRequest carRequest);
    List<CarResponse> getAllCars();
    CarResponse getCarById(String carId);
    void changeStatusOfCar(String carModel);
}
