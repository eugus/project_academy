package br.com.versao2.Academia.exceptions.manipuladas;

import br.com.versao2.Academia.service.ForbiddenExeception;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    /*@ExceptionHandler(ForbiddenExeception.class)
    public ResponseEntity<Object> erro(ForbiddenExeception ex){
        ErroResponse erroResponse = new ErroResponse("Forbidden", ex.getMessage());
        return new ResponseEntity<>(erroResponse, HttpStatus.FORBIDDEN);
    }

     */

    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<StandardErro> UnableToCreateAluno(IdNotFound e, WebRequest request){
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setError("ERRO!");
        error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardErro> data(DataIntegrityViolationException e, WebRequest request){
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setError("Impossível exluir este plano que contém alunos inseridos!");
        //error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }







}
