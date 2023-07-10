package com.ying.service;

import com.ying.data.entity.Goods;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 06 30 10:05
 * @Description:
 */
public interface GoodsService {
	List<Goods> queryGood();
/*
	public PageInfo<Goods> getOnegoods(String name, Integer pageNum, Integer pageSize);
*/
	List<Goods> selectList();

	List<Goods> selectPage(String name,Integer pageNo,Integer pageSize);

	Integer count();
}
