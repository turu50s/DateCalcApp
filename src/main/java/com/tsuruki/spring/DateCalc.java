package com.tsuruki.spring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="datemaster")
public class DateCalc {
	
	@Id
	@Column(name = "dateid", length = 6)
	@NotEmpty(message="未入力です")
	private String dateId;
		
	@Column(name = "datename", length = 32)
	@NotEmpty(message="未入力です")
	private String dateName;
		
	@Column(name = "calcyear", length = 2)
	@NotNull(message="未入力です")
	private int calcYear;
		
	@Column(name = "calcmonth", length = 3)
	@NotNull(message="未入力です")
	private int calcMonth;
		
	@Column(name = "calcday", length = 4)
	@NotNull(message="未入力です")
	private int calcDay;
		
	public String getDateId() {
		return dateId;
	}
		
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
		
	public String getDateName() {
		return dateName;
	}
		
	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

	public int getCalcYear() {
		return calcYear;
	}
			
	public void setCalcYear(int calcYear) {
		this.calcYear = calcYear;
	}
		
	public int getCalcMonth() {
		return calcMonth;
	}
		
	public void setCalcMonth(int calcMonth) {
		this.calcMonth = calcMonth;
	}
		
	public int getCalcDay() {
		return calcDay;
	}
		
	public void setCalcDay(int calcDay) {
		this.calcDay = calcDay;
	}
		
}
