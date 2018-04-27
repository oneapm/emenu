/**
 * 
 */
package com.struts2.list.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hibernate.restaurant.Restaurant;
import com.hibernate.user.HibernateSessionFactory;
import com.hibernate.user.User;

/**
 * @author GE
 * 
 */
public class ListSelectDao {

	/**
	 * @param args
	 */
	public static String select(int res_num) {
		String strResName = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria cri = session.createCriteria(Restaurant.class);
			cri.add(Restrictions.eq("resNum", res_num));
			List list = cri.list();
			if (list != null && list.size() != 0) {
				Restaurant res = (Restaurant) list.get(0);
				strResName = res.getResName();
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return strResName;
	}
}
