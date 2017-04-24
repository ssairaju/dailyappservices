package com.app.activity.tracker.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.activity.tracker.dao.TodoDao;
import com.app.activity.tracker.form.TodoForm;
import com.app.activity.tracker.model.Todo;
import com.app.activity.tracker.model.User;

@Service("todoService")
public class TodoServiceImpl implements TodoService {
	
	private Logger logger = Logger.getLogger(TodoServiceImpl.class);
	
	@Autowired
	private TodoDao dao;

	public void setDao(TodoDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<TodoForm> getTodo(String username) {
		logger.info("****getTodo :" + username);
		List<TodoForm> todoFormList = new ArrayList<TodoForm>();
		List<Todo> todoList = dao.getTodoList(username);
		for(Todo todo : todoList) {
			TodoForm todoForm = new TodoForm();
			todoForm.setId(todo.getId());
			todoForm.setName(todo.getName());
			todoForm.setCategory(todo.getCategory());
			todoForm.setDescription(todo.getDescription());
			todoForm.setStatus(todo.getStatus());
			todoFormList.add(todoForm);
		}
		return todoFormList;
	}

	@Override
	public String addTodoTask(TodoForm todoForm, User user) {
		Todo todo = new Todo();
		todo.setName(todoForm.getName());
		todo.setCategory(todoForm.getCategory());
		todo.setDescription(todoForm.getDescription());
		todo.setStatus("false");
		todo.setUsers(user);
		dao.addTodo(todo);
		return "Success Add TODO";
	}

}
