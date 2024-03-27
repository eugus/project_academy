package br.com.versao2.Academia.exceptions.manipuladas;

public class ExistingEntity extends RuntimeException {



    public ExistingEntity(String msg){
        super( msg);
    }

}
