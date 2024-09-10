package com.codeinsight.exercise.loginPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
//@RestController
@Controller
public class LoginPageApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LoginPageApplication.class, args);
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		return "signup";
	}


	@PostMapping("/signup")
	public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password,
			RedirectAttributes redirectAttributes) {
		if (userRepository.findByEmail(email) != null) {
			redirectAttributes.addFlashAttribute("error", "User with this email already exists");
			return "redirect:/signup";
		} else {
			User user = new User(name, email, password);
			userRepository.save(user);
			redirectAttributes.addFlashAttribute("saved", "User registered successfully");
			return "redirect:/login";
		}
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String loggingin(@RequestParam String email, @RequestParam String password,
			RedirectAttributes redirectAttributes) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			redirectAttributes.addFlashAttribute("error", "No user! Please signup");
			return "redirect:/signup";
		} else {
			if (user.getPassword().equals(password)) {
				redirectAttributes.addFlashAttribute("name", user.getName());
				return "redirect:/welcome";
			} else {
				redirectAttributes.addFlashAttribute("error", "Password not matching!!!");
				return "redirect:/login";
			}
		}
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

}
