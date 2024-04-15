package br.com.versao2.Academia.infra.security;

import br.com.versao2.Academia.entitys.Aluno;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    public String secret;

    public String generateToken(Aluno aluno){
        try {
            //recebe uma security(informação) que faz com os hashs sejam unicos na aplicação
            Algorithm algorithm =  Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("academia-auth-api") // quem esta emitindo esse token
                    .withSubject(aluno.getNome()) // quem esta ganhando esse token
                    .withExpiresAt(genExperationData()) //hora que expira o token
                    .sign(algorithm); //gerar o token

            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro enquanto gerava token ", e);
        }
    }
    private Instant genExperationData(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        //instante da criação do token + 2 horas zoneOffset --> Brasilia
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("academia-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return null;
        }
    }



}
