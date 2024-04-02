package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.ReplyDAO;
import com.sist.web.entity.Proreply;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReplyController {
@Autowired
private ReplyDAO rDao;

@PostMapping("/reply/insert")
public String replyInsertOk(Proreply vo,HttpSession session,RedirectAttributes ra)
{
	String id=(String)session.getAttribute("id");
	String name=(String)session.getAttribute("name");
	vo.setId(id);
	vo.setName(name);
	rDao.save(vo);
	ra.addAttribute("gno", vo.getGno());
	return "redirect:../goods/detail";
}
@GetMapping("/reply/delete")
public String reply_delete(int gno,int no,RedirectAttributes ra)
{
	Proreply vo=rDao.findByNo(no);
	rDao.delete(vo); 
	ra.addAttribute("gno",gno);
	return "redirect:/goods/detail";
}
@PostMapping("/reply/update")
public String replyUpdate(Proreply vo,RedirectAttributes ra)
{
	rDao.save(vo);
	ra.addAttribute("gno",vo.getGno());
	return "redirect:/goods/detail";
}
}
