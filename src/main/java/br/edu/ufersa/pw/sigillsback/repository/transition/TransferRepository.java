package br.edu.ufersa.pw.sigillsback.repository.transition;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.entity.transition.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    public List<Transfer> findByOrigin(Account origin);
    public List<Transfer> findByDestiny(Account destiny);
    public Optional<Transfer> findById(Long id);
    public void deleteById(Long id);
    
}
