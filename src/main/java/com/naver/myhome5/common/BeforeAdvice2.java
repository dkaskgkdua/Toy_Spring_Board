package com.naver.myhome5.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect // @Aspect가 설정된 클래스에는 Pointcut과 Advice를 결합하는 설정이 있어야 한다.
public class BeforeAdvice2 {

	@Before("execution(* com.naver.myhome5..*Impl.get*(..))")
	public void beforeLog(JoinPoint proceeding) {
		System.out.println("[BeforeAdvice] : 비즈니스 로직 수행 전 동작입니다.");
		System.out.println("[BeforeAdvice] : "
				+ proceeding.getTarget().getClass().getName()
				+ "의 " + proceeding.getSignature().getName() + " 호출합니다.");
	}
}
