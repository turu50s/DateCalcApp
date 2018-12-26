package com.tsuruki.spring;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tsuruki.spring.repositories.DateCalcRepository;

@Controller
public class DateCalculation {
	
	@Autowired
	DateCalcRepository repository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(@RequestParam("reference")String str, ModelAndView mav) {
		LocalDate result;
		mav.setViewName("index");
		LocalDate dateStr = LocalDate.parse(str,DateTimeFormatter.ofPattern("uuuuMMdd"));
		List<DateCalc> listFrom = repository.findAll();
		List<DateResult> listTo = new ArrayList<DateResult>();
		for (DateCalc obj : listFrom) {
			result = dateStr.plusYears(obj.getCalcYear()).plusMonths(obj.getCalcMonth()).plusDays(obj.getCalcDay());
			listTo.add(new DateResult(
					obj.getDateId(),
					obj.getDateName(),
					obj.getCalcYear(),
					obj.getCalcMonth(),
					obj.getCalcDay(),
					result));
		}
		mav.addObject("data", listTo);
		mav.addObject("value", str);
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("formModel") DateCalc datecalc, ModelAndView mav) {
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView create(@RequestParam("dateId")String dateid, @ModelAttribute("formModel") DateCalc datecalc, ModelAndView mav) {
		datecalc.setDateId(dateid);
		repository.saveAndFlush(datecalc);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/edit/{dateid}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable String dateid, @ModelAttribute("formModel") DateCalc datecalc, ModelAndView mav) {
		mav.setViewName("edit");
		Optional<DateCalc> data = repository.findById((String)dateid);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView update(@RequestParam("dateId")String dateid, @ModelAttribute DateCalc datecalc, ModelAndView mav) {
		datecalc.setDateId(dateid);
		repository.saveAndFlush(datecalc);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/{dateid}", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView remove(@PathVariable String dateid, ModelAndView mav) {
		repository.deleteById(dateid);
		return new ModelAndView("redirect:/");
	}
}
