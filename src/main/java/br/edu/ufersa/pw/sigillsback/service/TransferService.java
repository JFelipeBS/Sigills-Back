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
import br.edu.ufersa.pw.sigillsback.DTO.TransferDto;
import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.entity.transition.Transfer;
import br.edu.ufersa.pw.sigillsback.repository.transition.TransferRepository;

@Service
public class TransferService {
    
    @Autowired
    private TransferRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper mapper;

    public List<TransferDto> findAll(){
        List <TransferDto> result = this.findAllOut();
        result.addAll(this.findAllIn());
        return result;
    }

    public List<TransferDto> findAllIn(){
        List<TransferDto> list = new ArrayList<TransferDto>();

        List<Account> userAccounts = new ArrayList<Account>();

        for (AccountDto accountDto : accountService.findAll()) {
            userAccounts.add(mapper.map(accountDto, Account.class));
        }

        for (Account account : userAccounts) {
            List<Transfer> accountTransfers = repository.findByDestiny(account);
            for (Transfer transfer : accountTransfers) {
                list.add(mapper.map(transfer, TransferDto.class));
            }
        }

        return list;
    }

    public List<TransferDto> findAllOut(){
        List<TransferDto> list = new ArrayList<TransferDto>();

        List<Account> userAccounts = new ArrayList<Account>();

        for (AccountDto accountDto : accountService.findAll()) {
            userAccounts.add(mapper.map(accountDto, Account.class));
        }

        for (Account account : userAccounts) {
            List<Transfer> accountTransfers = repository.findByOrigin(account);
            for (Transfer transfer : accountTransfers) {
                list.add(mapper.map(transfer, TransferDto.class));
            }
        }

        return list;
    }

    public Optional<TransferDto> findById(String id){
        Optional<Transfer> transfer = repository.findById(Long.valueOf(id));
        if (transfer.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of( mapper.map(transfer.get(),TransferDto.class));
    }

    public TransferDto add(Transfer transfer){
        Transfer trasit = new Transfer();
       
        trasit.setOrigin(transfer.getOrigin());
        trasit.setDestiny(transfer.getDestiny());
        trasit.setValue(transfer.getValue());
        trasit.setDate(transfer.getDate());
        trasit.setDescription(transfer.getDescription());
        
        return mapper.map(repository.save(trasit),TransferDto.class);
    }

    public Optional<TransferDto> update(String id, Transfer dto) {
        Optional<Transfer> transfer = repository.findById(Long.valueOf(id));
        if (transfer.isEmpty()) {
          return Optional.empty();
        }
    
        transfer.get().setDestiny(dto.getDestiny());
        transfer.get().setOrigin(dto.getOrigin());
        transfer.get().setValue(dto.getValue());
        transfer.get().setDate(dto.getDate());
        transfer.get().setDescription(dto.getDescription());

        return Optional.of(mapper.map(repository.save(transfer.get()), TransferDto.class));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Transfer transfer){
        repository.deleteById(transfer.getId());
    }

}
