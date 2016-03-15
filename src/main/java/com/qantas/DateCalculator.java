package com.qantas;
import java.io.Console;
import java.text.MessageFormat;

public class DateCalculator {
	
	public static void main(String... args) {
		
		Console console = System.console();
		
		String start = console.readLine("Enter first date: ");
		String end = console.readLine("Enter second date: ");
		
		try {
			
			DateCalculator calculator = new DateCalculator();
			long daysBetween = calculator.calculateDaysBetween(start, end);
			
			String message = MessageFormat.format("Days between = {0}", daysBetween);
			console.readLine(message);
			System.exit(0);
			
		} catch (Exception e) {
			String message = e.getMessage();
			console.readLine(message + "\nPress any key...");
			System.exit(1);
		}
		
	}

	public long calculateDaysBetween(String start, String end) {
		
		Date startDate = new Date(start);
		Date endDate = new Date(end);
		
		return startDate.daysBetween(endDate);
	}

}
