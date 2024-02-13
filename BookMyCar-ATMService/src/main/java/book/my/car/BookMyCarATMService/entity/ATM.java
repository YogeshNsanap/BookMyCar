package book.my.car.BookMyCarATMService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String fname;
    private String lname;
    private String driverImg;
    private String driverName;
    private Long driverMobNo;
    private Long charges;
    private String hotel;
    private String room;
    private Integer stay;
    private String manageParking;
    private Long finalCost;
    private String carCompany;
    private String carColor;
    private String carModel;
    private String carType;
    private String carImg;
    private String fuelType;
    private String pickUpLocation;
    private String dropLocation;
    private LocalTime pickUpTime;
    private LocalDate pickUpDate;
    private LocalDate dropDate;
    private Integer passangerCapacity;
    private Integer members;
    private Integer days;
    private String cardNo;
    private String driverId;
}