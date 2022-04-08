package irwing.moura.shrtner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Url {

    @Id
    @GeneratedValue
    private long id;
    @Lob
    private String originalLink;
    private String shortLink;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

}
