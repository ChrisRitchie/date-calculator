package com.qantas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Date {

	long year;
	long month;
	long day;
	
	public Date(String date) {
		
		Pattern pattern = Pattern.compile("([0-9]{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
		Matcher matcher = pattern.matcher(date);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Date format incorrect. Please provide date in format yyyy-MM-dd");
		}
		
		String[] parts = matcher.group().split("-");
		
		this.year = new Integer(parts[0]).intValue();
		this.month = new Integer(parts[1]).intValue();
		this.day = new Integer(parts[2]).intValue();
		
	}
	
	public boolean isLeapYear() {
		
		return (year % 4 == 0) && ((year % 100 != 0) || year % 400 == 0);
				
//		boolean leapYear = false;
//		if (year % 4 == 0) {
//			if (year % 100 != 0) {
//				if (year % 400 == 0) {
//					leapYear = true;
//				}
//			}
//			leapYear = true;
//		} 
//		return false;
	}
	
	public long daysBetween(Date date) {
		
		long start = this.daysSince0000();
		long end = date.daysSince0000();
		long days = start - end;
		
		//we want to be exclusive of the dates passed in.
		//There must be a neater way of doing this
		if (start > end) {
			days -= 1;
		} else if (end > start) {
			days += 1;
		}
		
		//return ve+ value. Should not matter which date we call the function on.
		return Math.abs(days);
	}
	
	private long daysSince0000() {
		
		//number of days from 0000 assuming no leap year
		long totalDays = 365 * year;
		
		//now add additional days due to leap years (add one day for each leap year)
		totalDays += numberOfLeapYearsSince0000();
		
		//now deal with remaining months
		totalDays += numberOfDaysInMonthsSinceStartOfYear();
		
		//finally add the remaining days
		totalDays += day;
		
		return totalDays;
		
	}
	
	private long numberOfLeapYearsSince0000() {
		return (year / 4) - (year / 100) + (year / 400);
	}
	
	private long numberOfDaysInMonthsSinceStartOfYear() {
		
		long totalDays = 0;
		
		//minus 1 from the month. We dont want to include days for an incomplete month
		int tempMonth = (int) month-1;
		
		//this feels a bit crude, but here we go...
		switch (tempMonth) {
		case 12:
			//this should never happen given the way I am using this.
			totalDays += 31; //Dec
		case 11:
			totalDays += 30; //Nov
		case 10:
			totalDays += 31; //Oct
		case 9:
			totalDays += 30; //Sep
		case 8:
			totalDays += 31; //Aug
		case 7:
			totalDays += 31; //Jul
		case 6:
			totalDays += 30; //Jun
		case 5:
			totalDays += 31; //May
		case 4:
			totalDays += 30; //Apr
		case 3:
			totalDays += 31; //Mar
		case 2:
			totalDays += (isLeapYear() ? 29 : 28); //Feb
		case 1:
			totalDays += 31; //Jan
		case 0:
			break;
		default:
			throw new IllegalArgumentException("Invalid month");
		}
		
		return totalDays;
		
	}
}
