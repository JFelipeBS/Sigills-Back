package br.edu.ufersa.pw.sigillsback.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.pw.sigillsback.DTO.AccountDto;
import br.edu.ufersa.pw.sigillsback.DTO.CreateAccountDto;
import br.edu.ufersa.pw.sigillsback.entity.Account;
import br.edu.ufersa.pw.sigillsback.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;
    
    @GetMapping
    public List<AccountDto> findAll() {
        return service.findAll();
    }

    @GetMapping("search/byId/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable String id) {
        Optional<AccountDto> account = service.findById(id);
    
        if (account.isPresent()){
            return ResponseEntity.ok(account.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<AccountDto> add(@Valid @RequestBody CreateAccountDto account){

        Account entity = new Account();
        entity.setName(account.getName());
        entity.setType(account.getType());

        AccountDto dto = service.add(entity);

        if (dto == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable String id, @RequestBody Account accountReceived) {
        Optional<AccountDto> account = service.update(id, accountReceived);
        if (account.isPresent()){
            return ResponseEntity.ok(account.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        AccountDto dto = new AccountDto();
        dto.setId(id);

        try {
            service.deleteById(dto);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
