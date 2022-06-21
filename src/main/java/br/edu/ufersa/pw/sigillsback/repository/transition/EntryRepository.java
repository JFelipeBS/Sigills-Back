package br.edu.ufersa.pw.sigillsback.repository.transition;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.entity.transition.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    
    public List<Entry> findByAccount(Account account);
    public Optional<Entry> findById(Long id);
    public void deleteById(Long id);

}
