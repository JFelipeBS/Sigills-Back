package br.edu.ufersa.pw.sigillsback.repository.transition;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.entity.transition.Exit;

public interface ExitRepository extends JpaRepository<Exit, Long> {
    
    public List<Exit> findByAccount(Account account);
    public Optional<Exit> findById(Long id);
    public void deleteById(Long id);

}
