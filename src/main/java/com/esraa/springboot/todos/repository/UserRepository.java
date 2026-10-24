package com.esraa.springboot.todos.repository;

import com.esraa.springboot.todos.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    Optional <User>findByEmail(String email);

    @Query("SELECT COUNT(u) FROM  User u JOIN u.authorities  a where a.authority = 'ROLE_ADMIN' ")
    long countAdminUsers();
}
