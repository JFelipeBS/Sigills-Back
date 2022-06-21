package br.edu.ufersa.pw.sigillsback.support;

public enum AccountType {
    CORRENTE("corrente"),
    POUPANÇA("poupança");

    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
