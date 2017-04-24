package com.app.activity.tracker.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.activity.tracker.dao.UserDao;
import com.app.activity.tracker.form.ReminderForm;
import com.app.activity.tracker.model.Category;
import com.app.activity.tracker.model.Reminder;
import com.app.activity.tracker.model.User;

@Service("userService")
public class UserServImpl implements UserServ {

	@Autowired
	private UserDao dao;

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public User isValidUser(String user, String pass) {
		return dao.validate(user, pass);
	}

	@Override
	public int createRemainder(Reminder reminder) {
		int res=0;
		try{
			System.out.println("******createRemainder Service");
			dao.createRemainder(reminder);
			res=1;
		}catch(Exception e){
		}
		return res;
	}

	@Override
	public User getUserByUserName(String uname) {
		System.out.println("Service***"+uname);
		return dao.getUserByUserName(uname);
	}

	@Override
	public List<ReminderForm> getReminders(String username) {
		List<Reminder> reminderList = dao.getReminders(username);
		List<ReminderForm> userReminderList = new ArrayList<ReminderForm>(); 
		for(Reminder reminder: reminderList) {
			ReminderForm rf = new ReminderForm();
			rf.setText(reminder.getText());
			rf.setTitle(reminder.getTitle());
			userReminderList.add(rf);
		}
		
		return userReminderList;
	}

	@Override
	public ReminderForm getReminder(int id) {
		ReminderForm rf = new ReminderForm();
		Reminder remainder = dao.getReminder(id);
		rf.setId(remainder.getId());
		rf.setText(remainder.getText());
		rf.setTitle(remainder.getTitle());
		rf.setImagePath(remainder.getImagePath());
		return  rf;
	}

	@Override
	public String saveUser(User user) {
		return dao.saveUser(user);
	}

	@Override
	public List<Category> getCategories() {
		return dao.getCategories();
		/*List<String> categoryList = new ArrayList<String>();
		categoryList.add("");
		categoryList.add("Medication");
		categoryList.add("Grocery");
		categoryList.add("Remainders");
		categoryList.add("Spending");
		return categoryList;*/
	}
}