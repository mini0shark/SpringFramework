package chap07.calc;
public class RecCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		return num==1? 1:factorial(num - 1)*num;
	}

}