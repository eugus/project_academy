package br.com.versao2.Academia.exceptions.manipuladas;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {


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
    public ResponseEntity<?> data(DataIntegrityViolationException e, WebRequest request){
        StandardErro error = new StandardErro();

        error.setTimestamp(Instant.now());
        error.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setError("Verifique se este plano contém alunos ou se o ID é válido");
        //error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(ExistingEntity.class)
    public ResponseEntity<StandardErro> entityExists(ExistingEntity e, WebRequest request){
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setError("Este dado já foi cadastrado");
        error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErro> notBlank(MethodArgumentNotValidException e, WebRequest request){
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setError("Preencha todos os campos ou insira um CPF válido");
        //error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Unathorized.class)
    public ResponseEntity<StandardErro> unathorized(MethodArgumentNotValidException e, WebRequest request){
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(String.valueOf(HttpStatus.FORBIDDEN.value()));
        error.setError("Sem permissão");
        //error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }


    @ExceptionHandler(InvalidTokenJwt.class)
    public ResponseEntity<StandardErro> unathorizeTeste(InvalidTokenJwt e, WebRequest request){
        StandardErro error = new StandardErro();
        error.setTimestamp(Instant.now());
        error.setStatus(String.valueOf(HttpStatus.FORBIDDEN.value()));
        error.setError("Sem permissão");
        //error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }







}
