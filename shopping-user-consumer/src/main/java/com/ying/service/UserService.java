package com.ying.service;

import com.ying.dao.AuthorityDao;
import com.ying.dao.UserDao;
import com.ying.data.entity.Authority;
import com.ying.data.entity.SUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 11:21
 * @Description:
 */

@Service
public class UserService{
	@Resource
	UserDao UserDao;
	@Resource
	AuthorityDao authorityDao;
	@Resource
	RedisTemplate redisTemplate;

	public UserService(){
		System.out.println("UserDetailsServiceImpl实例化");
	}
	// 业务控制：使用唯一用户名查询用户信息
	public SUser getMyTbsUsers(String userName){
		SUser myUser=null;
		System.out.println("TblUsersService启动getMyTbsUsers的用户名："+userName);
		/*Object o = redisTemplate.opsForValue().get("mytbsuser_"+userName);
		System.out.println("redisTemplat获取缓存的对象："+o);*/
		Object o=null;
		if(o!=null){
			myUser=(SUser)o;
		}else {
			/*myUser = tUsersDao.findByUserName(userName);*/
			myUser=UserDao.findByUserName(userName);
			System.out.println("TblUsersService启动usersDAO.findByUserName-》"+myUser);
			if(myUser!=null){
				redisTemplate.opsForValue().set("mytbsuser_"+userName,myUser);
			}
		}
		return myUser;
	}
	// 业务控制：使用唯一用户名查询用户权限
	public List<Authority> getMyUsersAuthority(String username){
		List<Authority> authorities=null;
/*
		Object o = redisTemplate.opsForValue().get("tbsroleauthorities_"+username);
*/
		Object o=null;
		if(o!=null){
			authorities=(List<Authority>)o;
		}else {
			authorities=authorityDao.findAuthoritiesByUname(username);
			if(authorities.size()>0){
				redisTemplate.opsForValue().set("tbsroleauthorities_"+username,authorities);
				System.out.println(username+"\t"+authorities);
			}
		}
		return authorities;
	}
	public SUser selectByNamePass(String userName, String password){
		System.out.println(userName+"\t"+password);
		return UserDao.selectByNamePass(userName,password);
	}
}
