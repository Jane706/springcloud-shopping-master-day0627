package com.ying.dao;

import com.ying.data.entity.Goods;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 23:20
 * @Description:
 */

@Repository
public interface GoodsDao extends JpaRepository<Goods,Integer> {
	@Query(value = "select * from shop_goods where rownum<=4 and types=0 order by gid desc",nativeQuery = true)
	public List<Goods> findType0Top4();

	@Query(value = "select * from shop_goods where rownum<=4 and types=1 order by gid desc",nativeQuery = true)
	public List<Goods> findType1Top4();

	@Query(value = "select * from shop_goods where rownum<=4 and types=2 order by gid desc",nativeQuery = true)
	public List<Goods> findType2Top4();

	@Query(value = "select * from shop_goods where rownum<=4 and types=3 order by gid desc",nativeQuery = true)
	public List<Goods> findType3Top4();

	@Query(value = "select * from shop_goods where gid=?1",nativeQuery = true)
	public Goods findGoodsById(Integer id);

	@Query(value = "select * from shop_goods order by gid",nativeQuery = true)
	public List<Goods> AllGoods();

	@Query(value = "select * from shop_goods where gname like'%"+":name"+"%'",nativeQuery = true)
	public Page<Goods> getOnegoods(@Param(value = "name") String name,Pageable pageable);

	@Query(value = "select * from shop_goods",nativeQuery = true)
	public Page<Goods> getOnegoodsNull(Pageable pageable);

	@Query(value = "select * from(select row_number()over(order by gid desc)as no,g.* from shop_goods g where g.gname like('%"+":name"+"%'))temp where no between :start and :end",nativeQuery = true)
	public List<Goods> selectPageGoods(@Param(value = "name") String name,@Param(value = "start") Integer start,@Param(value = "end") Integer end);

	@Query(value = "select * from(select row_number()over(order by gid desc)as no,g.* from shop_goods g where g.gname like('%%'))temp where no between 1 and 4",nativeQuery = true)
	public List<Goods> selectPageGoodsNull(@Param(value = "start") Integer start,@Param(value = "end") Integer end);

	@Query(value = "select * from(select row_number()over(order by gid desc)as no,g.* from shop_goods g where g.gname like('%%'))temp where no between 4 and 8",nativeQuery = true)
	public List<Goods> selectPageGoodsNull2(@Param(value = "start") Integer start,@Param(value = "end") Integer end);

	@Query(value = "select * from(select row_number()over(order by gid desc)as no,g.* from shop_goods g where g.gname like('%%'))temp where no between :start and :end",nativeQuery = true)
	public List<Goods> selectPageGoodsNull3(@Param(value = "start") Integer start,@Param(value = "end") Integer end,Pageable pageable);


	@Query(value = "select count(*) from shop_goods",nativeQuery = true)
	public Integer countGoods();
}
