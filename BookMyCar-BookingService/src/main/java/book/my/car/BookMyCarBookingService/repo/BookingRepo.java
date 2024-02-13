package book.my.car.BookMyCarBookingService.repo;

import book.my.car.BookMyCarBookingService.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {
}
