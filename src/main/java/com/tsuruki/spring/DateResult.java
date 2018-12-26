package com.tsuruki.spring;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateResult {
	private String id;
	private String name;
	private String result;
	private String formula;
		
	public DateResult(String id, String name, int year, int month, int day, LocalDate result) {
		this.id = id;
		this.name = name;
		this.result = result.format(DateTimeFormatter.BASIC_ISO_DATE);
		this.formula = year + "/" + month + "/" + day;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getResult() {
		return result;
	}
	
	public String getFormula() {
		return formula;
	}
}
