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
import com.qfedu.house.dto.UserDTO;
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
		BufferedImage image = CommonUtil.getImageFromString(str, 120, 40);
		return image;
	}
	
	@GetMapping("/to_login")
	public String toLogin(Model model){
		UserDTO userDTO=new UserDTO();
		User user=new User();
		userDTO.setUser(user);
		model.addAttribute("userDTO", userDTO);
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest req,@Valid UserDTO userDTO,Errors errors,Model model){
		HttpSession session=req.getSession();
		String codeFromServer = (String) session.getAttribute("code");
		String code=userDTO.getCode();
		String hint=null;
		String viewName="login";
		if(codeFromServer!=null &&codeFromServer.equalsIgnoreCase(code)){
		if(errors.hasErrors()){
			hint="请输入有效的登录信息";
		}else{
			String ipAddress=req.getRemoteAddr();
			userDTO.setIpAdress(ipAddress);
			if(userService.login(userDTO)){
				model.addAttribute("user",userDTO.getUser());
				viewName="redirect:index";
			}else{
				hint="输入的用户名或密码有误";
			}
		}
	}else{
		hint="请输入正确的验证码";
	}
		if(hint!=null){
		model.addAttribute("hint",hint);
		}
		return viewName;
	}
	
	@GetMapping("/to_reg")
	public String toRegister(Model model){
		User user=new User();
		model.addAttribute("user",user);
		
		return "register";
	}
	
	@PostMapping("/reg")
	public String register(HttpServletRequest req,@Valid User user,Errors errors,Model model){
		HttpSession session=req.getSession();
		String codeFromServer = (String) session.getAttribute("code");
		String code=req.getParameter("code");
		String hint=null;
		String viewname="register";
		if(codeFromServer!=null && codeFromServer.equalsIgnoreCase(code)){
			if(errors.hasErrors()){
				hint="请输入有效的信息";
				model.addAttribute("user",user);
			}else{
				if(userService.register(user)){
					viewname="redirect:to_login";
				}else{
					model.addAttribute("user",user);
					hint="请输入正确的用户名";
				}
			}
		}else{
			hint="验证码有误请重新输入";
		}
		if(hint!=null){
			model.addAttribute("hint",hint);
		}
		return viewname;
	}
	
}
