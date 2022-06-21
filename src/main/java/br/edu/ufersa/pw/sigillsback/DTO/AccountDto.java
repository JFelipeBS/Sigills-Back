package br.edu.ufersa.pw.sigillsback.DTO;

import br.edu.ufersa.pw.sigillsback.support.AccountType;

public class AccountDto {

    private Long id;
    private String name;
    private AccountType type;
    private UserDto user;
    private double balance;

    
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }
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
    public boolean isPresent() {
        return false;
    } 
    

    
    
}
