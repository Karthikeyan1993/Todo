package com.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Todo.bean.Todo;

@Repository("todoRepository")
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
