package br.com.versao2.Academia.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //habilita a config do web security e eu vou configurar nesta classe
//desabilita as configurações do spring security
public class SecurityConfiguration {

    final
    SecurityFilter securityFilter;

    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    /*
        Security Filter Chain -> corrente de filtros que vou aplicar minha requisição
        para fazer a segurança da minha aplicação
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

         httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login", "/auth/register/standard").permitAll()
                        .requestMatchers(HttpMethod.POST, "/aluno").permitAll()
                        .requestMatchers(HttpMethod.GET, "/plano").permitAll()
                        .requestMatchers(HttpMethod.GET, "/aluno").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/plano").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/aluno/{idAluno}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/plano/{codigoPlano}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/aluno/{idPlano}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/aluno/{idAluno}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/plano/{codigoPlano}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/report/{format}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/aluno/page", "plano/pageP").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/csv/alunos").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/report/alunos").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/report/getPdf").permitAll()

                        //p/ as demais requisições apenas seja autenticado independente da role
                        //usuario ta logado? se nao retorna erro

                        .anyRequest().authenticated()
                )


                /*antes que ele caia nessa condição acima quero fazer a verificação
                 do token(ver o usuario/role) para fazer a segurança proposta acima
                 */

                /*
                p/ isso criamos um filter(securityFilter) que é para ocorrer antes da classe
                UsernamePasswordAuthenticationFilter já essa classe é responsavel por gerar o token
                 */
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
    }

    //relacionado a autenticação do usuário(autenticar solicitações de login/validar crendenciais)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
