package br.edu.ufersa.pw.sigillsback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.pw.sigillsback.DTO.AccountDto;
import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.entity.transition.Entry;
import br.edu.ufersa.pw.sigillsback.entity.transition.Exit;
import br.edu.ufersa.pw.sigillsback.entity.transition.Transfer;
import br.edu.ufersa.pw.sigillsback.repository.AccountRepository;
import br.edu.ufersa.pw.sigillsback.repository.transition.EntryRepository;
import br.edu.ufersa.pw.sigillsback.repository.transition.ExitRepository;
import br.edu.ufersa.pw.sigillsback.repository.transition.TransferRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ExitRepository exitRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private ModelMapper mapper;

    public List<AccountDto> findAll() {
        List<AccountDto> list = new ArrayList<AccountDto>();
        List<Account> userAccounts = repository.findByUser(userService.currentUser());

        for (Account account : userAccounts) {
            AccountDto partial = mapper.map(account, AccountDto.class);
            partial.setBalance(this.balance(account));
            list.add(partial);
        }

        return list;
    }

    public Optional<AccountDto> findById(String id) {
        Optional<Account> account = repository.findById(Long.valueOf(id));
        if (account.isEmpty()) {
            return Optional.empty();
        }
        Optional<AccountDto> result = Optional.of(mapper.map(account.get(), AccountDto.class));
        result.get().setBalance(this.balance(account.get()));
        return result;
    }

    public double balance(Account account) {
        double result = 0;

        for (Entry entry : entryRepository.findByAccount(account)) {
            result += entry.getValue();
        }

        for (Exit exit : exitRepository.findByAccount(account)) {
            result -= exit.getValue();
        }

        for (Transfer transferIn : transferRepository.findByDestiny(account)) {
            result += transferIn.getValue();
        }

        for (Transfer transferOut : transferRepository.findByOrigin(account)) {
            result -= transferOut.getValue();
        }

        return result;
    }

    public AccountDto add(Account account) {
        Account accoun = new Account();
        accoun.setName(account.getName());
        accoun.setType(account.getType());
        accoun.setUser(userService.currentUser());

        return mapper.map(repository.save(accoun), AccountDto.class);
    }

    public Optional<AccountDto> update(String id, Account accountReceived) {
        Optional<Account> account = repository.findById(Long.valueOf(id));
        if (account.isEmpty()) {
            return Optional.empty();
        }

        account.get().setName(accountReceived.getName());
        account.get().setType(accountReceived.getType());

        return Optional.of(mapper.map(repository.save(account.get()), AccountDto.class));

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(AccountDto account) {
        repository.deleteById(account.getId());
    }

}
