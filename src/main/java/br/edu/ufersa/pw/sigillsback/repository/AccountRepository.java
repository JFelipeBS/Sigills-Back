package br.edu.ufersa.pw.sigillsback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.entity.User;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {

    public List<Account> findByUser(User user);
    public Optional<Account> findById(Long id);
    public void deleteById(Long id);
    
}
