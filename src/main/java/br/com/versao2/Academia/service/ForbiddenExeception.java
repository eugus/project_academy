package br.com.versao2.Academia.service;

import java.lang.reflect.AccessibleObject;
import java.nio.file.AccessDeniedException;

public class ForbiddenExeception extends RuntimeException {

    public ForbiddenExeception(String msg){
        super(msg);
    }
}
