package chap07.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {
	// �̷������� �����ؼ� �ٸ� Ŭ�������� ����� �� �ִ�.
	// @Around("CommonPointcut.commonTarget()") ��������
	@Pointcut("execution(public * chap07..*(..))")
	public void commonTarget() {
		
	}
}
