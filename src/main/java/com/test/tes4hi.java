package com.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.hibernate.restaurant.AbstractRestaurant;
import com.hibernate.restaurant.Restaurant;
import com.hibernate.user.AbstractUser;
import com.hibernate.user.HibernateSessionFactory;
import com.hibernate.user.User;

public class tes4hi {
	public static void main(String[] args) throws HibernateException {
		tes4hi hi = new tes4hi();
		// hi.addUser();
		// hi.searchUser();
		// hi.deleteUser();
		// hi.updateUser("zhousy");
//		List<AbstractUser> qwe = hi.listSearch("zhousy");
//		for (int i = 0; i < qwe.size(); i++) {
//			System.out.println(qwe.get(i).getUserEmail());
//			System.out.println(qwe.get(i).getUserRealName());
//			System.out.println(qwe.get(i).getUserSex());
//			System.out.println(qwe.get(i).getUserTel());
//			System.out.println(qwe.get(i).getUserPwd());
//		}
//		hi.randResultSet();

	}

	/**
	 * 添加用户
	 */
	private void addUser() throws HibernateException {
		// TODO Auto-generated method stub
		User user = new User();

		// 添加
		user.setUserName("zhou");
		// 初始化，读取配置文件hibernate.cfg.xml
		// 实例化 Configuration
		Configuration config = new Configuration().configure();
		// 读取创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		// 打开session
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			// 开始一个事务
			tx = session.beginTransaction();
			// 持久化操作
			session.save(user);
			// 提交事务
			tx.commit();
			System.out.println("success");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	/**
	 * 查找用户
	 */
	private void searchUser() throws HibernateException {
		// 实例化 Configuration
		Configuration config = new Configuration().configure();
		// 读取创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		// 打开session
		Session session = sessionFactory.openSession();
		// 加载数据
		User user;
		SQLQuery query = session.createSQLQuery(
				"select count(id) as cnt from user").addScalar("cnt",
				Hibernate.INTEGER);
		int count = (Integer) query.uniqueResult();

		for (int i = 1; i <= count; i++) {
			user = (User) session.get(User.class, new Integer(i));
			System.out
					.println(user.getId() + "    " + user.getUserName()
							+ "    " + user.getUserPwd() + "    "
							+ user.getUserSex() + "    " + user.getUserEmail()
							+ "    " + user.getUserTel());
		}
	}

	private List<AbstractUser> listSearch(String username)
			throws HibernateException {
		// ArrayList<User> list = new ArrayList<User>();
		// 实例化 Configuration
		// Configuration config = new Configuration().configure();
		// 读取创建sessionFactory
		// SessionFactory sessionFactory = config.buildSessionFactory();
		// 打开session
		// Session session = sessionFactory.openSession();
		Session session = HibernateSessionFactory.getSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", username));
		List list = cri.list();
		User user = (User) list.get(0);
		// System.out.println("E_Mail:"+user.getUserEmail());

		List<AbstractUser> rs = new ArrayList<AbstractUser>();
		rs.add(user);
		// for(int i=0;i<rs.size(); i++){
		// System.out.println(rs.get(i).getUserEmail());
		// System.out.println(rs.get(i).getUserRealName());
		// System.out.println(rs.get(i).getUserSex());
		// System.out.println(rs.get(i).getUserTel());
		// }

		return rs;

	}

	/**
	 * 刪除用戶
	 */
	private void deleteUser() throws HibernateException {
		// 实例化 Configuration
		Configuration config = new Configuration().configure();
		// 读取创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		// 打开session
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = (User) session.get(User.class, new Integer(1));
		try {
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	/**
	 * 修改用戶
	 */
	private void updateUser(String username) throws HibernateException {
		// 实例化 Configuration
		Configuration config = new Configuration().configure();
		// 读取创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		// 打开session
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = (User) session.get(User.class, new Integer(2));
		user.setUserName(username);
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
			System.out.println("success");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}

	}


}
