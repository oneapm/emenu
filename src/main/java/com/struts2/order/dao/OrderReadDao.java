package com.struts2.order.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OrderReadDao {

	//ȥ�Ҳ˵�
	public void searchmenu(int ordernum) {
		// ��ʼ������ȡ�����ļ�hibernate.cfg.xml
		Configuration config = new Configuration().configure();
		// ��ȡ����sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		// ��session
		Session session = sessionFactory.openSession();
		;
	}

	public static void main(String[] args) {
		new OrderReadDao().searchmenu(1135731);
	}
}
