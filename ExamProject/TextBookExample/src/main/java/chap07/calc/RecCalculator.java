package chap07.calc;

public class RecCalculator implements Calculator{
	@Override
	public long factorial(long num) {
//		long start =System.currentTimeMillis();
//		try {
			return num==1? 1:factorial(num - 1)*num;
//		}finally {
//			long end =System.currentTimeMillis();
//			System.out.printf("RecCalculator.factorial(%d) 실행시간 = %d\n", num, (end-start));
//		}
	}
}
