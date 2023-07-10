package com.ying.control;

import com.ying.dao.UserDao;
import com.ying.data.entity.SUser;
import com.ying.service.UserService;
import com.ying.service.lmpl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Enumeration;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 11:24
 * @Description:
 */
@Controller
public class LoginControl {
	public LoginControl() {
		System.out.println("登录控制器LoginControl实例化。");
	}
	@Resource
	UserDetailsServiceImpl userDetailsServiceImpl;
	@Resource
	UserService userService;

	@Resource
	com.ying.dao.UserDao UserDao;

	// 向用户登录页面跳转
	@GetMapping("/userLogin")
	public String toLoginPage() {
		System.out.println("启动登录方法：toLoginPage");

		return "/login";//跳转到login/login.html登录页面
	}
	// 向用户登录成功页面a.html跳转
	@GetMapping("/index.do")
	public ModelAndView toIndexPage() {
		System.out.println("启动首页：toIndexPage");

		return new ModelAndView("redirect:/pagelist");
	}
	@RequestMapping("/login")
	public String toLogin(HttpSession session,String name,String pwd,Principal principal){
		if (principal==null){
			System.out.println("null");
		}
		String username=principal.getName();
		SUser user=UserDao.findByUserName(username);
		System.out.println(username+"\t"+user);

		/*// 从当前HttpSession获取绑定到此会话的所有对象的名称
		Enumeration<String> names = session.getAttributeNames();//会话范围所有键。
		while (names.hasMoreElements()) {
			// 获取HttpSession中会话名称
			String element = names.nextElement();//取出其中的一个键。
			// 获取HttpSession中的应用上下文
			SecurityContextImpl attribute = (SecurityContextImpl) session.getAttribute(element);
			System.out.println("element-》会话范围键: " + element);
			System.out.println("attribute-》键对应值-》转换SecurityContextImpl: " + attribute);
			// 获取用户相关信息
			Authentication authentication = attribute.getAuthentication();//取出验证信息。
			//UserDetails(用户名,密码，List代表当前用户所有角色)
			UserDetails principal = (UserDetails) authentication.getPrincipal();//取出登录的用户。
			System.out.println("验证成功的用户UserDetails：" + principal);
			System.out.println("username用户名: " + principal.getUsername());
			System.out.println("username密码"+principal.getPassword());
			//<span sec:authentication="principal.authorities">-->${sessionScope.userDetails.principal.authorities}
			System.out.println("所有角色：" + principal.getAuthorities());


			*//*String remoteUser = req.getRemoteUser();
			Authentication auth = ((Authentication) req.getUserPrincipal());
			boolean admin = req.isUserInRole("admin");
			System.out.println("remoteUser = " + remoteUser);
			System.out.println("auth.getName() = " + auth.getName());
			System.out.println("admin = " + admin);*//*

			*//*SecurityContext sc = SecurityContextHolder.getContext();
			Authentication auth = sc.getAuthentication();
			Object user = auth.getPrincipal();
			System.out.println(user);*//*


		}*/
		return "redirect:http://localhost:8896/goodsGetAll?id="+user.getUaccount();//跳转a.html页面
	}

}
