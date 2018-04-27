/**
 * 
 */
package com.struts2.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.struts2.order.dao.OrderDao;
import com.struts2.order.dao.OrderReadDao;
import com.struts2.order.dao.ResWaitDao;

/**
 * @author GE
 * 
 */
public class OrderAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int people;
	private String time;

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String execute() {
		int order_people = 0;
		order_people = Integer.valueOf(getPeople());
		String order_time = getTime();
//		System.out.println(order_people + " " + order_time);
		Cookie[] cookies;
		HttpServletRequest request = ServletActionContext.getRequest();
		cookies = request.getCookies();
		int res_num = 0, total = 0;

		ActionContext ctx = ActionContext.getContext();

		int order_num = (Integer) ctx.getSession().get("ordernum");
		String username = (String) ctx.getSession().get("user");
		
		res_num = (Integer) ctx.getSession().get("res_num");
		total = (Integer) ctx.getSession().get("total");

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("res_num")) {
				// map.put(cookie.getName(), cookie.getValue());
				String temp = (String) cookie.getValue();
				res_num = Integer.valueOf(temp);
			}
			if (cookie.getName().equals("total")) {
				String temp1 = (String) cookie.getValue();
				total = Integer.valueOf(temp1);
			}
		}
		// 订单号、总价、饭店编号、人数、用户、时间
		OrderDao oderdao = new OrderDao();
//		加入服务员名字
		ResWaitDao res = new ResWaitDao();
		String namewaiter = res.reswaiter(res_num);
		oderdao.AddOrder(order_num, total, res_num, order_people, username,
				order_time,namewaiter);
//		System.out.println(username);
		
//		OrderReadDao orderRead = new OrderReadDao();
//		List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
//		rs = orderRead.listOrder(username);
//		ctx.getSession().put("order_list", rs);

		return SUCCESS;

	}

}
