package com.Todo.service;

import java.util.List;

import com.Todo.bean.Todo;

public interface TodoService {

	public List<Todo> getAllTodo();

	public Todo getTodoById(Long id);

	public void removeTodo(Long id);

	public Todo saveTodo(Todo todo);

}
