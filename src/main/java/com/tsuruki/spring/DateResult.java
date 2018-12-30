package com.tsuruki.spring;

public class DateResult {
	private String id;
	private String name;
	private String result;
	private String formula;
		
	public DateResult(String id, String name, int year, int month, int day, String result) {
		this.id = id;
		this.name = name;
		this.result = result;
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
