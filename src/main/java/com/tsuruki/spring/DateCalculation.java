package com.tsuruki.spring;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tsuruki.spring.services.DateCalcService;

@Controller
public class DateCalculation {
	// 文言!!!
	@Autowired
	private DateCalcService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView dateCalcOutput(@RequestParam("base")String baseDate, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("dateValue", baseDate);
		try {			
			List<DateCalc> list = service.search();
			mav.addObject("dateResult", service.convert(baseDate, list));
		} catch (Exception e) {
			mav.addObject("msg", "入力項目に間違いがあります");
		}
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("formModel") DateCalc datecalc, ModelAndView mav) {
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView create(@RequestParam("dateId")String dateid,
						@ModelAttribute("formModel") @Validated DateCalc datecalc,
						BindingResult errors,
						ModelAndView mav) {
		
		ModelAndView response = null;
		
		if (!errors.hasErrors()) {			
			if (Objects.isNull(service.find(dateid))) {
				datecalc.setDateId(dateid);
				service.save(datecalc);				
				response =  new ModelAndView("redirect:/");
			} else {
				mav.addObject("msg", "日付IDが重複しています");
				response = mav;
			}
		} else {
			mav.setViewName("register");
			response = mav;
		}
		return response;
	}
	
	@RequestMapping(value="/edit/{dateid}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable String dateid,
							@ModelAttribute("formModel") DateCalc datecalc,
							ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("formModel", service.find(dateid));
		return mav;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ModelAndView update(@RequestParam("dateId")String dateid,
							@ModelAttribute("formModel") @Validated DateCalc datecalc,
							BindingResult errors,
							ModelAndView mav) {
		ModelAndView response = null;
		
		if (!errors.hasErrors()) {			
			datecalc.setDateId(dateid);
			service.save(datecalc);
			response =  new ModelAndView("redirect:/");
		} else {
			mav.setViewName("edit");
			response = mav;
		}
		return response;
	}
	
	@RequestMapping(value="/{dateid}", method=RequestMethod.POST)
	public ModelAndView remove(@PathVariable String dateid, ModelAndView mav) {
		service.delete(dateid);
		return new ModelAndView("redirect:/");
	}
}
