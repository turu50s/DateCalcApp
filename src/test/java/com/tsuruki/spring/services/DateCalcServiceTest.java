package com.tsuruki.spring.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tsuruki.spring.DateCalc;
import com.tsuruki.spring.DateResult;

public class DateCalcServiceTest {

	@Test
	public void 翌日の日付計算式に基準日20181208を渡して計算結果が20181209になること() throws Exception {
		DateCalcService sut = new DateCalcService();
		DateCalc formula = new DateCalc();
		formula.setCalcYear(0);
		formula.setCalcMonth(0);
		formula.setCalcDay(1);
		
		String actual = sut.calculate("20181208", formula);
		
		assertThat(actual).isEqualTo("20181209");
	}

}
