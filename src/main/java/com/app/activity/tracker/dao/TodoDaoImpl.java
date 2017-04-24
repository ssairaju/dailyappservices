package com.app.activity.tracker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.activity.tracker.model.Todo;

@Repository
@Transactional
public class TodoDaoImpl implements TodoDao {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addTodo(Todo todo) {
		Session sess=sessionFactory.getCurrentSession();
		System.out.println("In TodoDao");
		sess.save(todo);
	}
	
	@Override
	public List<Todo> getTodoList(String username) {
		String hql="from Todo a where a.users.userName=:uname";
		Session sess=sessionFactory.getCurrentSession();
		Query qry=sess.createQuery(hql);
		System.out.println("***"+username);
		qry.setString("uname", username);
		List<Todo> todoList=qry.list();
		System.out.println("******getTodoList>>:"+todoList.size());
		return todoList;
	}
}
