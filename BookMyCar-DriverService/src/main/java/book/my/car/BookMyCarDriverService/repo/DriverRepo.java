package book.my.car.BookMyCarDriverService.repo;

import book.my.car.BookMyCarDriverService.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<Driver,String> {

    Optional<Driver> findByDriverMobNo(Long mobNo);
}
