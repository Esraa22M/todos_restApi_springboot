package com.esraa.springboot.todos.service;

import com.esraa.springboot.todos.request.TodoRequest;
import com.esraa.springboot.todos.response.TodoResponse;

public interface TodoService {
  TodoResponse createTodo(TodoRequest todoRequest);
}
