package irwing.moura.shrtner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpiredUrlException extends RuntimeException {
    public ExpiredUrlException() {
        super("Url does not exist or it might have expired.");
    }
}
