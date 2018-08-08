package dev.project.expCal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class calculation {
	
	private String expression;
	private boolean dmasFlag = false;
	private ArrayList<String>numbers = new ArrayList<String>();
	private ArrayList<Character>oper = new ArrayList<Character>();
	
	public void input() {
		System.out.print("Enter the expression : ");
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		expression = inp.nextLine();
		
		try {
			System.out.print("Result using Simple expression calculation : ");
			System.out.printf("%.4f\n", simple_calculate());
			System.out.print("Result using BODMAS of expression calculation : ");
			System.out.printf("%.4f\n", bodmas_calculate());
		}catch(Exception e) {
			System.out.println("Exception " + e + " caught.");
		}
		
	}

	public void create_lists() {
		String number = "";
		char operator = 0;
		char[] ch = expression.toCharArray();
		
		for(int i = 0; i < ch.length; i++) {
			while(ch[i++] == ' ');
			i--;
			
			if (ch[i] >= '0' && ch[i] <= '9') {
				number += ch[i];
			}else if (ch[i] == '-' && ( operator == '*' || operator == '/' ||  operator == '%' || operator == '+')) {
				number += ch[i];
			}else if (ch[i] == '.' && !number.isEmpty()) {
				number += ch[i];
			}else {
				operator = ch[i];
				numbers.add(number);
				oper.add(operator);
				number = "";
			}
		}
		if(number != "") {
			numbers.add(number);
		}
		
//		System.out.println("\n!!WITHOUT DMAS !!");
//		System.out.println("List of numbers   : " + numbers);
//		System.out.println("List of operators : " + oper);
	}
	
	public double calculate(double digit_1, double digit_2, char oper, boolean dmasFlag) {
		double result = 0.00;
		switch (oper) {
			case '+':
				result = digit_1 + digit_2;
				break;

			case '-':
				if(dmasFlag) {
					result = digit_2 - digit_1;
				}else {
					result = digit_1 - digit_2;	
				}
				break;

			case '*':
				result = digit_1 * digit_2;
				break;

			case '/':
				if(dmasFlag) {
					result = digit_2 / digit_1;
				}else {
					result = digit_1 / digit_2;	
				}
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
			digit_1 = calculate(digit_1, digit_2, oper.get(i-1),dmasFlag);
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
		dmasFlag = true;
		for (int i = 0; i < exp.length; i++) {
			while(exp[i++] == ' ');
			i--;
			 
			if(exp[i] == '-' && (operator.isEmpty() && !(exp[i-1] >= '0' && exp[i-1] <= '9' ) )) {
				number += exp[i];
			}else if ( ( exp[i] >= '0' && exp[i] <= '9' ) || exp[i] == '.') {				
				while( i < exp.length && ( ( exp[i] >= '0' && exp[i] <= '9' ) || (exp[i] == '.') ) ) {
					if ( exp[i] == '.' && number.matches("[0-9]*") ) {
						if(!number.contains(".")) {
							number += exp[i++];
						}else {
							System.out.println("!!! INVALID EXPRESSION !!!");
							System.exit(1);
						}
					}else {
						number += exp[i++];
					}
				}	
				i--;
				list.push(Double.parseDouble(number));
				number = "";
			}else if(exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/' || exp[i] == '%') {
				while(!operator.isEmpty() && hasPrecedence(exp[i], operator.peek()))
					list.push(calculate(list.pop(), list.pop(), operator.pop(),dmasFlag));
				operator.push(exp[i]);
			}
		}

//		System.out.println("\n!!WITH DMAS!!");
//		System.out.println("\nNumber   List --> " + list);
//		System.out.println("Operator List --> " + operator);
		while (!operator.isEmpty()) {
//			System.out.println("\nNumber   List --> " + list);
			list.push(calculate(list.pop(), list.pop(), operator.pop(),dmasFlag));
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
