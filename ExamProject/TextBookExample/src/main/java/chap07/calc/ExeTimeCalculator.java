package chap07.calc;

public class ExeTimeCalculator implements Calculator {
	Calculator delegate;
	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}
	@Override
	public long factorial(long num) {
		long start =System.nanoTime();
		long res = delegate.factorial(num);
		long end =System.nanoTime();
		System.out.printf("%s.Calculator.factorial(%d) 실행시간 = %d\n", delegate.getClass().getSimpleName(), num, (end-start));
		return res;
	}
}
