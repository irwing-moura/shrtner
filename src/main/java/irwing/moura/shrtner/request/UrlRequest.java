package irwing.moura.shrtner.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UrlRequest {


    @ApiModelProperty(value = "https://www.google.com", example = "https://www.google.com")
    private String url;

    @ApiModelProperty(value = "2022-00-00T00:00:00.0000000", example = "2022-00-00T00:00:00.0000000")
    private String expirationDate;

}
