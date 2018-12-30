package com.tsuruki.spring.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.tsuruki.spring.DateCalc;

public class DateCalcServiceTest {

	private DateCalcService sut;
	
	@Before
	public void before() throws Exception {
		sut = new DateCalcService();
	}
	
	/**
	 *  テストは４行で書く
	 */
	
	@Test
	public void 翌日の日付計算式に基準日20181208を渡して計算結果が20181208になること() throws Exception {
		
		DateCalc formula = setUpFormula(0,0,1); 				/** セットアップ */
		
		String actual = sut.calculate("20181208", formula);		/** 実行 */
		
		assertThat(actual).isEqualTo("20181209");				/** 検証 */
		
	}
	
	@Test
	public void 翌月の日付計算式に基準日20181208を渡して計算結果が20190109になること() throws Exception {
		
		DateCalc formula = setUpFormula(0,1,0);
		
		String actual = sut.calculate("20181208", formula);
		
		assertThat(actual).isEqualTo("20190108");
		
	}
	
	@Test
	public void 翌年の日付計算式に基準日20181208を渡して計算結果が20191208になること() throws Exception {
		
		DateCalc formula = setUpFormula(1,0,0);
		
		String actual = sut.calculate("20181208", formula);
		
		assertThat(actual).isEqualTo("20191208");
	}
	
	@Test
	public void 前日の日付計算式に基準日20181208を渡して計算結果が20191207になること() throws Exception {
		
		DateCalc formula = setUpFormula(0,0,-1);
		
		String actual = sut.calculate("20181208", formula);
		
		assertThat(actual).isEqualTo("20181207");
	}
	
	/**
	 * 日付計算式の初期設定
	 * 
	 * @return 設定された日付計算式
	 */
	private DateCalc setUpFormula(int year, int month, int day) {
		DateCalc formula = new DateCalc();
		formula.setCalcYear(year);
		formula.setCalcMonth(month);
		formula.setCalcDay(day);
		return formula;
	}
}
