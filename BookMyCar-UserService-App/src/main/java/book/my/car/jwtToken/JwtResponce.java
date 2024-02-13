package book.my.car.jwtToken;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponce {

    private String token;
    private String email;
 //   private String password;
}
