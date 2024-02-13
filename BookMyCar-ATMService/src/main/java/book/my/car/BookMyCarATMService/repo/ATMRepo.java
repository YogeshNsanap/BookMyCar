package book.my.car.BookMyCarATMService.repo;

import book.my.car.BookMyCarATMService.entity.ATM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ATMRepo extends JpaRepository<ATM,String> {
    List<ATM> findByEmail(String email);
}
