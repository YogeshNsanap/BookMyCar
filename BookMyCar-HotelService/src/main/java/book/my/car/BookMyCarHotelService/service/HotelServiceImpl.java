package book.my.car.BookMyCarHotelService.service;

import book.my.car.BookMyCarHotelService.entity.Hotel;
import book.my.car.BookMyCarHotelService.repo.HotelRepo;
import book.my.car.BookMyCarHotelService.request.HotelRequest;
import book.my.car.BookMyCarHotelService.response.HotelResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService{
    @Autowired
    HotelRepo hotelRepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public HotelResponse saveHotelBooking(HotelRequest hotelRequest) {
        Hotel hotel=hotelRepo.save(modelMapper.map(hotelRequest,Hotel.class));
        return modelMapper.map(hotel, HotelResponse.class);
    }
}
