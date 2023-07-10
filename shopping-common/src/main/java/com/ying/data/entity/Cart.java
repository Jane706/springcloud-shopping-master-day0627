package com.ying.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author：YingJiang
 * @Date:2023 07 03 8:14
 * @Description:
 */
@Data
@Entity
@Table(name = "shop_cart")
public class Cart {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer id;

	private String goodsname;
	private Integer goodnumber;
	private Double price;
	private Integer goodid;
	private Integer userid;
}
