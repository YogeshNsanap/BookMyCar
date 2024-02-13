package book.my.car.BookMyCarATMService.controller;

import book.my.car.BookMyCarATMService.entity.ATM;
import book.my.car.BookMyCarATMService.service.ATMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atm")
@CrossOrigin
@Slf4j
public class ATMController {
    @Autowired
    ATMService atmService;
    @PostMapping
    public ResponseEntity<ATM> createATM(@RequestBody ATM atm){
        log.info("entered atm controller"+atm);
        ATM atm1=atmService.saveATM(atm);
        log.info("return object"+atm1);
        return new ResponseEntity<>(atm1, HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<List<ATM>> findATMByEmail(@PathVariable String email){
        log.info("Entered controller by id"+ email);
        return new ResponseEntity<>(atmService.getATMByEmail(email),HttpStatus.FOUND);
    }
}