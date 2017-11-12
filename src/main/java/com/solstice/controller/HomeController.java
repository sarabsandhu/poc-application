package com.solstice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Controller responsible for root request mapping
 */
@RestController
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "Welcome to Solstice POC Application";
	}
}
