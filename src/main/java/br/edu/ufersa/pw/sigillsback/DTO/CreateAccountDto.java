package br.edu.ufersa.pw.sigillsback.DTO;

import br.edu.ufersa.pw.sigillsback.support.AccountType;

public class CreateAccountDto {
    
    private Long id;
    private String name;
    private AccountType type;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public AccountType getType() {
        return type;
    }
    public void setType(AccountType type) {
        this.type = type;
    }
}
