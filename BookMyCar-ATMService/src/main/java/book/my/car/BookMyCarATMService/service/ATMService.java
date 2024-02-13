package book.my.car.BookMyCarATMService.service;

import book.my.car.BookMyCarATMService.entity.ATM;

import java.util.List;

public interface ATMService {
    ATM saveATM(ATM atm);
    List<ATM> getATMByEmail(String email);
}
