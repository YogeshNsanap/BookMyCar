package book.my.car.BookMyCarCarService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
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
    private String status;
}
