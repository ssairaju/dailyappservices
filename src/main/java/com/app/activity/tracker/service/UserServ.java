	package com.app.activity.tracker.service;

import java.util.List;

import com.app.activity.tracker.form.ReminderForm;
import com.app.activity.tracker.model.Category;
import com.app.activity.tracker.model.Reminder;
import com.app.activity.tracker.model.User;

public interface UserServ {
	public User isValidUser(String user,String pass);
	public int createRemainder(Reminder reminder);
	public User getUserByUserName(String uname);
	public List<ReminderForm> getReminders(String username);
	public ReminderForm getReminder(int id);
	public String saveUser(User user);
	public List<Category> getCategories();
}