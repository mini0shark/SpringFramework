package chap07.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {
	// 이런식으로 관리해서 다른 클래서에서 사용할 수 있다.
	// @Around("CommonPointcut.commonTarget()") 형식으로
	@Pointcut("execution(public * chap07..*(..))")
	public void commonTarget() {
		
	}
}
