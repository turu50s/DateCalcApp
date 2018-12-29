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
	
	public List<DateResult> calculate(String str, List<DateCalc> listFrom) {
		LocalDate result;
		LocalDate dateStr = LocalDate.parse(str,DateTimeFormatter.ofPattern("uuuuMMdd"));
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
		return listTo;
	}
	
	public List<DateCalc> search() {
		List<DateCalc> list = repository.findAll();
		return list;
	}
	
	public Optional<DateCalc>find(String dateid) {
		Optional<DateCalc> data = repository.findById((String)dateid);
		return data;
	}
	
	@Transactional(readOnly=false)
	public void save(DateCalc datecalc) {
		repository.saveAndFlush(datecalc);
	}
	
	@Transactional(readOnly=false)
	public void delete(String dateid) {
		repository.deleteById(dateid);
	}
}
