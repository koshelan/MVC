package server.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleConverterErrors(NotFoundException exception) {
//        Throwable cause = exception.getCause() // First cause is a ConversionException
//                                   .getCause(); // Second Cause is your custom exception or some other exception e.g. NullPointerException
//        if(cause.getClass() == UnauthorizedException.class) {
//            return ResponseEntity.status(401).body("Your session has expired");
//        }
        return ResponseEntity.notFound().build();
                //body("Bad Request: [" + cause.getMessage() + "]");
    }
}

