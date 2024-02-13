package book.my.car.BookMyCarDriverService.service;

import book.my.car.BookMyCarDriverService.entity.Driver;
import book.my.car.BookMyCarDriverService.repo.DriverRepo;
import book.my.car.BookMyCarDriverService.request.DriverRequest;
import book.my.car.BookMyCarDriverService.response.DriverResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DriverServiceImpl implements DriverService{
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public String saveDriver(DriverRequest driverRequest) {
        Driver driver=modelMapper.map(driverRequest, Driver.class);
        driver.setStatus("available");
        return driverRepo.save (driver) != null ? "Driver saved" : "Driver didnt save, please try again";
    }

    @Override
    public List<DriverResponse> getAllDrivers() {
        return driverRepo.findAll().stream()
                .filter(x->x.getStatus().equals("available"))
                .map(e->modelMapper.map(e, DriverResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void changeStatusOfDriver(Long mobNo) {
        log.info("entered book driver service:"+mobNo);
        Driver driver=driverRepo.findByDriverMobNo(mobNo).get();
        driver.setStatus("booked");
        driver.setAvailability ("Not available");
        driverRepo.save(driver);
    }
}
