package com.ying.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author：YingJiang
 * @Date:2023 06 27 10:43
 * @Description:
 */
@Data
/*@AllArgsConstructor //带所有参数构造方法
@NoArgsConstructor//不带参数默认构造方法*/
@Entity
@Table(name = "shop_user")
public class SUser {
	@Id
	@Column(name = "uaccount")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer uaccount;

	private String upassword;

	private String uname;

	private String usex;

}
