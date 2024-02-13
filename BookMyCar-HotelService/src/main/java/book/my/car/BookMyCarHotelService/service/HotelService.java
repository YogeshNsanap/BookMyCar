package book.my.car.BookMyCarHotelService.service;

import book.my.car.BookMyCarHotelService.request.HotelRequest;
import book.my.car.BookMyCarHotelService.response.HotelResponse;

public interface HotelService {
    HotelResponse saveHotelBooking(HotelRequest hotelRequest);
}
