package com.ying.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 17:58
 * @Description:
 */
@Data
@Entity
@Table(name = "shop_authority")
public class Authority {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer id;

	private String authority;
}
