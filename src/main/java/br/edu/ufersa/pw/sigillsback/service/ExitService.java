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
import br.edu.ufersa.pw.sigillsback.DTO.ExitDto;
import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.entity.transition.Exit;
import br.edu.ufersa.pw.sigillsback.repository.transition.ExitRepository;

@Service
public class ExitService {
    
    @Autowired
    private ExitRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper mapper;

    public List<ExitDto> findAll(){
        List<ExitDto> list = new ArrayList<ExitDto>();

        List<Account> userAccounts = new ArrayList<Account>();

        for (AccountDto accountDto : accountService.findAll()) {
            userAccounts.add(mapper.map(accountDto, Account.class));
        }

        for (Account account : userAccounts) {
            List<Exit> accountExits = repository.findByAccount(account);
            for (Exit exit : accountExits) {
                list.add(mapper.map(exit, ExitDto.class));
            }
        }

        return list;
    }

    public Optional<ExitDto> findById(String id){
        Optional<Exit> exit = repository.findById(Long.valueOf(id));
        if (exit.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of( mapper.map(exit.get(),ExitDto.class));
    }

    public ExitDto add(Exit exit){
        Exit card = new Exit();
        card.setAccount(exit.getAccount());
        card.setCategory(exit.getCategory());
        card.setDescription(exit.getDescription());
        card.setDate(exit.getDate());
        card.setValue(exit.getValue());
        
        return mapper.map(repository.save(card),ExitDto.class);
    }

    public Optional<ExitDto> update(String id, ExitDto dto) {
        Optional<Exit> exit = repository.findById(Long.valueOf(id));
        if (exit.isEmpty()) {
          return Optional.empty();
        }
    
        exit.get().setAccount(dto.getAccount());
        exit.get().setDate(dto.getDate());
        exit.get().setCategory(dto.getCategory());;
        exit.get().setDescription(dto.getDescription());
        exit.get().setValue(dto.getValue());

        return Optional.of(mapper.map(repository.save(exit.get()), ExitDto.class));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(ExitDto exit){
        repository.deleteById(exit.getId());
    }

}
