package com.tsuruki.spring;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tsuruki.spring.services.DateCalcService;

@Controller
public class DateCalculation {
	
	@Autowired
	private DateCalcService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(@RequestParam("reference")String str, ModelAndView mav) {
		mav.setViewName("index");
		List<DateCalc> list = service.search();
		mav.addObject("data", service.convert(str, list));
		mav.addObject("value", str);
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("formModel") DateCalc datecalc, ModelAndView mav) {
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView create(@RequestParam("dateId")String dateid, @ModelAttribute("formModel") DateCalc datecalc, ModelAndView mav) {
		datecalc.setDateId(dateid);
		service.save(datecalc);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/edit/{dateid}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable String dateid, @ModelAttribute("formModel") DateCalc datecalc, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("formModel", service.find(dateid).get());
		return mav;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView update(@RequestParam("dateId")String dateid, @ModelAttribute DateCalc datecalc, ModelAndView mav) {
		datecalc.setDateId(dateid);
		service.save(datecalc);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/{dateid}", method=RequestMethod.POST)
	public ModelAndView remove(@PathVariable String dateid, ModelAndView mav) {
		service.delete(dateid);
		return new ModelAndView("redirect:/");
	}
}
