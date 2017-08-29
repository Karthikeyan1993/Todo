package com.Todo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.Todo.bean.Todo;
import com.Todo.repository.TodoRepository;
import com.Todo.service.TodoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class TodoServiceTest {

	@Mock
	private TodoRepository todoRepository;

	@InjectMocks
	private TodoServiceImpl todoService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllToDo() {
		List<Todo> todolist = new ArrayList<>();
		todolist.add(new Todo("Test One", true));
		todolist.add(new Todo("Test Two", true));
		todolist.add(new Todo("Test Three", true));
		when(todoRepository.findAll()).thenReturn(todolist);
		List<Todo> result = todoService.getAllTodo();
		assertEquals(3, result.size());
	}

	@Test
	public void testGetToDoById() {
		Todo t1 = new Todo("Sample Todo", false);
		when(todoRepository.findOne(1L)).thenReturn(t1);
		Todo todo = todoService.getTodoById(1L);
		assertEquals(false, todo.isActivte());
		assertEquals("Sample Todo", todo.getName());

	}

	@Test
	public void testSaveToDo() {
		Todo t1 = new Todo("Sample Test Todo", true);
		when(todoRepository.save(t1)).thenReturn(t1);
		Todo todo = todoService.saveTodo(t1);
		assertEquals(true, todo.isActivte());
		assertEquals("Sample Test Todo", todo.getName());

	}

	@Test
	public void deleteToDo() {
		Todo t1 = new Todo("Sample Test Todo", true);
		todoService.removeTodo(t1.getId());
		verify(todoRepository, times(1)).delete(t1.getId());
	}

}
