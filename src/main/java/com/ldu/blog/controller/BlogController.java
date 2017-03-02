package com.ldu.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ldu.blog.model.BlogEntity;
import com.ldu.blog.model.UserEntity;
import com.ldu.blog.repository.BlogRepository;
import com.ldu.blog.repository.UserRepository;


@Controller
public class BlogController {
	
	@Autowired
	BlogRepository blogRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	//查看所有博文
	@RequestMapping(value="/admin/blogs",method=RequestMethod.GET)
	public String showBlogs(ModelMap modelMap){
		
		//查找所有博文
		List<BlogEntity> blogList=blogRepository.findAll();
		
		//将博客集合传递到页面
		modelMap.addAttribute("blogList", blogList);
		return "admin/blogs";
	}
	
	
	//添加博文
	@RequestMapping(value="/admin/blogs/add",method=RequestMethod.GET)
	public String addBlog(ModelMap modelMap){
		
		//查找所有用户
		List<UserEntity> userList=userRepository.findAll();
		
		//向jsp页面注入用户列表
		modelMap.addAttribute("userList",userList);
		return "admin/addBlog";
	}
	
	//添加博文操作,重定向到查看博客页面
	@RequestMapping(value="/webapp/admin/blogs/addP",method=RequestMethod.POST)
	public String addBlogsPost(@ModelAttribute("blog")BlogEntity blogEntity){
		
		System.out.println(blogEntity.getId()+blogEntity.getTitle());
		//将博文存到数据库并刷新缓存
		blogRepository.saveAndFlush(blogEntity);
		return "redirect:/admin/blogs";
	}
	
}
