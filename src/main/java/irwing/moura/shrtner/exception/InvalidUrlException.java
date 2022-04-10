package irwing.moura.shrtner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException() {
        super("Invalid Url.");
    }
}
