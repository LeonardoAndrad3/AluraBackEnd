package br.com.challengeApiFilms.controller;

import br.com.challengeApiFilms.entity.ErrorResponse;
import br.com.challengeApiFilms.service.exceptions.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class Advice {

        @ResponseBody
        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
            HttpStatus status = getStatus(request);
            return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage()), status);
        }

        private HttpStatus getStatus(HttpServletRequest request) {
            Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
            HttpStatus status = HttpStatus.resolve(code);
            return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        }
}
