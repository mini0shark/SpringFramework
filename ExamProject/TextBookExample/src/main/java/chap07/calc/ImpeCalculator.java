package chap07.calc;

public class ImpeCalculator implements Calculator{
	@Override
	public long factorial(long num) {
//		long start =System.currentTimeMillis();
		long result = 1;
		for(long i=1; i<=num; i++) {
			result*=i;
		}
//		long end =System.currentTimeMillis();
//		System.out.printf("ImpeCalculator.factorial(%d) ����ð� = %d\n", num, (end-start));
		return result;
	}
}
