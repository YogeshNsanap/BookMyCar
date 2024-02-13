package book.my.car.BookMyCarHotelService.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {
    private String hotel;
    private String room;
    private Integer stay;
    private String manageParking;
    private Long finalCost;
}
