package com.Todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Todo.bean.Todo;
import com.Todo.repository.TodoRepository;

@Service("todoService")
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<Todo> getAllTodo() {
		// TODO Auto-generated method stub
		return todoRepository.findAll();
	}

	@Override
	public Todo saveTodo(Todo todo) {
		// TODO Auto-generated method stub
		return todoRepository.save(todo);
	}

	@Override
	public Todo getTodoById(Long id) {
		// TODO Auto-generated method stub
		return todoRepository.findOne(id);
	}

	@Override
	public void removeTodo(Long id) {
		// TODO Auto-generated method stub

		todoRepository.delete(id);

	}

}
