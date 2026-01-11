package za.co.app.Userkolekt.rest;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    // Bean validation exception handler
    @ExceptionHandler
    public Mono<ErrorResponse> handleWebExchangeBindException(WebExchangeBindException exception) {
        String errorMessage = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map( error -> error.getDefaultMessage())
                .collect(Collectors.joining(","));
        return Mono.just(ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, errorMessage).build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Mono<ErrorResponse> handleBadCredentialsException(BadCredentialsException exception) {
        return Mono.just(ErrorResponse.builder(exception, HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage()).build());
    }
}
