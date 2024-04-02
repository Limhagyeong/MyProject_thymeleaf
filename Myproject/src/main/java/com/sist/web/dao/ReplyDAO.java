package com.sist.web.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Proreply;

public interface ReplyDAO extends JpaRepository<Proreply, Integer>{
	// 리뷰 댓글 목록 출력
	@Query(value="SELECT * FROM proreply WHERE gno=:gno ORDER BY no DESC LIMIT :start,4",nativeQuery = true)
	public List<Proreply> replyListData(@Param("start") int start,@Param("gno") int gno);
	
	@Query(value = "SELECT CEIL(COUNT(*)/4.0) FROM proreply WHERE gno=:gno",nativeQuery = true)
	public int replyTotalPage(@Param("gno") int gno);
	
	public Proreply findByNo(int no);

}
