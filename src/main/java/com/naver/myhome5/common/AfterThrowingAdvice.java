package com.naver.myhome5.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Service;

/* Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성된다.
 * AfterThrowing (예외 발생했을 때 실행)
 * 타겟 메서드가 수행 중 예외를 던지게 되면 어드바이스 기능을 수행
 * */
@Service
@Aspect
public class AfterThrowingAdvice {
	
	public void getPointcut() {}
	// throwing = "exp"의 exp와 Throwable exp의 매개변수 이름을 일치시켜야 합니다.
	@AfterThrowing(pointcut="execution(* com.naver.myhome5..*Impl.get*(..))",
					throwing="exp")
	public void afterThrowingLog(Throwable exp) {
		System.out.println("====================================");
		System.out.println("[AfterThrowing] : 비즈니스 로직 수행 중 오류가 "
		                    + " 발생하면 동작합니다.");
		System.out.println("ex : " + exp.toString());
		System.out.println("====================================");
	}
}
