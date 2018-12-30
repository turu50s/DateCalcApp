package com.tsuruki.spring.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsuruki.spring.DateCalc;
import com.tsuruki.spring.DateResult;
import com.tsuruki.spring.repositories.DateCalcRepository;

@Service
public class DateCalcService {
	@Autowired
	DateCalcRepository repository;
	
	public List<DateResult> convert(String baseDate, List<DateCalc> listFrom) {
		List<DateResult> listTo = new ArrayList<DateResult>();
		for (DateCalc dateCalc : listFrom) {
			String result = calculate(baseDate, dateCalc);
			listTo.add(new DateResult(
					dateCalc.getDateId(),
					dateCalc.getDateName(),
					dateCalc.getCalcYear(),
					dateCalc.getCalcMonth(),
					dateCalc.getCalcDay(),
					result));
		}
		return listTo;
	}
	
	public String calculate(String str, DateCalc dateCalc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd");
		LocalDate dateStr = LocalDate.parse(str,formatter);
		LocalDate calc = dateStr.plusYears(dateCalc.getCalcYear()).plusMonths(dateCalc.getCalcMonth()).plusDays(dateCalc.getCalcDay());
		return calc.format(formatter);
	}
	
	public List<DateCalc> search() {
		List<DateCalc> list = repository.findAll();
		return list;
	}
	
	public Optional<DateCalc> find(String dateid) {
		Optional<DateCalc> dateCalcData = repository.findById((String)dateid);
		return dateCalcData;
	}
	
	@Transactional
	public void save(DateCalc datecalc) {
		repository.saveAndFlush(datecalc);
	}
	
	@Transactional
	public void delete(String dateid) {
		repository.deleteById(dateid);
	}
}
