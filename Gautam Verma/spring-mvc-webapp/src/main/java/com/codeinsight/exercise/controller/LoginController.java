package com.codeinsight.exercise.controller;
import com.codeinsight.exercise.services.*;
import com.codeinsight.exercise.userform.*;
import com.codeinsight.exercise.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String register(@ModelAttribute @Valid UserForm userForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			StringBuilder errors = new StringBuilder();
			result.getAllErrors().forEach(error -> {
				String message = error.getDefaultMessage();
				if (message != null) {
					errors.append(message).append(" ");
				}
			});

			redirectAttributes.addFlashAttribute("error", errors);
			return "redirect:/signup";
		}

		if (userService.findUserByEmail(userForm.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("error", "User with this email already exists");
			return "redirect:/signup";
		} else {
			userService.registerUser(userForm);
			redirectAttributes.addFlashAttribute("saved", "User registered successfully");
			return "redirect:/login";
		}
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "login";
	}

	@PostMapping("/login")
	public String loggingin(@ModelAttribute UserForm userForm, RedirectAttributes redirectAttributes,
			HttpSession httpSession) {
		User user = userService.findUserByEmail(userForm.getEmail());
		if (user == null) {
			redirectAttributes.addFlashAttribute("error", "No user! Please signup");
			return "redirect:/signup";
		} else {
			if (user.getPassword().equals(userForm.getPassword())) {
				httpSession.setAttribute("name", user.getName());
				return "redirect:/welcome";
			} else {
				redirectAttributes.addFlashAttribute("error", "Password not matching!!!");
				return "redirect:/login";
			}
		}
	}

	@GetMapping("/welcome")
	public String welcome(HttpSession httpSession, Model model) {
		String name = (String) httpSession.getAttribute("name");
		model.addAttribute("name", name);
		return "welcome";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest httpRequest, Model model, RedirectAttributes redirectAttributes) {
		HttpSession httpSession = httpRequest.getSession(false);
		if (httpSession != null) {
			httpSession.invalidate();
		}
		redirectAttributes.addFlashAttribute("saved","You have been successfully logged out");
		return "redirect:/login";
	}
}
