package book.my.car.BookMyCarDriverService.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequest {

    private String driverImg;
    private String driverName;
    private String drivingExperiance;
    private Long driverMobNo;
    private String availability;
    private String food;
    private String smoke;
    private String drink;
    private Long charges;
}
