package book.my.car.BookMyCarCarService.service;

import book.my.car.BookMyCarCarService.entity.Car;
import book.my.car.BookMyCarCarService.exception.CarNotFoundException;
import book.my.car.BookMyCarCarService.repo.CarRepo;
import book.my.car.BookMyCarCarService.request.CarRequest;
import book.my.car.BookMyCarCarService.response.CarResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarServiceImpl implements CarService{
    @Autowired
    CarRepo carRepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public String saveCar(CarRequest carRequest) {
        Car car=modelMapper.map(carRequest,Car.class);
        car.setStatus("available");
        if (carRepo.save(car)!=null) return "Car saved successfully";
        return "Car didnt save, please try again";
    }

    @Override
    public List<CarResponse> getAllCars() {
        log.info("Entered service");
        return carRepo.findAll()
                .stream()
                .filter(x->x.getStatus().equals("available"))
                .map(e->modelMapper.map(e, CarResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarResponse getCarById(String carId) {
        Car car=carRepo.findById(carId).orElseThrow(()->new CarNotFoundException(carId));
        return modelMapper.map(car, CarResponse.class);
    }
    @Override
    public void changeStatusOfCar(String carModel) {
        log.info("entered book car service:"+carModel);
        Car car=carRepo.findByCarModel(carModel).get();
        car.setStatus("booked");
        carRepo.save(car);
    }

}
