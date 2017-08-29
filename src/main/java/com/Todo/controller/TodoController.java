package com.Todo.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Todo.bean.Response;
import com.Todo.bean.Todo;
import com.Todo.exception.ToDoException;
import com.Todo.service.TodoService;
import com.Todo.utility.TodoValidator;

@RestController
@RequestMapping("api/")
public class TodoController {
	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
	@Autowired
	private TodoService todoService;

	// public ResponseEntity<List<ToDo>> getAllToDo(){

	@RequestMapping(value = "todos", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> getAllTodo() {
		logger.info("Returning all Todo's details");
		return new ResponseEntity<List<Todo>>(todoService.getAllTodo(), HttpStatus.OK);
	}

	@RequestMapping(value = "todos/{id}", method = RequestMethod.GET)
	public ResponseEntity<Todo> findTodoById(@PathVariable Long id) throws ToDoException {
		logger.info("Todo id to return " + id);
		Todo todo = todoService.getTodoById(id);
		if (todo == null || todo.getId() <= 0) {
			throw new ToDoException("Todo does not exits");
		} else {
			return new ResponseEntity<Todo>(todoService.getTodoById(id), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "todos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteTodo(@PathVariable Long id) throws ToDoException {
		logger.info("Todo id to delete " + id);
		Todo todo = todoService.getTodoById(id);
		if (todo == null || id <= 0) {
			throw new ToDoException("Todo id does not exit");
		} else {
			todoService.removeTodo(id);
			return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "ToDo has been deleted"),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "todos", method = RequestMethod.POST)
	public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) throws ToDoException {
		logger.info("Todo to save " + todo);
		if (!TodoValidator.validateTodo(todo)) {
			throw new ToDoException("Id must not be defined");
		}
		return new ResponseEntity<Todo>(todoService.saveTodo(todo), HttpStatus.OK);
	}

}
