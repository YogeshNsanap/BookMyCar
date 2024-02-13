package book.my.car.BookMyCarDriverService.controller;

import book.my.car.BookMyCarDriverService.request.DriverRequest;
import book.my.car.BookMyCarDriverService.response.DriverResponse;
import book.my.car.BookMyCarDriverService.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@CrossOrigin
@Slf4j
public class DriverController {
    @Autowired
    DriverService driverService;
    @PostMapping
    public ResponseEntity<String> createDriver(@RequestBody DriverRequest driverRequest){
        return new ResponseEntity<>(driverService.saveDriver(driverRequest), HttpStatus.OK);
    }
    @GetMapping("/allDrivers")
    public ResponseEntity<List<DriverResponse>> findAllDrivers(){
        return new ResponseEntity<>(driverService.getAllDrivers(),HttpStatus.FOUND);
    }
    @PatchMapping("/book/{mobNo}")
    public void bookDriver(@PathVariable Long mobNo ){
        log.info("entered book driver controller:"+mobNo);
        driverService.changeStatusOfDriver(mobNo);
    }
}
