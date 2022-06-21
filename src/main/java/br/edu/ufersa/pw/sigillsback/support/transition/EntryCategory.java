package br.edu.ufersa.pw.sigillsback.support.transition;

public enum EntryCategory {
    OUTRO("outro"),
    SALDO_INICIAL("saldo inicial"),
    SALARIO("salario"),
    EMPRESTADO("emprestado");

    private final String description;

    EntryCategory(String description) {
        this.description = description;
    }

    public String getType() {
        return this.description;
    }
}
