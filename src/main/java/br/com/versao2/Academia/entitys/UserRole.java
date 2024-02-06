package br.com.versao2.Academia.entitys;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

     UserRole(String role){
        this.role = role;
    }

    public String getRole(){
         return role;
    }
}
