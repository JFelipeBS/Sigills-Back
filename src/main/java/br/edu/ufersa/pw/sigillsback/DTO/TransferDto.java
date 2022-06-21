package br.edu.ufersa.pw.sigillsback.DTO;

import java.util.Calendar;

public class TransferDto {
    
    private Double value;
    private Calendar date;
    private String description;
    
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

}
