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

import br.edu.ufersa.pw.sigillsback.DTO.ExitDto;
import br.edu.ufersa.pw.sigillsback.entity.transition.Exit;
import br.edu.ufersa.pw.sigillsback.service.ExitService;

@RestController
@RequestMapping("/exit")
public class ExitController{
    
    @Autowired
    private ExitService service;

    @GetMapping
    public List<ExitDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExitDto> getById(@PathVariable String id) {
    Optional<ExitDto> user = service.findById(id);
    
    if (user.isPresent()){
        return ResponseEntity.ok(user.get());
    }else{
        return ResponseEntity.badRequest().build();
    }
}

    @PostMapping
    public ResponseEntity<ExitDto> add(@Valid @RequestBody Exit exit){
        ExitDto dto = service.add(exit);

        if (dto == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExitDto> update(@PathVariable String id, @RequestBody ExitDto dto) {
        Optional<ExitDto> compromisso = service.update(id, dto);
        if (compromisso.isPresent()){
            return ResponseEntity.ok(compromisso.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        ExitDto dto = new ExitDto();
        dto.setId(id);

        try {
            service.deleteById(dto);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
