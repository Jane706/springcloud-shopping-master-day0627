package com.ying.dao;

import com.ying.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 07 03 8:17
 * @Description:
 */
public interface CartDao extends JpaRepository<Cart,Integer> {
	@Query(value = "select * from shop_cart where userid=?1",nativeQuery = true)
	public List<Cart>  findCartsByID(Integer id);

	@Query(value = "delete from shop_cart where goodid=?1",nativeQuery=true)
	public void  deleteByGoodid(Integer gooid);

	@Query(value = "delete from shop_cart where userid=?1",nativeQuery=true)
	public void  deleteByUserID(Integer userId);

}
