package book.my.car.BookMyCarBookingService.service;

import book.my.car.BookMyCarBookingService.request.BookingRequest;
import book.my.car.BookMyCarBookingService.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse saveBooking(BookingRequest bookingRequest);
    List<BookingResponse> getAllBookings();
}
