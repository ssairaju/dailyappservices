package com.app.activity.tracker.dao;

import java.util.List;

import com.app.activity.tracker.model.Todo;

public interface TodoDao {
	public void addTodo(Todo todo);
	public List<Todo> getTodoList(String username);
}
