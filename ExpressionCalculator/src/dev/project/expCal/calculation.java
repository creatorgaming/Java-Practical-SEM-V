package dev.project.expCal;

import java.util.Scanner;

public class calculation {
	
	public String expression;
	
	public void input() {
		System.out.print("Enter the expression : ");
		Scanner inp = new Scanner(System.in);
		expression = inp.next();
		
		System.out.print("Result using Simple expression calculation : ");
		System.out.println(simple_calculate());
		System.out.print("Result using rules of expression calculation : ");
		dmas_calculate();
	}
	
	public int simple_calculate() {
		boolean check_digit_1 = false, check_digit_2 = true;
		int digit_1 = Integer.MIN_VALUE, digit_2 = Integer.MIN_VALUE;
		char operator = ' ';
		String digit_1_s = "" , digit_2_s = "";
		int result = 0;
		
		for(char ch : expression.toCharArray()) {
			if((int)ch >= 48 && (int)ch <= 57) {
				if(!check_digit_1 && check_digit_2) {
					digit_1 = Integer.parseInt(digit_1_s);
					digit_2_s = digit_2_s + "" + ch;
				}else if(check_digit_2 && !check_digit_2){
						digit_2 = Integer.parseInt(digit_2_s);
						digit_1_s = digit_1_s + "" + ch;
				}
			}else if(digit_1 != Integer.MIN_VALUE && digit_2 != Integer.MIN_VALUE){
				
				if(operator == '+') {
					result = digit_1 + digit_2;
				}else if(operator == '-') {
					result = digit_1 - digit_2;
				}else if(operator == '/') {
					result = digit_1 / digit_2;
				}else if(operator == '*') {
					result = digit_1 * digit_2;
				}
				
				digit_1 = result;
				digit_2 = Integer.MIN_VALUE;
			}else {
				operator = ch;
			}
		}
		
		return result;
	}
	
	public void dmas_calculate() {}
}
