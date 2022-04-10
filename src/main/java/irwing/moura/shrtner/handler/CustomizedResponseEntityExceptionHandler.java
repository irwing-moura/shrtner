package irwing.moura.shrtner.handler;

import irwing.moura.shrtner.exception.ExpiredUrlException;
import irwing.moura.shrtner.exception.InvalidUrlException;
import irwing.moura.shrtner.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    //CASO A URL SEJA INVALIDA
    @ExceptionHandler(InvalidUrlException.class)
    public final ResponseEntity<Object> handleInvalidUrlException(InvalidUrlException e, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

    }

    //CASO A URL NÃO EXISTA OU TENHA EXPIRADO
    @ExceptionHandler(ExpiredUrlException.class)
    public final ResponseEntity<Object> handleExpiredUrlException(ExpiredUrlException e, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    //LIDAR COM QUALQUER EXCEÇÃO
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
