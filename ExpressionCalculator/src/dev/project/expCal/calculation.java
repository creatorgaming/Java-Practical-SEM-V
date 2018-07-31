package dev.project.expCal;

import java.util.ArrayList;
import java.util.Scanner;

public class calculation {
	
	public String expression;
	public ArrayList<String>numbers = new ArrayList<String>();
	public ArrayList<String>oper = new ArrayList<String>();
	
	public void input() {
		System.out.print("Enter the expression : ");
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		expression = inp.next();
			
		System.out.println("Result using Simple expression calculation : " + simple_calculate());
//		System.out.print("Result using BODMAS of expression calculation : " + dmas_calculate());
	}

	public void create_lists() {
		
		String number = "", operator = "";
		
		for(char ch : expression.toCharArray()) {
//			System.out.println(ch);
			if(ch >= '0' && ch <= '9') {
				number += ch;
			}else if (ch == '-' && ( operator.equals("*") || operator.equals("/") ) ) {
				number += ch;
			}else {
				operator = "" + ch;
				numbers.add(number);
				oper.add(operator);
				number = "";
			}
		}
		if(number != "") {
			numbers.add(number);
		}
//		System.out.println("List of numbers   : " + numbers);
//		System.out.println("List of operators : " + oper);
	}
	
	public double calculate(double digit_1, double digit_2, String oper) {
		double result = 0.00;
		switch (oper) {
			case "+":
				result = digit_1 + digit_2;
				break;

			case "-":
				result = digit_1 - digit_2;
				break;

			case "*":
				result = digit_1 * digit_2;
				break;

			case "/":
				result = digit_1 / digit_2;
				break;

			case "%":
				result = digit_1 % digit_2;
				break;

			default:
				System.out.println("!!! WRONG OPERATOR !!!");
				System.exit(1);
				break;
		}
		return result;
	}
	
	public double simple_calculate() {
		create_lists();
		double result = 0.00, digit_1 = 0.00, digit_2 = 0.00;
		int i = 0;
		digit_1 = Integer.parseInt(numbers.get(0));
		for (i = 1; i  < numbers.size(); i++) {
			digit_2 = Integer.parseInt(numbers.get(i));
			digit_1 = calculate(digit_1, digit_2, oper.get(i-1));
		}
		
		if(i - 1 != oper.size()) {
			System.out.println("!!! Illegal Expression !!!");
			System.exit(1);
		}
		
		result = digit_1;
		return result;
	}
	
	public double dmas_calculate() {
		double result = 0.00;
		return result;
		
	}
}
