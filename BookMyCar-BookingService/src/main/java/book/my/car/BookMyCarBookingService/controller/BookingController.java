package book.my.car.BookMyCarBookingService.controller;

import book.my.car.BookMyCarBookingService.request.BookingRequest;
import book.my.car.BookMyCarBookingService.response.BookingResponse;
import book.my.car.BookMyCarBookingService.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
@Slf4j
public class BookingController {
    @Autowired
    BookingService bookingService;
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest){
        log.info("Entered booking controller");
        return new ResponseEntity<>(bookingService.saveBooking(bookingRequest), HttpStatus.OK);
    }
}
