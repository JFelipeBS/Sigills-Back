package br.edu.ufersa.pw.sigillsback.DTO;


public class CreditCardDto {

    private Long id;
    private String name;
    private int dueDate;    
    private int closingDate;
    private UserDto user;
    
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
    public int getDueDate() {
        return dueDate;
    }
    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }
    public int getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(int closingDate) {
        this.closingDate = closingDate;
    }
    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }

        
    
}
