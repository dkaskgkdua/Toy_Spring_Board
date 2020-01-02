package com.naver.myhome5.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
@Service
@Aspect
public class AfterAdvice2 {
	@After("execution(* com.naver.myhome5..*Impl.get*(..))")
	public void afterLog(JoinPoint proceeding) {
		System.out.println("==================================");
		System.out.println("[AfterAdvice] : 비즈니스 로직 수행 후 동작");
		System.out.println("[AfterAdvice] : " +proceeding.getTarget().getClass().getName()+"의 "
				               + proceeding.getSignature().getName()+" 호출 후 입니다.");
		System.out.println("==================================");
	}
}
