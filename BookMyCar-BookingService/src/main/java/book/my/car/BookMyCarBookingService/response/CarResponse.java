package book.my.car.BookMyCarBookingService.response;

import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private String carId;
    private String carImg;
    private String car360;
    private String carCompany;
    private String carModel;
    private String carColor;
    private String carOwner;
    private Double charge;
    private LocalDate regDate;
    private String carType;
    private Integer passangerCapacity;
    private String fuelType;
    private Long totalRunning;
    private String numPlate;
    private LocalDate insuranceValidity;
    private String insurance;
    private String feature;
}
