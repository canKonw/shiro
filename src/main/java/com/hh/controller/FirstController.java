package com.hh.controller;


import com.hh.entity.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class FirstController {
	//系统首页
	@RequestMapping("/first")
	public String first(Model model,HttpSession session)throws Exception{
		//从shiro的session中取出activeUser
		Subject subject= SecurityUtils.getSubject();
		ActiveUser activeUser=(ActiveUser)subject.getPrincipal();
       // ActiveUser activeUser=(ActiveUser)session.getAttribute("activeUser");
		//通过model传送到页面
		model.addAttribute("activeUser",activeUser);
		return "first";
	}
	
	//欢迎页面
	@RequestMapping("/welcome")
	public String welcome(Model model)throws Exception{
		return "welcome";
		
	}
}	
