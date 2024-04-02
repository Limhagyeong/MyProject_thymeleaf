package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.GoodsData;
import com.sist.web.entity.Goodslist;
/*
 * @Query(value = "SELECT gno,g_name,g_img,g_price FROM goodslist LIMIT :start,20",nativeQuery = true)
public List<GoodsData> goodsListData(@Param("start") int start);
 */
@Repository
public interface GoodsDAO extends JpaRepository<Goodslist, Integer>{
// 전체 리스트
@Query(value = "SELECT * "
		+ "FROM goodslist WHERE gno NOT IN (242, 243, 240) ORDER BY gno DESC LIMIT :start,12 ",nativeQuery = true)
public List<Goodslist> goodsListData(@Param("start") int start);

@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM goodslist",nativeQuery = true)
public int goodsTotalpage();

// 쿠키
public Goodslist findByGno(int gno);

@Query(value = "SELECT * "
		+ "FROM goodslist WHERE gno NOT IN (242, 243, 240) AND g_name LIKE CONCAT('%',:g_name,'%') LIMIT :start,12 ",nativeQuery = true)
public List<Goodslist> goodsFindData(@Param("start") Integer start,@Param("g_name") String g_name);

@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM goodslist WHERE g_name LIKE CONCAT('%',:g_name,'%')",nativeQuery = true)
public int goodsFindTotalpage(@Param("g_name") String g_name);

@Query(value = "SELECT DISTINCT category FROM goodslist",nativeQuery = true)
public List<GoodsData> goodsCategory();

}
