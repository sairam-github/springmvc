package com.ptc.sairam.Spring.SpringMvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddController {
	@RequestMapping("/add")
	public ModelAndView Add(HttpServletRequest req, HttpServletResponse resp) {
		int i = Integer.parseInt(req.getParameter("t1"));
		int j = Integer.parseInt(req.getParameter("t2"));
		
		int k = i + j;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/display.jsp");
		mv.addObject("result", k);
		
		return mv;
	}
}