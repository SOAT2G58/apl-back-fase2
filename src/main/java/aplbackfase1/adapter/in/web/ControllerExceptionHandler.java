package aplbackfase1.adapter.in.web;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> resourceNotFoundException(NoSuchElementException ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
