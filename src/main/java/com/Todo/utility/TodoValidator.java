package com.Todo.utility;

import com.Todo.bean.Todo;

public class TodoValidator {
	public static boolean validateTodo(Todo todo) {
		if (todo.getId() > 0) {
			return false;
		} else {
			return true;
		}
	}
}
