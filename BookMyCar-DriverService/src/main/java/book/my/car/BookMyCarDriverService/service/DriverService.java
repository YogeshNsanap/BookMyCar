package book.my.car.BookMyCarDriverService.service;

import book.my.car.BookMyCarDriverService.request.DriverRequest;
import book.my.car.BookMyCarDriverService.response.DriverResponse;

import java.util.List;

public interface DriverService {
    String saveDriver(DriverRequest driverRequest);
    List<DriverResponse> getAllDrivers();
    void changeStatusOfDriver(Long mobNo);
}
