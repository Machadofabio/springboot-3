package estudo.springboot.springboot3.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DataNotFoundExceptionDetails {
    private int statusCode;
    private String title;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;

}
