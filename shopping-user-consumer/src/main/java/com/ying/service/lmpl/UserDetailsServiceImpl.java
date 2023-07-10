package com.ying.service.lmpl;

import com.ying.data.entity.Authority;
import com.ying.data.entity.SUser;
import com.ying.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 11:26
 * @Description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Resource
	UserService userService;

	public UserDetailsServiceImpl(){System.out.println("TUsersDetailsServiceImpl实例化");}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername输入的用户名："+s);
		// 通过业务方法获取用户及权限信息
		SUser myUser = userService.getMyTbsUsers(s);
		List<Authority> authorities = userService.getMyUsersAuthority(s);
		authorities.forEach(System.out::println);
		System.out.println("验证的用户："+myUser);
		// 对用户权限进行封装
		List<SimpleGrantedAuthority> list = authorities.stream().map
				(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
		// 返回封装的UserDetails用户详情类
		if(myUser!=null){
			UserDetails userDetails=
					new User(myUser.getUname(),myUser.getUpassword(),list);
			return userDetails;
		} else {
			// 如果查询的用户不存在（用户名不存在），必须抛出此异常
			throw new UsernameNotFoundException("当前用户不存在！");
		}
	}
}
