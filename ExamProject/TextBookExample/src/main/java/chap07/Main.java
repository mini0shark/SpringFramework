package chap07;

import chap07.calc.Calculator;
import chap07.calc.ExeTimeCalculator;
import chap07.calc.ImpeCalculator;
import chap07.calc.RecCalculator;

public class Main {
	public static void main(String[] args) {
		Calculator cal1 = new ImpeCalculator();
		cal1.factorial(100);
		
		Calculator cal2 = new RecCalculator();
		cal2.factorial(100);
		
		Calculator cal3 = new ImpeCalculator();
		Calculator cal4 = new ExeTimeCalculator(cal3);
		long result = cal4.factorial(4);
	}
}
