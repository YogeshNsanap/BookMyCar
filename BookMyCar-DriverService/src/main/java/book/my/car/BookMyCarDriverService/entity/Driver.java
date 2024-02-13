package book.my.car.BookMyCarDriverService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String driverImg;
    private String driverName;
    private String drivingExperiance;
    private Long driverMobNo;
    private String availability;
    private String food;
    private String smoke;
    private String drink;
    private Long charges;
    private String status;
}
