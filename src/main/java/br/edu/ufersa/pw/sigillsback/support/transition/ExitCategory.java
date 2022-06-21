package br.edu.ufersa.pw.sigillsback.support.transition;

public enum ExitCategory {
    OUTRO("outro"),
    MORADIA("saldo inicial"),
    SAUDE("saude"),
    EDUCACAO("educacao"),
    MERCADO("mercado"),
    TRANSPORTE("transporte");

    private final String description;

    ExitCategory(String description) {
        this.description = description;
    }

    public String getType() {
        return this.description;
    }
}
