package com.ying.dao;

import com.ying.data.entity.SUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 11:28
 * @Description:
 */
@Repository
public interface UserDao extends JpaRepository<SUser,Integer>{
	@Query(value = "select * from shop_user where uname=?1",nativeQuery = true)
	SUser findByUserName(String uname);

	@Query(value = "select * from shop_user where uname=?1 and upassword=?2",nativeQuery = true)
	SUser selectByNamePass(String uname, String password);

	@Query(value = "select * from shop_user where uaccount=?1",nativeQuery = true)
	SUser selectById(Integer id);
}
