package br.edu.ufersa.pw.sigillsback.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.edu.ufersa.pw.sigillsback.entity.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    public Optional<User> findByUuid(UUID uuid);
    public void deleteByEmail(String email);
}
