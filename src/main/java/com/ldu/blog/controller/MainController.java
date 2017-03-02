package com.ldu.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ldu.blog.model.UserEntity;
import com.ldu.blog.repository.UserRepository;

@Controller
public class MainController {
	
	
	//自动装配数据库接口，不需要再写原始的Connection来操作数据库
	@Autowired
	UserRepository userRepository;
	
	
		@RequestMapping(value="/",method=RequestMethod.GET)
		public String index(){
			return "index";
		}
		
		@RequestMapping(value="/admin/users",method=RequestMethod.GET)
		public String getUsers(ModelMap modelMap){
			//查询user表中所有记录
			List<UserEntity> userList=userRepository.findAll();
			modelMap.addAttribute("userList", userList);
			return "admin/users";
		}
		
		//get请求，访问添加用户页面
		@RequestMapping(value="/admin/users/add")
		public String addUser(){
			return "admin/addUser";
		}
		
		
		//post请求，处理添加用户请求，并重定向到用户管理页面
		@RequestMapping(value="/admin/users/addP",method=RequestMethod.POST)
		
		public String addUserPost(@ModelAttribute("user")UserEntity userEntity){
			
			//post请求传递过来的是一个UserEntity的对象，里面包含了该用户的信息
			//通过ModelAttribute()注解可以获取传递过来的user,并创建这个对象，
			
			//向数据库中添加一个用户，并立即刷新缓存
			userRepository.saveAndFlush(userEntity);
			
			//重定向到用户管理页面，用redirect:url实现
			return "redirect:/admin/users";
		}
		
		//查看用户详情
		
		@RequestMapping(value="/admin/users/show/{id}",method=RequestMethod.GET)
		public String showUser(@PathVariable("id")Integer userId,ModelMap modelMap){
			
			
			//通过userid找到对应用户
			UserEntity userEntity=userRepository.findOne(userId);
			
			//传递给请求页面
			modelMap.addAttribute("user",userEntity);
			return "admin/userDetail";
		}
		
		//更新用户信息
		@RequestMapping(value="/admin/users/update/{id}",method=RequestMethod.GET)
		public String update(@PathVariable("id")Integer userId,ModelMap modelMap){
			
			
			//通过userid查找对应的用户
			UserEntity userEntity=userRepository.findOne(userId);
			
			//传递给请求页面
			modelMap.addAttribute("user",userEntity);
			
			return "admin/updateUser";
		}
		
		//更新用户信息操作
		@RequestMapping(value="/admin/users/updateP",method=RequestMethod.GET)
		public String updateUserPost(@ModelAttribute("userP")UserEntity user){
			
			
			//使用JpaRepository中方法更新用户信息
			userRepository.updateUser(user.getNickname(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getId());
			
			//刷新缓冲区
			userRepository.flush();
			return "redirect:/admin/users";
		}
		
		//删除用户
		@RequestMapping(value="/admin/users/delete/{id}",method=RequestMethod.GET)
		public String deleteUser(@PathVariable("id")Integer userId){
			
			//删除id为userId的用户
			userRepository.delete(userId);
			//刷新缓存
			userRepository.flush();
			
			return "redirect:/admin/users";
		}
		
}
