package com.qantas.test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.qantas.DateCalculator;

public class DateCalculatorTests {

	private DateCalculator dateCalculator;

	@Before
	public void setup() {
		dateCalculator = new DateCalculator();
	}

	
	/**
	 * Verify sample data
	 * 1. 1983-06-02 – 1983-06-22: 19 days 
	 * 2. 1984-07-04 – 1984-12-25: 173 days
	 * 3. 1989-01-03 – 1983-08-03: 1979 days
	 */
	@Test
	public void testValidDates() {
		
		List<Map<String, String>> sampleDate = generateData();

		sampleDate.forEach(data -> {
			
			//given
			String start = data.get("start");
			String end = data.get("end");
			Long expectedDays = new Long(data.get("result"));
			
			//when
			long days = dateCalculator.calculateDaysBetween(start, end);
			
			//then
			assertThat(days, is(equalTo(expectedDays)));
			
		});

	}
	
	/**
	 * 1972-11-07 to 1972-11-08
	 */
	@Test
	public void testDatesBetweenMinAndMaxDatesAllowed() {
		
		//given
		String start = "1901-01-01";
		String end = "2999-12-31";
		
		//when
		long days = dateCalculator.calculateDaysBetween(start, end);
		
		//then
		assertThat(days, is(equalTo(401400L)));
		
	}
	
	/**
	 * 1972-11-07 to 1972-11-07
	 */
	@Test
	public void testSameDayEntered() {
			
		//given
		String start = "1972-11-07";
		String end = "1972-11-07";
		
		//when
		long days = dateCalculator.calculateDaysBetween(start, end);
		
		//then
		assertThat(days, is(equalTo(0L)));
		
	}
	
	/**
	 * 1972-11-07 to 1972-11-08
	 */
	@Test
	public void testNoDayBetweenDates() {
			
		//given
		String start = "1972-11-07";
		String end = "1972-11-08";
		
		//when
		long days = dateCalculator.calculateDaysBetween(start, end);
		
		//then
		assertThat(days, is(equalTo(0L)));
		
	}
	
	/**
	 * 1972-11-07 to 1972-11-09
	 */
	@Test
	public void testOneDayBetweenDates() {
		
		//given
		String start = "1972-11-07";
		String end = "1972-11-09";
		
		//when
		long days = dateCalculator.calculateDaysBetween(start, end);
		
		//then
		assertThat(days, is(equalTo(1L)));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDateFormat() {
		
		//given
		String start = "72-11-07";
		String end = "1972-11-09";
		
		//when
		long days = dateCalculator.calculateDaysBetween(start, end);
		
		//then
		assertThat(days, is(equalTo(1L)));
		
	}
	
	private List<Map<String, String>> generateData() {
		
		/**
		 * 1. 1983-06-02 – 1983-06-22: 19 days 
		 * 2. 1984-07-04 – 1984-12-25: 173 days
		 * 3. 1989-01-03 – 1983-08-03: 1979 days
		 */
		List<Map<String, String>> data = new ArrayList<>();
		
		Map<String, String> row = new HashMap<>();
		row.put("start", "1983-06-02");
		row.put("end", "1983-06-22");
		row.put("result", "19");
		data.add(row);
		
		row = new HashMap<>();
		row.put("start", "1984-07-04");
		row.put("end", "1984-12-25");
		row.put("result", "173");
		data.add(row);
		
		row = new HashMap<>();
		row.put("start", "1989-01-03");
		row.put("end", "1983-08-03");
		row.put("result", "1979");
		data.add(row);
		
		return data;
	}

}
