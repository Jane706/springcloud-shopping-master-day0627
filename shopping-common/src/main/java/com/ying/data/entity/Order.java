package com.ying.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author：YingJiang
 * @Date:2023 07 03 18:43
 * @Description:
 */
@Data
@Entity
@Table(name = "userorder")
public class Order {
	@Id
	@Column(name = "gid")
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer gid;

	private String goodsname;
	private Integer number1;
	private Double price;
	private Date time;
	private Integer userid;
}
