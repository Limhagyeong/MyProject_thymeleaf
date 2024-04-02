package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * GNO int 
G_NAME text 
G_PRICE text 
G_IMG text 
CATEGORY text 
G_REGDATE text 
BUY_COUNT int 
G_LIKE int 
G_HIT int 
CATEGORY_MINOR text
 */
@Entity
@Data
public class Goodslist {
	@Id
	private int gno;
	private int buy_count;
	private String category;
	private int g_like;
	private int g_hit;
	private String category_minor;
	private String g_name;
	private String g_price;
	private String g_img;
}
