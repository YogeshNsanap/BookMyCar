package book.my.car.BookMyCarBookingService.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private String id;
    private String carCompany;
    private String carColor;
    private String carModel;
    private String carType;
    private String carOwner;
    private String carImg;
    private String numPlate;
    private LocalDate insuranceValidity;
    private String fuelType;
    private String feature;
    private String pickUpLocation;
    private String dropLocation;
    private LocalTime pickUpTime;
    private LocalDate pickUpDate;
    private LocalDate dropDate;
    private Integer passangerCapacity;
    private Integer members;
    private Integer days;
    private Integer numBags;
    private Double totalPrice;
}

