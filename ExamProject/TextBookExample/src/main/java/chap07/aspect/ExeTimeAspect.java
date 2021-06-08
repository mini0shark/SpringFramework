package chap07.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ExeTimeAspect {

	@Pointcut("execution(public * chap07.calc..*(..))")
	private void publicTarget() {
	}

	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();				// �ٽɱ�� �Ϸ� �� (������)
		try {
			Object result = joinPoint.proceed();	// joinPoint(�ٽɱ��) ����
			return result;
		} finally {				// �ٽɱ�� �Ϸ� �� (������)
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();	//	ȣ��Ǵ� �޼��忡 ���� ����
			System.out.printf("%s.%s(%s) ���� �ð� : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),	// getTarget => ��� ��ü
					sig.getName(), Arrays.toString(joinPoint.getArgs()),	// getArgs => �Ķ���� ���
					(finish - start));
		}
	}

}