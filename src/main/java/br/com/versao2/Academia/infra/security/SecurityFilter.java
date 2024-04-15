package br.com.versao2.Academia.infra.security;

import br.com.versao2.Academia.repository.AlunoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    //OncePerRequestFilter acontece uma vez a cada uma requisição

    //O spring security é como se fosse um filter para os nossos controllers

    //Antes de chegar no controller esse filter faz authenticação do usuário(se ele pode fazer isso ou não)

    /*
        essa classe vai fazer a verificação se o token
        enviado pelo usuário é valido(se é um token que nossa aplicação emitiu)
     */

    @Autowired
    TokenService service;
    @Autowired
    AlunoRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = recoverToken(request);
        if (token != null) {
            var login = service.validateToken(token);
            UserDetails aluno = repository.findByNome(login);
            System.out.println("sdada");

            var authentication = new UsernamePasswordAuthenticationToken(aluno, null, aluno.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            /*
            contexto de segurança do spring security, cada componente do spring vai alimentando esse
            security context holder pra ele saber oq ele ja validou ou não,
             salvar as informações do usuário que já tiver autenticado
             */

        }
        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization"); //pegar o header
        if (authHeader == null) return null; // se header não tiver nada já retorna vazio
        return authHeader.replace("Bearer ", ""); /*
        substituindo o "bearer" por uma string vazia para ficar so o token*/
    }

}
