package com.ying.dao;

import com.ying.data.entity.Authority;
import com.ying.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 07 03 18:45
 * @Description:
 */
public interface OrderDao extends JpaRepository<Order,Integer> {
	@Query(value = "select * from userorder where userId=?1",nativeQuery = true)
	List<Order> selectOrderByUserid(Integer userId);
}
