package book.my.car.BookMyCarHotelService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponse {
    private String id;
    private String hotel;
    private String room;
    private Integer stay;
    private String manageParking;
    private Long finalCost;
}
