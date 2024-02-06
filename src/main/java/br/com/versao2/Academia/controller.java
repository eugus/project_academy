package br.com.versao2.Academia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class controller {

    @GetMapping
    public String ola(){
        return "olá";
    }
}
