package com.ying;


import org.springframework.data.domain.Page;
import com.ying.dao.GoodsDao;
import com.ying.data.entity.Goods;
import com.ying.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
class ShoppingCommonApplicationTests {
	@Resource
	GoodsService goodsService;

	@Resource
	GoodsDao goodsDao;

	@Test
	void contextLoads() {
		Integer pageIndex=0;
		Integer pageSize=4;

		Sort sort=Sort.by(Sort.Direction.ASC,"gid");

		Pageable pageable= PageRequest.of(pageIndex,pageSize,sort);

		Page<Goods> goodsPage=goodsDao.getOnegoodsNull(pageable);

		List<Goods> goodsList=goodsPage.getContent();
		for (Goods goods : goodsList) {
			System.out.println(goods.toString());
		}
		//分页信息
		System.out.println("当前页码：第" + (goodsPage.getNumber() + 1) + "页");
		System.out.println("分页大小：每页" + goodsPage.getSize() + "条");
		System.out.println("数据总数：共" + goodsPage.getTotalElements() + "条");
		System.out.println("总页数：共" + goodsPage.getTotalPages() + "页");
		System.out.println("排序信息：" + goodsPage.getSort().toString());

	}

}
