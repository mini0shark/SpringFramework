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
		long start = System.nanoTime();				// 핵심기능 완료 후 (실행전)
		try {
			Object result = joinPoint.proceed();	// joinPoint(핵심기능) 수행
			return result;
		} finally {				// 핵심기능 완료 후 (무조건)
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();	//	호출되는 메서드에 대한 정보
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),	// getTarget => 대상 객체
					sig.getName(), Arrays.toString(joinPoint.getArgs()),	// getArgs => 파라미터 목록
					(finish - start));
		}
	}

}