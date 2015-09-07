package com.acc.internship.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acc.internship.model.Assignment;
import com.acc.internship.model.Message;
import com.acc.internship.model.PasswordVerify;
import com.acc.internship.model.User;
import com.acc.internship.model.UserMessage;
import com.acc.internship.repo.AssignmentDAO;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.UserDAO;

@Controller
public class DriverController {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private RouteDAO routeDao;

	@Autowired
	private AssignmentDAO assignmentDao;
	
	@Autowired
	private SimpMessagingTemplate template1;

	@RequestMapping(value = "/driver", method = RequestMethod.GET)
	public String newDriverGet(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();

		User user = userDao.findByUsername(username);
		int id = user.getId();
		PasswordVerify pass = new PasswordVerify();
		pass.setId(user.getId());
		List<Assignment> assignements = assignmentDao.listRouteForUser(id);
		
		model.addAttribute("chat", new UserMessage());
		model.addAttribute("assignements", assignements);
		model.addAttribute("userupdate", user);
		model.addAttribute("passupdateoptions", pass);

		return "driver";

	}

	@RequestMapping(value = { "/driver" }, method = RequestMethod.POST)
	public String updateDriverPost(@Valid @ModelAttribute User userupdate, BindingResult result, Model model) {

		if ((userupdate.getFirstName() == "") || (userupdate.getFirstName() == null)
				|| !((Pattern.compile("^[a-zA-Z ]*$").matcher(userupdate.getFirstName()).matches()))) {
			result.rejectValue("firstName", "firstName.fail", "Not a normal name!Try with letters!");
		} else {
			if ((userupdate.getLastName() == "") ||(userupdate.getLastName() == null)
					|| !((Pattern.compile("^[a-zA-Z ]*$").matcher(userupdate.getLastName()).matches()))) {
				result.rejectValue("lastName", "lastName.fail", "Not a normal name!Try with letters!");
			} else {
				
			}
		}

		if (!result.hasErrors()) {
			userDao.update(userupdate);
			model.addAttribute("success1", "Credentials Succesfully changed!");
			return "redirect:/driver";
		}
		{
			List<ObjectError> errors = result.getAllErrors();
			String s = null;
			for (ObjectError o : errors) {
				s = o.getDefaultMessage();
			}
			model.addAttribute("error1", s);
			return "redirect:/driver";
		}
	}

	@RequestMapping(value = { "/driver/updatepass" }, method = RequestMethod.POST)
	public String updatePassPost(@Valid @ModelAttribute PasswordVerify passupdateoptions, BindingResult result,
			Model model) {

		String old = passupdateoptions.getOldpassword();
		String nPass = passupdateoptions.getNewpassword();
		String confirm = passupdateoptions.getConfirm();

		User user = userDao.get(passupdateoptions.getId());

		if (new BCryptPasswordEncoder().matches(old, user.getPassword())) {

			if (nPass.equals(confirm)) {
				user.setPassword(new BCryptPasswordEncoder().encode(nPass));
				user.setConfirmPassword(confirm);
				userDao.updatepass(user);
			} else {
				result.rejectValue("confirm", "confirm.fail", "Password do not match");
			}
		} else {
			result.rejectValue("oldpassword", "oldpassword.fail", "Old password not correct");
		}

		if (!result.hasErrors()) {
			model.addAttribute("success", "Password succesfully change!");
			return "redirect:/driver";
		} else {
			List<ObjectError> errors = result.getAllErrors();
			String s = null;
			for (ObjectError o : errors) {
				s = o.getDefaultMessage();
			}

			model.addAttribute("error", s);

			return "redirect:/driver";
		}

	}
	
	@RequestMapping(value = { "/driver/chatform" }, method = RequestMethod.POST)
	public String UserMessagePost(@ModelAttribute("chat") UserMessage chat, BindingResult result) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();
		chat.setName(username);
		
		
		UserMessage um = new UserMessage(chat.getDate(),chat.getName(),chat.getUsermessage());
		this.template1.convertAndSend("/admin/topic/message", um);
				return "redirect:/driver";
	}
	
}
