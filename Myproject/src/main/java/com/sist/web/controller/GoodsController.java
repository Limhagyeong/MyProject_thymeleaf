package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.GoodsDAO;
import com.sist.web.dao.GoodsSubDAO;
import com.sist.web.dao.ReplyDAO;
import com.sist.web.entity.GoodsData;
import com.sist.web.entity.Goods_sub_img;
import com.sist.web.entity.Goodslist;
import com.sist.web.entity.Proreply;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class GoodsController {
@Autowired
private GoodsDAO dao;

@Autowired
private GoodsSubDAO gsDao;

@Autowired
private ReplyDAO rDao;

@RequestMapping("goods/main")
public String goods_main(String page,String g_name,Model model,HttpServletRequest request)
{
	
	if(page==null)
		page="1";
	int curpage=Integer.parseInt(page);
	if(g_name==null)
	{
		
		int rowsize=20;
		int start=(curpage*rowsize)-rowsize;
		List<Goodslist> list=dao.goodsListData(start);
		int totalpage=dao.goodsTotalpage();
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
	}
	if(g_name!=null)
	{
		int rowsize=20;
		int start=(curpage*rowsize)-rowsize;
		List<Goodslist> list=dao.goodsFindData(start, g_name);
		int totalpage=dao.goodsFindTotalpage(g_name);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("g_name",g_name);
	}
	
	Cookie[] cookies=request.getCookies();
	List<Goodslist> gList=new ArrayList<Goodslist>();
	int k=0;
	if(cookies!=null)
	{
		for(int i=cookies.length-1;i>=0;i--)
		{
			
			if(cookies[i].getName().startsWith("goods_"))
			{
				if(k>2)
					break;
				String gno=cookies[i].getValue();
				Goodslist gl=dao.findByGno(Integer.parseInt(gno));
				gList.add(gl);
				k++;
			}
		}
	}
	
	List<GoodsData> cList=dao.goodsCategory();
	
	model.addAttribute("cList",cList);
	model.addAttribute("gList",gList);
	model.addAttribute("curpage",curpage);
	
	model.addAttribute("main_html","goods/main");
	return "main";
}
@GetMapping("/goods/before_detail")
public String goods_before_detail(int gno,RedirectAttributes ra,HttpServletResponse response)
{

		Cookie cookie=new Cookie("goods_"+gno, String.valueOf(gno));
												
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie); 
		ra.addAttribute("gno",gno);
	
	return "redirect:../goods/detail";
}

@GetMapping("/goods/detail")
public String goods_detail(int gno, String page,Model model) {
	// 디테일 정보
	Goodslist gs=dao.findByGno(gno);
	// 디테일 이미지
    List<Goods_sub_img> gsList=gsDao.findByGno(gno);
    // 리뷰 출력
    if(page==null)
    	page="1";
    int curpage=Integer.parseInt(page);
    int rowsize=4;
    int start=(curpage*rowsize)-rowsize;
    List<Proreply> rList=rDao.replyListData(start,gno);
    
    int totalpage=rDao.replyTotalPage(gno);
    final int BLOCK=10;
	int startpage=((curpage-1)/BLOCK*BLOCK)+1;
	int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	if(endpage>totalpage)
		endpage=totalpage;
	
	model.addAttribute("size",rList.size());
    model.addAttribute("rList",rList);
    model.addAttribute("totalpage",totalpage);
    model.addAttribute("curpage",curpage);
    model.addAttribute("startpage",startpage);
    model.addAttribute("endpage",endpage);
    model.addAttribute("gs", gs);
    model.addAttribute("gsList", gsList);
    model.addAttribute("main_html", "goods/detail");
    return "main";
}
}
