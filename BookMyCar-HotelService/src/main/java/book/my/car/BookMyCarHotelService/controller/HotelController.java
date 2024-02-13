package book.my.car.BookMyCarHotelService.controller;

import book.my.car.BookMyCarHotelService.request.HotelRequest;
import book.my.car.BookMyCarHotelService.response.HotelResponse;
import book.my.car.BookMyCarHotelService.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotelBooking")
@CrossOrigin
@Slf4j
public class HotelController {
    @Autowired
    HotelService hotelService;
    @PostMapping
    public ResponseEntity<HotelResponse> createHotelBooking(@RequestBody HotelRequest hotelRequest){
        log.info("Entered hotelbooking controller");
        HotelResponse hotelResponse=hotelService.saveHotelBooking(hotelRequest);
        log.info("Hotelresponse object:" + hotelResponse);
        return new ResponseEntity<>(hotelService.saveHotelBooking(hotelRequest), HttpStatus.OK);
    }
}
