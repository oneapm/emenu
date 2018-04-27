package com.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.hibernate.menu.Menu;
import com.hibernate.user.*;



public class TestHibernate {
	public static void main(String[] args) throws HibernateException {
	//	new TestHibernate().addUser();
		new TestHibernate().search();
	//	new TestHibernate().get();
	//  new TestHibernate().delet();
	//	new TestHibernate().add();
	//  new TestHibernate().change();
	//	new TestHibernate().select();
//	  new TestHibernate().selectTest();    
//		System.out.println(new TestHibernate().deldetefun(food_num));;
	}

	public void deldetefun(int food_num){
//		int sign = 0;
		
		Configuration config = new Configuration().configure();
		SessionFactory  sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Menu menu = (Menu) session.get(Menu.class, food_num);
		tx = session.beginTransaction();
		try{
	
			session.delete(menu);
			tx.commit();
		}catch(Exception e){
			if (tx != null) 
				tx.rollback();
		}finally{
			session.close();
		
		}
//		return sign;
	}
	
	public int ResNum(String username) {
		int res_num = 0;
		Session session = HibernateSessionFactory.getSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", username));
		List list = cri.list();
		if (list != null && list.size() != 0) {
			User user = (User) list.get(0);
			String strUserName = user.getUserName();
			int strUserPwd = user.getUserRes();
			res_num = user.getUserRes();
		}
		return res_num;
	}

	private void selectTest() {
		long start = 0L, end = 0L, res = 0L;
		long start1 = 0L, end1= 0L, res1 = 0L;
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Criteria cri = session.createCriteria(User.class);
//		cri.add(Restrictions.eq("name", "ccc"));
//		List list = cri.list();
//		User user = (User) list.get(0);
//		System.out.println("psw:"+user.getPassword());
		List list = cri.list();
		ListIterator iterator = list.listIterator();
		List<String> list2 = new ArrayList<String>();
		//for eachѭ��
//		start = System.nanoTime();
//		
//		for(String str: list2) {
//			System.out.println(str);
//		}
//		end = System.nanoTime();
//		res = end - start;
//		System.out.println("for each time :"+res);
		
		
		while(iterator.hasNext())
		{
			User user = (User)iterator.next();
			System.out.println("name:"+user.getUserName());
			System.out.println("password:"+user.getUserPwd());
		}
		
		
	}


	private void select() {
		/* 
		1�����Hibernate��Session����
		2����Session���󴴽�Criteria����
		3��ʹ��Restriction�ľ�̬��������Criterion��ѯ����
		4����Criteria��ѯ�����Criterion��ѯ����
		5��ִ��Criterion��list()��������uniqueResult()�������ؽ����
		  */
			Session session = HibernateSessionFactory.getSession() ;
			Criteria crit = session.createCriteria(User.class);
		//	crit.add(Restrictions.eq("name","hello"));
			List list = crit.list();
			/*
			int i=0;
			while(list.get(i)!=null){
			User user = (User)list.get(i++);
			System.out.println("name:"+user.getName()+" password:"+user.getPassword());
			}*/
			//������н��
			
			/*
			 *   hasNext()�� �����жϵ�ǰԪ���Ƿ���ڣ���ָ����һ��Ԫ�أ�����ν��������
			 *   next(),  �Ƿ��ص�ǰԪ�أ� ��ָ����һ��Ԫ�ء�
			 * */
			for (ListIterator iterator = list.listIterator(); iterator.hasNext(); ) {
				User user = (User) iterator.next();
				System.out.println("name: " + user.getUserName());
				System.out.println("password: " + user.getUserPwd());
				}	
	}
	

	private void change() {
		// TODO Auto-generated method stub
		User user = new User();
	
		Configuration config = new Configuration().configure();//��ʼ������ȡ�����ļ� hibernate,cfg,xml
		SessionFactory sessionFactory = config.buildSessionFactory();//��ȡ������ӳ���ļ� user.hbm.xml,����sessionfactory
		Session session = sessionFactory.openSession();//��session
		Transaction tx = null;
		user = (User) session.get(User.class, new Integer(2));
		user.setUserName("hello");
		user.setUserPwd("helloword!");
		try{
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		}catch(Exception e){
			if(tx!=null)
				tx.rollback();
		}finally{
			session.close();
		}
	}

	private void add() {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName("zsy");
		user.setUserPwd("000");
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			// ��ʼһ������
			tx = session.beginTransaction();
			// �־û�����
			session.save(user);
			// �ύ����
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	private void get() {
		// TODO Auto-generated method stub
		User user = new User();		
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		user = (User) session.get(User.class,new Integer(1));
		System.out.println("====="+user.getUserName()+"========"+user.getUserPwd());
	
	}
	private void delet(){
	
		Configuration config = new Configuration().configure();
		SessionFactory  sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		User user = (User) session.get(User.class, new Integer(1));
		tx = session.beginTransaction();
		try{
	
			session.delete(user);
			tx.commit();
		}catch(Exception e){
			if (tx != null) 
				tx.rollback();
		}finally{
			session.close();
		
		}
	}
	private void addUser() throws HibernateException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName("zhou");
		// ��ʼ������ȡ�����ļ�hibernate.cfg.xml
		Configuration config = new Configuration().configure();
		// ��ȡ����sessionFactory
	
		SessionFactory sessionFactory = config.buildSessionFactory();
		// ��session
		Session session = sessionFactory.openSession();
		Transaction tx = null;// ��
		try {
			// ��ʼһ������
			tx = session.beginTransaction();
			// �־û�����
			session.save(user);
			// �ύ����
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	private void search() throws HibernateException{
		User user = new User();
//		user.setName("zhou");
		// ��ʼ������ȡ�����ļ�hibernate.cfg.xml
		Configuration config = new Configuration().configure();
		// ��ȡ����sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		// ��session
		Session session = sessionFactory.openSession();
		
		SQLQuery query = session.createSQLQuery("select count(id)  as cnt from user").addScalar("cnt", Hibernate.INTEGER);
		
		int c =  (Integer) query.uniqueResult();
		
		
		System.out.println("c="+c);
	}
	
	public int searchmenu(){
		int num = 0;
		List list;
		Configuration config = new Configuration().configure();
		// ��ȡ����sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		// ��session
		Session session = sessionFactory.openSession();
	
		return num;
	} 
}
