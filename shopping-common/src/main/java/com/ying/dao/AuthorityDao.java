package com.ying.dao;

import com.ying.data.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 18:04
 * @Description:
 */
public interface AuthorityDao extends JpaRepository<Authority,Integer> {
	@Query(value = "select a.* from shop_user u inner join shop_user_authority ua on u.uaccount=ua.user_id inner join shop_authority a on ua.authority_id=a.id where u.uname=?1",nativeQuery = true)
	public List<Authority> findAuthoritiesByUname(String uname);
}
