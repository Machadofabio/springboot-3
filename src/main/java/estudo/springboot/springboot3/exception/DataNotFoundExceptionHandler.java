package estudo.springboot.springboot3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
       List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
       String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
       String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

       return new ResponseEntity<>(ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .details("Check the fields error!")
                .developerMessage("Please, verify data inputed.")
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .title("Bad Request Exception, Invalid Fields").build(), HttpStatus.NOT_FOUND);
    }
}
