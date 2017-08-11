package com.qfedu.house.controller;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.house.domain.User;
import com.qfedu.house.service.UserService;
import com.qfedu.house.util.CommonUtil;

@Controller
public class UserController {

	private static final int VC_LEN=4;
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/code",produces="image/png")
	@ResponseBody
	public BufferedImage getCode(HttpSession session){
		String str = CommonUtil.generateVC(VC_LEN);
		session.setAttribute("code", str);
		BufferedImage image = CommonUtil.getImageFromString(str, 120, 30);
		return image;
	}
	
	@GetMapping("/to_login")
	public String toLogin(Model model){
		User user=new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest req,@Valid User user,Errors errors,Model model){
		if(errors.hasErrors()){
			model.addAttribute("hint", "请输入有效的登录信息");
			return "login";
		}else{
			String ipAddress=req.getRemoteAddr();
			if(userService.login(user, ipAddress)){
				return "redirect:index";
			}else{
				model.addAttribute("hint", "输入的用户名或密码有误");
				return "login";
			}
		}
		
	}
}
