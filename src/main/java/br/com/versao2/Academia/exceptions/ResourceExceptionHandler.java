package br.com.versao2.Academia.exceptions;

import br.com.versao2.Academia.entitys.ErroResponse;
import br.com.versao2.Academia.service.ForbiddenExeception;
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

    @ExceptionHandler(ForbiddenExeception.class)
    public ResponseEntity<StandardErro> UnableToCreateAluno(ForbiddenExeception e, WebRequest request){
        StandardErro erro = new StandardErro();
        erro.setTimestamp(Instant.now());
        erro.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        erro.setError("teste");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }







}
