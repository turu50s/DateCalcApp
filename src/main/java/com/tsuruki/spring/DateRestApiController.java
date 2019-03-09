package com.tsuruki.spring;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsuruki.spring.services.DateCalcService;

@RestController
@RequestMapping("/api")
public class DateRestApiController {
	
	@Autowired
	private DateCalcService service;
	
	@GetMapping(value="{base}")
	public List<DateResult> dateCalcOutput(@PathVariable("base")String baseDate) {
		List<DateCalc> list = service.search();
		List<DateResult> result = service.convert(baseDate, list);
		return result;
	}
	
}
