package com.ying.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 22:40
 * @Description:
 */
@Data
@Entity
@Table(name = "shop_Goods")
public class Goods {
	@Id
	@Column(name = "gid")
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer gid;

	private String gname;
	private String gremain;
	private String gdetails;
	private Double gprice;
	private Integer types;
}
