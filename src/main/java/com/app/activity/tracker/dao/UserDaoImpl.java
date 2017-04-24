package com.app.activity.tracker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.activity.tracker.model.Category;
import com.app.activity.tracker.model.Reminder;
import com.app.activity.tracker.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User validate(String userName, String password) {
		Session sess=sessionFactory.getCurrentSession();
		String hql="FROM User u where u.userName=:uName and u.password=:pass";
		Query query = sess.createQuery(hql);
		query.setParameter("uName",userName);
		query.setParameter("pass",password);
		List results = query.list();
		User us=null;

		if(results.size()>0){
			us=(User) results.get(0);
			return us;
		}else{
			return us;
		}
	}

	@Override
	public void createRemainder(Reminder reminder) {
		Session sess=sessionFactory.getCurrentSession();
		System.out.println("****createRemainder DAO");
		sess.save(reminder);

	}

	@Override
	public User getUserByUserName(String uname) {
		System.out.println("***"+uname);		
		String hql="from User u where u.userName=:uname";
		Session sess=sessionFactory.getCurrentSession();
		Query qry=sess.createQuery(hql);
		qry.setString("uname", uname);
		User u=(User) qry.uniqueResult();

		return u;

	}

	@Override
	public List<Reminder> getReminders(String username) {
		String hql="from Reminder a where a.users.userName=:uname";
		Session sess=sessionFactory.getCurrentSession();
		Query qry=sess.createQuery(hql);
		System.out.println("***"+username);
		qry.setString("uname", username);
		List<Reminder> reminderList=qry.list();
		System.out.println("******getAllReminders>>:"+reminderList.size());
		return reminderList;
	}

	@Override
	public Reminder getReminder(int id) {
		Session sess=sessionFactory.getCurrentSession();
		Reminder reminder = (Reminder) sess.load(Reminder.class, id);
		return reminder;
	}

	@Override
	public String saveUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
		return "Success";
	}

	@Override
	public List<Category> getCategories() {
		String hql="from Category";
		Session sess=sessionFactory.getCurrentSession();
		Query qry=sess.createQuery(hql);
		List<Category> categoryList=qry.list();
		System.out.println("******getCategories>>:"+categoryList.size());
		return categoryList;
	}
}
