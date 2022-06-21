package br.edu.ufersa.pw.sigillsback.DTO;

import java.util.UUID;

public class UserDto {
    
    private UUID uuid;
    private String email;
    private String name;
    
    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    

}
