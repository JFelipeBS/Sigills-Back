package br.edu.ufersa.pw.sigillsback.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import br.edu.ufersa.pw.sigillsback.DTO.CreatedUserDto;
import br.edu.ufersa.pw.sigillsback.DTO.UserDto;
import br.edu.ufersa.pw.sigillsback.entity.User;
import br.edu.ufersa.pw.sigillsback.repository.UserRepository;
import br.edu.ufersa.pw.sigillsback.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository repository;
    @Autowired
    UserService service;

    @GetMapping("search/byEmail")
    public UserDto getByEmail(@Param("email") String email){
        return service.findByEmail(email);
    }

    @GetMapping("/{uuid}")
        public ResponseEntity<UserDto> getByUuid(@PathVariable String uuid) {
        Optional<UserDto> user = service.findByUuid(uuid);
        
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> add(@Valid @RequestBody User user){
        UserDto dto = service.save(user);

        if (dto == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<String> deleteByEmail(@PathVariable String email){
        UserDto dto = new UserDto();
        dto.setEmail(email);

        try {
            service.deleteByEmail(dto);
            return new ResponseEntity<>(email, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserDto> update(@PathVariable String uuid, @RequestBody CreatedUserDto dto) {
        Optional<UserDto> user = service.update(uuid, dto);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
