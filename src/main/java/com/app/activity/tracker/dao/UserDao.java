package com.app.activity.tracker.dao;

import java.util.List;

import com.app.activity.tracker.model.Category;
import com.app.activity.tracker.model.Reminder;
import com.app.activity.tracker.model.User;

public interface UserDao {
	public User validate(String user,String pass);
	public void createRemainder(Reminder reminder);
	public User getUserByUserName(String uname);
	public List<Reminder> getReminders(String username);
	public Reminder getReminder(int id);
	public String saveUser(User user);
	public List<Category> getCategories();
}
