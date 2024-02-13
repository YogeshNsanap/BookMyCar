package book.my.car.BookMyCarBookingService.service;

import book.my.car.BookMyCarBookingService.entity.Booking;
import book.my.car.BookMyCarBookingService.response.CarResponse;
import book.my.car.BookMyCarBookingService.repo.BookingRepo;
import book.my.car.BookMyCarBookingService.request.BookingRequest;
import book.my.car.BookMyCarBookingService.response.BookingResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService{
    @Autowired
    BookingRepo bookingRepo;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public BookingResponse saveBooking(BookingRequest bookingRequest) {
        log.info("entered save booking");
        String carId=bookingRequest.getCarId();
        Booking booking=modelMapper.map(bookingRequest,Booking.class);
        CarResponse carResponse=restTemplate.getForObject("http://localhost:9092/car/"+carId, CarResponse.class);
        log.info("came back from car api"+carResponse);
        booking.setTotalPrice(calPrice(bookingRequest.getDays(), carResponse.getCharge()));
        bookingRepo.save(booking);
        BookingResponse bookingResponse=modelMapper.map(booking,BookingResponse.class);
        return getBookingResponse (bookingResponse, carResponse);
    }

    private static BookingResponse getBookingResponse(BookingResponse bookingResponse, CarResponse carResponse) {
        bookingResponse.setCarCompany(carResponse.getCarCompany());
        bookingResponse.setCarColor(carResponse.getCarColor());
        bookingResponse.setCarModel(carResponse.getCarModel());
        bookingResponse.setCarType(carResponse.getCarType());
        bookingResponse.setCarOwner(carResponse.getCarOwner());
        bookingResponse.setCarImg(carResponse.getCarImg());
        bookingResponse.setNumPlate(carResponse.getNumPlate());
        bookingResponse.setInsuranceValidity(carResponse.getInsuranceValidity());
        bookingResponse.setFuelType(carResponse.getFuelType());
        bookingResponse.setFeature(carResponse.getFeature());
        bookingResponse.setPassangerCapacity(carResponse.getPassangerCapacity());
        return bookingResponse;
    }

    private Double calPrice(Integer days, Double charge) {
        return (days*charge);
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return null;
    }
}
