package com.some;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

	@RequestMapping("/info")
	public String getInfo(){
		return "MF_INFO";
	}
}
