package br.com.challengeApiFilms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private HttpStatus status;
    private String message;

    private String stackTrace;

    private Object data;

    public ErrorResponse(){
        this.timestamp = new Date();
    }

    public ErrorResponse(HttpStatus status, String message) {
        this();
        this.code = status.value();
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message, String stackTrace) {
        this(status, message);
        this.stackTrace = stackTrace;
    }

    public ErrorResponse(HttpStatus status, String message, String stackTrace, Object data) {
        this(status,message,stackTrace);
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
