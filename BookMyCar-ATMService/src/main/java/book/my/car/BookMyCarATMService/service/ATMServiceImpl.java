package book.my.car.BookMyCarATMService.service;

import book.my.car.BookMyCarATMService.entity.ATM;
import book.my.car.BookMyCarATMService.repo.ATMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class ATMServiceImpl implements ATMService {
    @Autowired
    ATMRepo atmRepo;

    @Override
    public ATM saveATM(ATM atm) {
        WebClient webClient=WebClient.create();
        webClient.patch()
                .uri("http://localhost:9094/driver/book/" + atm.getDriverMobNo())
                .exchange()
                .block();
        webClient.patch()
                .uri("http://localhost:9092/car/book/" + atm.getCarModel())
                .exchange()
                .block();
        return atmRepo.save(atm);
    }

    @Override
    public List<ATM> getATMByEmail(String email) {
        return atmRepo.findByEmail(email);
    }
}
