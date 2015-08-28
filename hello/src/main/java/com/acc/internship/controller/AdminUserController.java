package com.acc.internship.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acc.internship.model.User;
import com.acc.internship.repo.UserDAO;
import com.acc.internship.repo.UserRoleDAO;

@Controller
public class AdminUserController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserRoleDAO userRoleDao;

	@RequestMapping(value = "/admin/adduser", method = RequestMethod.GET)
	public String newDriverGet(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("page", "newdriver");
		return "admin";

	}

	@RequestMapping(value = "/admin/adduser", method = RequestMethod.POST)
	public String newDriverPost(@Valid @ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request, Model model) {

		user.setUserRole(userRoleDao.list().get(1));
		
		if(user.getPassword().equals(user.getConfirmPassword())){
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			try {
				userDao.add(user);
			} catch (DuplicateKeyException e) {
				bindingResult.rejectValue("username", "username.duplicate", "The username exists");
			}
	
			if (!bindingResult.hasErrors()) {
				model.addAttribute("success", "User added succesfully");
				return "redirect:/admin/userview";
			}
		}else{
			bindingResult.rejectValue("confirmPassword", "confirm.fail", "The passwords does not match");
		}

		model.addAttribute("page", "newdriver");
		return "admin";
	}
	
	@RequestMapping(value = "/admin/userview", method = RequestMethod.GET)
	public String userView(Model model) {
		List<User> users = userDao.list();
		model.addAttribute("users", users);
		model.addAttribute("page", "userview");
		return "admin";

	}

	@RequestMapping(value = "/admin/userview/deleteuser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {
		userDao.delete(id);
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/admin/userview/edituser", method = RequestMethod.GET)
	public String editUserGet(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {
		User user = userDao.get(id);

		model.addAttribute("user", user);
		model.addAttribute("page", "edituser");
		return "admin";
	}

	@RequestMapping(value = "/admin/userview/edituser", method = RequestMethod.POST)
	public String commitEditUserPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model,  HttpServletRequest request) {
		if(!bindingResult.hasErrors()){
			if(user.getPassword().equals(user.getConfirmPassword())){
				user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
				userDao.update(user);
				userDao.updatepass(user);
			}else{
				bindingResult.rejectValue("confirmPassword", "confirm.fail","Password does not match");
			}
		}
		
		if(bindingResult.hasErrors()){
			model.addAttribute("page","edituser");
			return "admin";
		}
		model.addAttribute("success", "User updated");
		return "redirect:/admin/userview";
	}
}
