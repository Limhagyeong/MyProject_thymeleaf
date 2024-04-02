package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Goods_sub_img {
	@Id
	private int gsno;
	private int gno;
	private String g_subimg;
}
