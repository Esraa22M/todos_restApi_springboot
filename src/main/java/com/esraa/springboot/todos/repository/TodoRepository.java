package com.esraa.springboot.todos.repository;

import com.esraa.springboot.todos.entities.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo , Long> {
}
