package com.qf.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qf.domain.User;
import com.qf.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("ajaxname.do")
	@ResponseBody
	public String ajaxname(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		if(userService.findByName(username)){
				return "{'flag':true}";
			}else{
				return "{'flag':false}";
			}
	}
	
	@RequestMapping("login.do")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.findByNameAndPwd(username, password);
		if(user!=null){
		mav.addObject("deptList", user);
		request.getSession().setAttribute("user", user);
		mav.setViewName("index.jsp");
		return mav;
		}
		mav.setViewName("login.jsp");
		mav.addObject("error","√‹¬Î¥ÌŒÛ«Î÷ÿ–¬ ‰»Î");
		return mav;
	}
}
