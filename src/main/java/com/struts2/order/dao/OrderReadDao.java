package com.struts2.order.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OrderReadDao {

	//去找菜单
	public void searchmenu(int ordernum) {
		// 初始化，读取配置文件hibernate.cfg.xml
		Configuration config = new Configuration().configure();
		// 读取创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		// 打开session
		Session session = sessionFactory.openSession();
		;
	}

	public static void main(String[] args) {
		new OrderReadDao().searchmenu(1135731);
	}
}
