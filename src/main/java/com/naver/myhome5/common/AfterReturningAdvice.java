package com.naver.myhome5.common;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterReturningAdvice {
	public void getPointcut() {}
	@AfterReturning(pointcut="execution(* com.naver.myhome5..*Impl.get*(..))",
					returning="obj")
	public void afterReturningLog(Object obj) {
		System.out.println("===============================");
		System.out.println("[AfterReturningAdvice] obj : "
							+ obj.toString());
		System.out.println("===============================");
	}
}
