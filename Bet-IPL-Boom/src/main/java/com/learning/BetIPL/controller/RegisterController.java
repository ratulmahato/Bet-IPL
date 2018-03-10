package com.learning.BetIPL.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learning.BetIPL.dao.model.User;
import com.learning.BetIPL.framework.common.StringUtil;
import com.learning.BetIPL.framework.mail.MailMessage;
import com.learning.BetIPL.framework.mail.MailUtil;
import com.learning.BetIPL.service.EmailService;
import com.learning.BetIPL.service.LoginService;
import com.learning.BetIPL.service.UserService;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.nulabinc.zxcvbn.Strength;

@Controller
public class RegisterController {

	// private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	// @Autowired
	// public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder,
	// UserService userService, EmailService emailService) {
	//
	// this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	// this.userService = userService;
	// this.emailService = emailService;
	// }

	// Return registration form template
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView) {
		User user = new User();
		modelAndView.addObject("userForm", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(@Valid @ModelAttribute("userForm") User user,
			BindingResult bindingResult, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("userForm", user);
		// Lookup user in database by e-mail
		User userExists = userService.findByEmail(user.getEmail());

		System.out.println(userExists);

		if (userExists != null) {
			modelAndView.addObject("errormessage",
					"Oops!  There is already a user registered with the email provided.");
			// bindingResult.reject("email");
			return modelAndView;
		}

		if (bindingResult.hasErrors()) {
		} else { // new user so we create user and send confirmation e-mail

			// Disable user until they click on confirmation link in email
			user.setEnabled(false);

			// Generate random 36-character string token for confirmation link
			user.setConfirmationToken(UUID.randomUUID().toString());

			userService.createUser(user);
			String ab = "http://localhost:8586/register";
			StringBuffer requestURL = request.getRequestURL();

			String url = requestURL.toString();

			String[] urlArray = url.split("://");
			String protocol = urlArray[0];

			String t[] = urlArray[1].split("/");
			String hostWithPort = t[0];

			String appUrl = protocol + "://" + hostWithPort;

			MailMessage ms = new MailMessage();
			ms.setToAddress(user.getEmail());
			ms.setBody("To confirm your e-mail address, please click the link below:\n" + "<a href=" + appUrl
					+ "/confirm?token=" + user.getConfirmationToken() + "> Click Here </a>");
			ms.setSubject("Test");
			MailUtil.INSTANCE.sendMail(ms);

			//
			// SimpleMailMessage registrationEmail = new SimpleMailMessage();
			// registrationEmail.setTo(user.getEmail());
			// registrationEmail.setSubject("Registration Confirmation");
			// registrationEmail.setText("To confirm your e-mail address, please
			// click the link below:\n" + appUrl
			// + "/confirm?token=" + user.getConfirmationToken());
			// registrationEmail.setFrom("noreply@domain.com");
			//
			// emailService.sendEmail(registrationEmail);

			modelAndView.addObject("msg-success", "A confirmation e-mail has been sent to " + user.getEmail());
		}

		return modelAndView;
	}

	// Process confirmation link
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

		User user = userService.findByConfirmationToken(token);

		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}

		modelAndView.setViewName("confirm");
		return modelAndView;
	}

	// Process confirmation link
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult,
			@SuppressWarnings("rawtypes") @RequestParam Map requestParams, RedirectAttributes redir) {

		modelAndView.setViewName("confirm");

		Zxcvbn passwordCheck = new Zxcvbn();

		// Strength strength = passwordCheck.measure((String)
		// requestParams.get("password"));
		String pass = (String) requestParams.get("password");
		String confirmPass = (String) requestParams.get("ConfirmPassword");
		boolean isEquals = StringUtil.equals(pass, confirmPass);
		if (!isEquals) {
			modelAndView.addObject("errormessage", "confirm password doesn't matches with password.");
			modelAndView.setViewName("confirm");
			modelAndView.addObject("token", requestParams.get("token"));
			return modelAndView;
		}

		User user = userService.findByConfirmationToken((String) requestParams.get("token"));
		if(null==user){
			modelAndView.addObject("errormessage", "No user found. Try again");
			modelAndView.setViewName("confirm");
			modelAndView.addObject("token", requestParams.get("token"));
			return modelAndView;
		}
		
		user.setPassword((String) requestParams.get("password"));
		user.setConfirmationToken(null);
		user.setEnabled(true);
		userService.updateUser(user);

		modelAndView.addObject("successmessage", "Your password has been set!");
		modelAndView.addObject("token", requestParams.get("token"));
		return modelAndView;

	}

	// Find the user associated with the reset token

}
