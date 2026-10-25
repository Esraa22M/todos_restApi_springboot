package com.esraa.springboot.todos.controller;

import com.esraa.springboot.todos.request.TodoRequest;
import com.esraa.springboot.todos.response.TodoResponse;
import com.esraa.springboot.todos.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Todo Rest Api" , description = "Operations to manage User Todos")
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "create todo" , description = "create todo for signed in user")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@Valid @RequestBody TodoRequest todoRequest){
        return todoService.createTodo(todoRequest);
    }
}
