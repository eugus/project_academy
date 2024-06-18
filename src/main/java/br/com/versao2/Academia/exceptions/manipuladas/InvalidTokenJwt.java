package br.com.versao2.Academia.exceptions.manipuladas;

public class InvalidTokenJwt extends RuntimeException {

    public InvalidTokenJwt(String msg){
        super(msg);
    }
}
