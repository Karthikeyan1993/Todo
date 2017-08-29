package com.Todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Todo.bean.Todo;
import com.Todo.repository.TodoRepository;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(TodoApplication.class);
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Command Line Runner Invoked");
		Todo t1 = new Todo("Montly Status Report", true);
		todoRepository.save(t1);
		Todo t2 = new Todo("Weekly Status Report", true);
		todoRepository.save(t2);
		logger.info("Todo sample data saved");

	}
}
