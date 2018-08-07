package dev.project.expCal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class calculation {
	
	public String expression;
	public ArrayList<String>numbers = new ArrayList<String>();
	public ArrayList<Character>oper = new ArrayList<Character>();
	
	public void input() {
		System.out.print("Enter the expression : ");
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		expression = inp.next();

		try {
//			System.out.println("Result using Simple expression calculation : " + simple_calculate());
			System.out.print("Result using BODMAS of expression calculation : " + bodmas_calculate());
		}catch(Exception e) {
			System.out.println("Exception " + e + " caught.");
		}
	}

	public void create_lists() {
		
		String number = "";
		char operator = 0;
		
		for(char ch : expression.toCharArray()) {
//			System.out.println(ch);
			while(ch == ' ');
			
			if (ch >= '0' && ch <= '9') {
				number += ch;
			}else if (ch == '-' && ( operator == '*' || operator == '/' ||  operator == '%' || operator == '+')) {
				number += ch;
			}else if (ch == '.' && !number.isEmpty()) {
				number += ch;
			}else {
//				if (number.endsWith(".")) {
//					System.out.println("!!! Invalid number Detected !!!");
//					System.exit(1);
//				}
				operator = ch;
				numbers.add(number);
				oper.add(operator);
				number = "";
			}
		}
		if(number != "") {
			numbers.add(number);
		}

		System.out.println("List of numbers   : " + numbers);
		System.out.println("List of operators : " + oper);
	}
	
	public double calculate(double digit_1, double digit_2, char oper) {
		double result = 0.00;
		switch (oper) {
			case '+':
				result = digit_1 + digit_2;
				break;

			case '-':
				result = digit_1 - digit_2;
				break;

			case '*':
				result = digit_1 * digit_2;
				break;

			case '/':
				result = digit_1 / digit_2;
				break;

			case '%':
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
		digit_1 = Double.parseDouble(numbers.get(0));
		for (i = 1; i  < numbers.size(); i++) {
			digit_2 = Double.parseDouble(numbers.get(i));
			digit_1 = calculate(digit_1, digit_2, oper.get(i-1));
		}
		
		if(i - 1 != oper.size()) {
			System.out.println("!!! Illegal Expression !!!");
			System.exit(1);
		}
		
		result = digit_1;
		return result;
	}
	
	public double bodmas_calculate() {
		double result = 0.00;
		String number = "";
		char[] exp = expression.toCharArray();
		LinkedList<Character> operator = new LinkedList<Character>();
		LinkedList<Double> list = new LinkedList<Double>();
// Debuging		
//		list.push(2.00);
//		list.push(3.00);
//		list.push(5.00);
//		System.out.println(list);
		
		for (int i = 0; i < exp.length; i++) {
			System.out.println(exp[i] + " <-index-> " + i + "\nNumber   List --> " + list);
			System.out.println("Operator List --> " + operator);
			while(exp[i] == ' ');
			
			if(exp[i] == '-' && number.isEmpty() && (exp[i-1] >= '0' && exp[i-1] <= '9')) {
				number += exp[i];
			}else if (exp[i] >= '0' && exp[i] <= '9') {				
				while(exp[i] >= '0' && exp[i] <= '9') {
					number += exp[i++];
				}				
				list.push(Double.parseDouble(number));
				number = "";
			}else if(exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/' || exp[i] == '%') {
				System.out.println(exp[i]);
				while(!operator.isEmpty() && hasPrecedence(exp[i], operator.peek()))
					list.push(calculate(list.pop(), list.pop(), operator.pop()));
				operator.push(exp[i]);
			}
		}
		while (!operator.isEmpty()) {
			list.push(calculate(list.pop(), list.pop(), operator.pop()));
		}
		
		if(!list.isEmpty()) {
			result = list.pop();
		}else {
			System.out.println("!!! Invalid expression !!!");
		}
		
		return result;
	}
	
	public boolean hasPrecedence(char oper_1, char oper_2) {
		if((oper_1 == '*' || oper_1 == '/') || (oper_2 == '+' || oper_2 == '-'))
			return false;	
		else 
			return true;
	}
}
