package com.ying.control;

import com.ying.dao.CartDao;
import com.ying.dao.UserDao;
import com.ying.data.entity.Cart;
import com.ying.data.entity.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 07 02 17:58
 * @Description:
 */
@Controller
public class CartController {
	@Autowired
	CartDao cartDao;
	@Autowired
	UserDao userDao;

	@GetMapping(value = "/goods/cart")
	public String InsertCart(@RequestParam("id") Integer id,@RequestParam("che")Integer che,@RequestParam("name")String name,@RequestParam("number") Integer number,@RequestParam("price") Double price){
		Cart cart=new Cart();
		cart.setUserid(id);
		cart.setGoodid(che);
		cart.setGoodsname(name);
		cart.setGoodnumber(number);
		cart.setPrice(price);
		System.out.println(cart);
		Cart cart1=cartDao.save(cart);
		System.out.println(cart1);

		return "redirect:/cart?id="+id;
	}

	@GetMapping(value = "/cart")
	public String Cart(Model model, @RequestParam("id") Integer id){
		SUser user=userDao.selectById(id);
		model.addAttribute("user",user);

		List<Cart> list=cartDao.findCartsByID(id);
		list.forEach(System.out::println);
		model.addAttribute("carts",list);
		return "Settle";
	}
	@GetMapping(value = "/Delete")
	public String DeleteCart(@RequestParam("id") Integer id,@RequestParam("CartId")Integer CartId){
		System.out.println(CartId);
		if (CartId!=null){
			cartDao.deleteById(CartId);
		}
		return "redirect:/cart?id="+id;
	}
	@GetMapping(value = "/DeleteAll")
	public String DeleteAll(@RequestParam("userId")Integer userId){
		cartDao.deleteByUserID(userId);
		return "order";
	}
}
