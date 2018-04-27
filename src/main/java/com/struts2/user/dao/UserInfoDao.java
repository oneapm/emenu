package com.struts2.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.hibernate.user.AbstractUser;
import com.hibernate.user.HibernateSessionFactory;
import com.hibernate.user.User;

public class UserInfoDao {
	public List<AbstractUser> listSearch(String username)
			throws HibernateException {
		Session session = HibernateSessionFactory.getSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", username));
		List list = cri.list();
		User user = (User) list.get(0);
		List<AbstractUser> rs = new ArrayList<AbstractUser>();
		rs.add(user);
		return rs;

	}

	public void updateuser(int id, String username, String pwd, String mail,
			String tel, String sex, String real) {
		User user = new User();

		Configuration config = new Configuration().configure();// ��ʼ������ȡ�����ļ�
																// hibernate,cfg,xml
		SessionFactory sessionFactory = config.buildSessionFactory();// ��ȡ������ӳ���ļ�
																		// user.hbm.xml,����sessionfactory
		Session session = sessionFactory.openSession();// ��session
		Transaction tx = null;
		user = (User) session.get(User.class, id);
		// user.setUserName("hello");
		// user.setUserPwd("helloword!");
		user.setUserName(username);
		user.setUserPwd(pwd);
		user.setUserEmail(mail);
		user.setUserTel(tel);
		user.setUserSex(sex);
		user.setUserRealName(real);

		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) throws HibernateException {
		UserInfoDao userinfo = new UserInfoDao();
		List<AbstractUser> qwe = userinfo.listSearch("admin");
		System.out.println(qwe);
	}
}
