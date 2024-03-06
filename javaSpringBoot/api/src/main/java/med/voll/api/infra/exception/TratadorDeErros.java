package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
     public ResponseEntity tratarError404(){
         return ResponseEntity.notFound().build();
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity tratarError400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DataBinder::new).toList());
     }

     @ExceptionHandler(ValidacaoException.class)
     public ResponseEntity tratrarErroRegraDeNegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
     }

     private record DataErrorValidation(String campo, String mensage){

        public DataErrorValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

     }
}
