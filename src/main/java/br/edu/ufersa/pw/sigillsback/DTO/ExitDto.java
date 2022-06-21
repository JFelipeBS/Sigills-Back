package br.edu.ufersa.pw.sigillsback.DTO;

import java.util.Calendar;

import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.support.transition.ExitCategory;

public class ExitDto {
    
    private Long id;
    private Double value;
    private Calendar date;
    private String description;
    private ExitCategory category = ExitCategory.OUTRO;
    private Account account;

    
    
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ExitCategory getCategory() {
        return category;
    }
    public void setCategory(ExitCategory category) {
        this.category = category;
    }
    
}
