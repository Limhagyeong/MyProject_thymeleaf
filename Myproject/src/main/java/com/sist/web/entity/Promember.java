package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Promember {
	@Id
	private String id;
	private String pwd;
	private String name;
}
