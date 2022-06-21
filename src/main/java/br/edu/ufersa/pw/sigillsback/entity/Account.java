package br.edu.ufersa.pw.sigillsback.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.ufersa.pw.sigillsback.entity.transition.Entry;
import br.edu.ufersa.pw.sigillsback.entity.transition.Exit;
import br.edu.ufersa.pw.sigillsback.entity.transition.Transfer;
import br.edu.ufersa.pw.sigillsback.support.AccountType;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "A quem pertence essa conta?")
    private User user;

    @NotBlank(message = "O nome não pode ficar em branco.")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private AccountType type = AccountType.CORRENTE;

    @OneToMany(mappedBy = "account")
    private List<Entry> entries = new ArrayList<Entry>();
    
    @OneToMany(mappedBy = "account")
    private List<Exit> exits = new ArrayList<Exit>();
    
    @OneToMany(mappedBy = "origin")
    private List<Transfer> transfersOut = new ArrayList<Transfer>();
    
    @OneToMany(mappedBy = "destiny")
    private List<Transfer> transfersIn = new ArrayList<Transfer>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    
}
