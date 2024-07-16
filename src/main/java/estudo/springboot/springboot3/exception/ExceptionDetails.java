package estudo.springboot.springboot3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ExceptionDetails {
    private int statusCode;
    private String title;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
}
