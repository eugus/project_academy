package br.com.versao2.Academia.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity

public class Aluno implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;
    private String nome;
    private LocalDateTime dataCadastro;
    private String cpf;
    private String telefone;
    private String endereco;
    private String password;
    public UserRole role;

    @ManyToOne
    @JoinColumn(name = "ID_PLANO")
    private Plano plano;

    public Aluno() {
    }

    public Aluno(Long idAluno, String nome, LocalDateTime dataCadastro, String cpf, String telefone, String endereco, String password, UserRole role, Plano plano) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataCadastro = dataCadastro = LocalDateTime.now();
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.password = password;
        this.plano = plano;
        this.role = role;
    }


    public Aluno(String nome, String encryptedPassword, UserRole role, String cpf, String telefone, String endereco, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.password = encryptedPassword;
        this.dataCadastro = dataCadastro = LocalDateTime.now();
        this.role = role;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    @JsonIgnore
    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of( new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_ALUNO"));
        else return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nome;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "idAluno=" + idAluno +
                ", nome='" + nome + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", plano=" + plano +
                '}';
    }
}
