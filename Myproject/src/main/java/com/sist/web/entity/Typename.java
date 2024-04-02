package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * GTNO int 
GNO int 
TYPE_NAME text 
TYPE_IMG text
 */
@Entity
@Data
public class Typename {
	@Id
	private int gtno;
	private int gno;
	private String type_name;
	private String type_img;
}
