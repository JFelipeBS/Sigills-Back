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

import br.edu.ufersa.pw.sigillsback.DTO.EntryDto;
import br.edu.ufersa.pw.sigillsback.entity.transition.Entry;
import br.edu.ufersa.pw.sigillsback.service.EntryService;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private EntryService service;

    @GetMapping
    public List<EntryDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryDto> getById(@PathVariable String id) {
        Optional<EntryDto> user = service.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<EntryDto> add(@Valid @RequestBody Entry entry) {
        EntryDto dto = service.add(entry);

        if (dto == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntryDto> update(@PathVariable String id, @RequestBody EntryDto dto) {
        Optional<EntryDto> entry = service.update(id, dto);
        if (entry.isPresent()) {
            return ResponseEntity.ok(entry.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        EntryDto dto = new EntryDto();
        dto.setId(id);

        try {
            service.deleteById(dto);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
