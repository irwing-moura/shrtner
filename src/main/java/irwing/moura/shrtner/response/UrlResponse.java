package irwing.moura.shrtner.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UrlResponse {

    private String originalUrl;
    private String shortLink;
    private LocalDateTime expirationDate;

}
