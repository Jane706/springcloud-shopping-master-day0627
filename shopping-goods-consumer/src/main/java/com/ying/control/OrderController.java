package com.ying.control;

import com.ying.dao.UserDao;
import com.ying.data.entity.Cart;
import com.ying.data.entity.Order;
import com.ying.dao.CartDao;
import com.ying.dao.OrderDao;
import com.ying.data.entity.SUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 07 03 18:41
 * @Description:
 */
@Controller
public class OrderController {
	@Resource
	OrderDao orderDao;

	@Resource
	CartDao cartDao;

	@Resource
	UserDao userDao;

	@GetMapping("/InsertOrder")
	public String InsertOrder(Model model, @RequestParam("userId")Integer userId){
		List<Cart> cartList=cartDao.findCartsByID(userId);
		if (cartList.size()>0){
			for (Cart cart : cartList) {
				System.out.println(cart);
				//添加订单
				Order order=new Order();
				order.setGoodsname(cart.getGoodsname());
				order.setNumber1(cart.getGoodnumber());
				order.setPrice(cart.getPrice());
				order.setTime(new Date());
				order.setUserid(userId);
				Order order1=orderDao.save(order);
				System.out.println(order1);
				//删除购物车
				cartDao.deleteById(cart.getId());
			}
		}
		//查询当前用户信息
		SUser user=userDao.selectById(userId);
		model.addAttribute("session",user);
		//根据用户ID查询所有购物车
		List<Order> orders=orderDao.selectOrderByUserid(userId);
		orders.forEach(System.out::println);
		model.addAttribute("order",orders);
		return "order";
	}
}
