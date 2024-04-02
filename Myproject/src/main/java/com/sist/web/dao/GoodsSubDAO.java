package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.Goods_sub_img;

public interface GoodsSubDAO extends JpaRepository<Goods_sub_img, Integer> {
	public List<Goods_sub_img> findByGno(int gno);
}
