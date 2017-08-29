package com.Todo.bean;

import javax.persistence.*;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TODO_ID")
	private Long id;

	@Column(name = "TODO_NAME")
	private String name;

	@Column(name = "TODO_ACTIVE")
	private boolean isActivte;

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Todo(String name, boolean isActivte) {
		super();
		this.name = name;
		this.isActivte = isActivte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActivte() {
		return isActivte;
	}

	public void setActivte(boolean isActivte) {
		this.isActivte = isActivte;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", isActivte=" + isActivte + "]";
	}

}
