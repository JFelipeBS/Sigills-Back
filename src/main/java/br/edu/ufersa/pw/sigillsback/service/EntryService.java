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
import br.edu.ufersa.pw.sigillsback.DTO.EntryDto;
import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.entity.transition.Entry;
import br.edu.ufersa.pw.sigillsback.repository.transition.EntryRepository;

@Service
public class EntryService {

    @Autowired
    private EntryRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper mapper;

    public List<EntryDto> findAll() {
        List<EntryDto> list = new ArrayList<EntryDto>();

        List<Account> userAccounts = new ArrayList<Account>();

        for (AccountDto accountDto : accountService.findAll()) {
            userAccounts.add(mapper.map(accountDto, Account.class));
        }

        for (Account account : userAccounts) {
            List<Entry> accountEntries = repository.findByAccount(account);
            for (Entry entry : accountEntries) {
                list.add(mapper.map(entry, EntryDto.class));
            }
        }

        return list;
    }

    public Optional<EntryDto> findById(String id) {
        Optional<Entry> entry = repository.findById(Long.valueOf(id));
        if (entry.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.map(entry.get(), EntryDto.class));
    }

    public EntryDto add(Entry entry) {
        Entry card = new Entry();
        card.setAccount(entry.getAccount());
        card.setCategory(entry.getCategory());
        card.setDescription(entry.getDescription());
        card.setDate(entry.getDate());
        card.setValue(entry.getValue());

        return mapper.map(repository.save(card), EntryDto.class);
    }

    public Optional<EntryDto> update(String id, EntryDto dto) {
        Optional<Entry> entry = repository.findById(Long.valueOf(id));
        if (entry.isEmpty()) {
            return Optional.empty();
        }

        entry.get().setAccount(dto.getAccount());
        entry.get().setCategory(dto.getCategory());
        entry.get().setDate(dto.getDate());
        entry.get().setDescription(dto.getDescription());
        entry.get().setValue(dto.getValue());

        return Optional.of(mapper.map(repository.save(entry.get()), EntryDto.class));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(EntryDto entry) {
        repository.deleteById(entry.getId());
    }

}
