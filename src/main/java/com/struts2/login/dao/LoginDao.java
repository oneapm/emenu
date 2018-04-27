package com.struts2.login.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.hibernate.user.HibernateSessionFactory;
import com.hibernate.user.User;

/**
 * 功能：判断用户名和密码是否正确 作者：tang 时间：2014年3月19日 M：处理用户名和密码的业务逻辑层
 */
public class LoginDao {

	/**
	 * 处理方法
	 */
	public boolean strLoginDeal(String username, String password) {
		boolean sign = false;
		Session session = HibernateSessionFactory.getSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", username));
		List list = cri.list();
		if (list != null && list.size() != 0) {
			User user = (User) list.get(0);
			String strUserName = user.getUserName();
			String strUserPwd = user.getUserPwd();
			if (username.equals(strUserName) && password.equals(strUserPwd)) {
				sign = true;
			} else {
				sign = false;
			}
			System.out.println(sign);
		}
		return sign;

	}

	public boolean checkName(String name) {
		boolean sign = false;
		Session session = HibernateSessionFactory.getSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", name));
		List list = cri.list();
		if (list != null && list.size() != 0) {
			User user = (User) list.get(0);
			String strUserName = user.getUserName();
			if (name.equals(strUserName)) {
				sign = true;
			} else {
				sign = false;
			}
		}
		return sign;

	}
	
	public String forgotPassword(String name){
		String res = "";		
		Session session = HibernateSessionFactory.getSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", name));
		List list = cri.list();
		if (list != null && list.size() != 0) {
			User user = (User) list.get(0);
			String strUserName = user.getUserName();
			if (name.equals(strUserName)) {
				res = user.getUserPwd();
			} else {
				res = "";
			}
		}
		return res;
	}

	public int checkSign(String username) {
		int sign = 1;
		Session session = HibernateSessionFactory.getSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", username));
		List list = cri.list();
		if (list != null && list.size() != 0) {
			User user = (User) list.get(0);
			String strUserName = user.getUserName();

			if (username.equals(strUserName)) {
				sign = user.getUserSign();
			} else {
				sign = 5;
			}
		}
		return sign;
	}

	public void AddUser(String userName, String userPwd, String userEmail,
			int userSign, String userTel, String userRealName, String userSex,
			int userRes) {
		User user = new User();

		user.setUserName(userName);
		user.setUserPwd(userPwd);
		user.setUserEmail(userEmail);
		user.setUserSign(userSign);
		user.setUserTel(userTel);
		user.setUserRealName(userRealName);
		user.setUserSex(userSex);
		user.setUserRes(userRes);

		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			// 开始一个事务
			tx = session.beginTransaction();
			// 持久化操作
			session.save(user);
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			System.out.println("success");
			session.close();
		}
	}

	public void AddUser1(String userName, String userPwd, String userEmail,
			int userSign, String userTel, String userRealName, String userSex) {
		User user = new User();

		user.setUserName(userName);
		user.setUserPwd(userPwd);
		user.setUserEmail(userEmail);
		user.setUserSign(userSign);
		user.setUserTel(userTel);
		user.setUserRealName(userRealName);
		user.setUserSex(userSex);

		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			// 开始一个事务
			tx = session.beginTransaction();
			// 持久化操作
			session.save(user);
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			System.out.println("success");
			session.close();
		}
	}
}
