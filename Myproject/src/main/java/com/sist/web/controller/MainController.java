package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
@GetMapping("/")
public String main_page(Model model)
{
	
	model.addAttribute("main_html","main/home");
	return "main";
}
}
