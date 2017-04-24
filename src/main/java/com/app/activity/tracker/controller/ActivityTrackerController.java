package com.app.activity.tracker.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.activity.tracker.form.ReminderForm;
import com.app.activity.tracker.form.TodoForm;
import com.app.activity.tracker.form.UserForm;
import com.app.activity.tracker.model.Category;
import com.app.activity.tracker.model.Reminder;
import com.app.activity.tracker.model.User;
import com.app.activity.tracker.service.TodoService;
import com.app.activity.tracker.service.UserServ;

@RestController
public class ActivityTrackerController {
	private Logger logger = Logger.getLogger(ActivityTrackerController.class);

	@Autowired
	private UserServ ser;
	@Autowired
	private TodoService todoService;

	@RequestMapping(value = "/isvalid", method = RequestMethod.POST)
	public User isValid(@RequestBody User user){
		User us= ser.isValidUser(user.getUserName(), user.getPassword());
		System.out.println(us.getUserName());
		return us;
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public UserForm userRegistration(@RequestBody UserForm user){
		UserForm us = new UserForm();
		User saveUser = new User();
		saveUser.setFirstName(user.getFirstName());
		saveUser.setLastName(user.getLastName());
		saveUser.setUserName(user.getFirstName() + user.getLastName());
		saveUser.setEmail(user.getEmail());
		saveUser.setPassword(user.getPassword());
		String msg= ser.saveUser(saveUser);
		return us;
	}

	@RequestMapping(value = "/createReminder", method = RequestMethod.POST)
	public ReminderForm createRemainder(@RequestBody ReminderForm reminderForm){
		Reminder reminder = new Reminder();
		reminder.setText(reminderForm.getText());
		reminder.setTitle(reminderForm.getTitle());
		reminder.setUsers(ser.getUserByUserName(reminderForm.getUserName()));
		int res= ser.createRemainder(reminder);
		if(res==1){
			return reminderForm;
		}
		return null;
	}

	@RequestMapping(value = "/getReminders/{username}", method = RequestMethod.GET, headers="Accept=application/json")
	public List<ReminderForm> getReminders(@PathVariable String username){
		List<ReminderForm> reminderList = ser.getReminders(username);
		return reminderList;
	}
	
	@RequestMapping(value = "/getCategories", method = RequestMethod.GET, headers="Accept=application/json")
	public List<Category> getCategories(){
		List<Category> catList=ser.getCategories();
		return catList;
	}
	
	@RequestMapping(value = "/getTodo/{userName}", method = RequestMethod.GET, headers="Accept=application/json")
	public List<TodoForm> getTodo(@PathVariable String userName){
		List<TodoForm> todoList = todoService.getTodo(userName);
		return todoList;
	}
	
	@RequestMapping(value = "/addTodo", method = RequestMethod.POST)
	public TodoForm addTodo(@RequestBody TodoForm todoForm){
		TodoForm tf = new TodoForm();
		User user = ser.getUserByUserName(todoForm.getUserName());
		String msg = todoService.addTodoTask(todoForm, user);
		return tf;
	}
	
	@RequestMapping(value = "/getReminder", method = RequestMethod.POST)
	public ReminderForm getReminderDetail(@RequestBody ReminderForm reminderForm){
		ReminderForm reminder = ser.getReminder(reminderForm.getId());
		return reminder;
	}
}
