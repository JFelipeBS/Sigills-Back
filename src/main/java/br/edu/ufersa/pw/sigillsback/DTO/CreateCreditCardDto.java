package br.edu.ufersa.pw.sigillsback.DTO;


public class CreateCreditCardDto {

    private String name;
    private int dueDate;    
    private int closingDate;
    
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
    
}
