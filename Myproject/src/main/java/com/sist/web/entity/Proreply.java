package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;
/*
 * no int AI PK 
gno int 
id varchar(20) 
name varchar(50) 
msg text 
regdate datetime
 */
@Entity
@Data
public class Proreply {
	@Id
	private int no;
	@Column(insertable = true, updatable = false)
	private int gno;
	@Column(insertable = true, updatable = false)
	private String id;
	@Column(insertable = true, updatable = false)
	private String name;
	private String msg;
	@Column(insertable = true, updatable = false)
	private String regdate;
	@Column(insertable = true, updatable = false)
	private String subject;
	
	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
