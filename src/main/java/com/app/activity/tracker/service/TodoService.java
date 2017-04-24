package com.app.activity.tracker.service;

import java.util.List;

import com.app.activity.tracker.form.TodoForm;
import com.app.activity.tracker.model.User;

public interface TodoService {
	public List<TodoForm> getTodo(String username);
	public String addTodoTask(TodoForm todoForm, User user);
}
