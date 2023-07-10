package com.ying.service.impl;

import com.ying.dao.GoodsDao;
import com.ying.data.entity.Goods;
import com.ying.service.GoodsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 06 30 10:05
 * @Description:
 */
@Service
/*
@Transactional(rollbackFor = Exception.class)
*/
public class GoodsServiceImpl implements GoodsService {
	@Resource
	GoodsDao goodsDao;

	@Override
	public List<Goods> queryGood() {
		return goodsDao.AllGoods();
	}

	/*public PageInfo<Goods> getOnegoods(String name, Integer pageNum, Integer pageSize) {
		System.out.println(name);
		PageHelper.startPage(pageNum,pageSize);
		if (name==null||name.equals("null")){
			System.out.println("gname is null");
			List<Goods> list=goodsDao.getOnegoodsNull();
			list.forEach(System.out::println);
			PageInfo<Goods> pageInfo=new PageInfo<>(list);
			return pageInfo;
		}else {
			System.out.println("gname is not null");
			List<Goods> list=goodsDao.getOnegoods(name);
			list.forEach(System.out::println);
			PageInfo<Goods> pageInfo=new PageInfo<>(list);
			return pageInfo;
		}
	}*/

	@Override
	public List<Goods> selectList() {
		return goodsDao.AllGoods();
	}

	@Override
	public List<Goods> selectPage(String name,Integer pageNo, Integer pageSize) {
		Integer start=(pageNo-1)*pageSize+1;
		Integer end=pageNo *pageSize+1;
		if (name.equals(null)&&name==null) {
			System.out.println("name is null");
			return goodsDao.selectPageGoodsNull(start, end);
		}else{
			return goodsDao.selectPageGoods(name, start, end);
		}
	}

	@Override
	public Integer count() {
		return goodsDao.countGoods();
	}

	/*public PageInfo<Goods> queryUserByPage(Integer pageNum, Integer pageSize){
		if(pageNum == null){//若pageNum为空
			pageNum = 1;   //设置默认当前页为1
		}
		if(pageSize == null){ //若页面大小为空
			pageSize = 8;    //设置默认每页显示的数据数
		}
		PageHelper.startPage(pageNum,pageSize);
		List<Goods> list=goodsDao.findAll();
		PageInfo<Goods> pageInfo=new PageInfo<Goods>(list);
		return pageInfo;
	}*/
}
