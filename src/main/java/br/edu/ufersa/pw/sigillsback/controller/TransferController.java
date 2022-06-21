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

import br.edu.ufersa.pw.sigillsback.DTO.TransferDto;
import br.edu.ufersa.pw.sigillsback.entity.transition.Transfer;
import br.edu.ufersa.pw.sigillsback.service.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController{
    
    @Autowired
    private TransferService service;

    @GetMapping
    public List<TransferDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDto> getById(@PathVariable String id) {
    Optional<TransferDto> user = service.findById(id);
    
    if (user.isPresent()){
        return ResponseEntity.ok(user.get());
    }else{
        return ResponseEntity.badRequest().build();
    }
}

    @PostMapping
    public ResponseEntity<TransferDto> add(@Valid @RequestBody Transfer transfer){
        TransferDto dto = service.add(transfer);

        if (dto == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferDto> update(@PathVariable String id, @RequestBody Transfer dto) {
        Optional<TransferDto> compromisso = service.update(id, dto);
        if (compromisso.isPresent()){
            return ResponseEntity.ok(compromisso.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        Transfer dto = new Transfer();
        dto.setId(id);

        try {
            service.deleteById(dto);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
