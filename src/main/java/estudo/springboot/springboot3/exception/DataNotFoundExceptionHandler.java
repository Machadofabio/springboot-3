package estudo.springboot.springboot3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DataNotFoundExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<DataNotFoundExceptionDetails> handlerDataNotFoundException(DataNotFoundException dnf){
        return new ResponseEntity<>(DataNotFoundExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .details(dnf.getMessage())
                .developerMessage("Please, verify data inputed.")
                .title("Data not found").build(), HttpStatus.NOT_FOUND);
    }
}
