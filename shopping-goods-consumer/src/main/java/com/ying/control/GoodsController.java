package com.ying.control;



import com.ying.dao.GoodsDao;
import com.ying.dao.UserDao;
import com.ying.data.entity.Goods;
import com.ying.data.entity.SUser;
import com.ying.service.impl.GoodsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 23:19
 * @Description:
 */
@Controller
public class GoodsController {
	@Resource
	GoodsDao goodsDao;
	@Resource
	UserDao userDao;

	@Resource
	GoodsServiceImpl goodsService;

	@GetMapping("/tologin")
	public String toLoginPage() {
		System.out.println("启动登录方法：toLoginPage");

		return "redirect:http://localhost:8088/userLogin?";//跳转到login/login.html登录页面
	}

	@GetMapping(value = "/goodsGetAll")
	public String getAll(Model model,@RequestParam("id") Integer id){
		System.out.println(id);
		SUser user=userDao.selectById(id);
		model.addAttribute("user",user);

		List<Goods> list=goodsDao.findAll();
		System.out.println(list);
		list.forEach(System.out::println);
		List<Goods> goods1=goodsDao.findType0Top4();
		List<Goods> goods2=goodsDao.findType1Top4();
		List<Goods> goods3=goodsDao.findType2Top4();
		List<Goods> goods4=goodsDao.findType3Top4();
		model.addAttribute("goods1",goods1);
		model.addAttribute("goods2",goods2);
		model.addAttribute("goods3",goods3);
		model.addAttribute("goods4",goods4);
		return "index";
	}
	@GetMapping(value = "/goodsDetail")
	public String getGood(Model model, @RequestParam("id")Integer id,@RequestParam("userId") Integer userId){
		SUser user;
		if (userId!=null){
			user=userDao.selectById(userId);
		}else{
			user=null;
		}
		model.addAttribute("user",user);

		Goods good=goodsDao.findGoodsById(id);
		System.out.println(good);
		model.addAttribute("detail",good);
		return "detail";
	}
	@GetMapping(value = "/selectOne")
	public String getSelect(Model model,String name,@RequestParam("id") Integer id, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

		SUser user = userDao.selectById(id);
		model.addAttribute("user", user);

		System.out.println(name);
		if (pageNum<1){
			pageNum=1;
		}
		Integer pageIndex=pageNum-1;
		Integer pageSize=4;

		Sort sort=Sort.by(Sort.Direction.ASC,"gid");

		Pageable pageable= PageRequest.of(pageIndex,pageSize,sort);

		Page<Goods> goodsPage;
		if (name==null||name.equals("null")||name.equals("")) {
			goodsPage = goodsDao.getOnegoodsNull(pageable);
		}else {
			goodsPage=goodsDao.getOnegoods(name,pageable);
		}

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
		model.addAttribute("pageInfo",goodsPage);
		model.addAttribute("goods",goodsList);


		return "selectOne";
	}

	@GetMapping(value = "/settle")
	public String settle(){
		return "Settle";
	}
	@GetMapping(value = "cartAll")
	public  String cartAll(Model model, Integer id){

		return "Settle";
	}


}
