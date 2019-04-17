package com.jowl.account.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * The Status WebController class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Controller
public class WebController {

	@Value("${welcome.message_search:test}")
	private String messageSearch;

	@Value("${welcome.message_card:test}")
	private String messageCard;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message_search", this.messageSearch);
		model.put("message_card", this.messageCard);
		return "index";
	}

	@RequestMapping("/home")
	public String home(Map<String, Object> model) {
		model.put("message_search", this.messageSearch);
		model.put("message_card", this.messageCard);
		return "index";
	}
}
