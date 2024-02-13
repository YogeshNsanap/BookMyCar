package book.my.car.BookMyCarCarService.repo;

import book.my.car.BookMyCarCarService.entity.Car;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, String> {
    Optional<Car> findByCarModel(String carModel);
}
