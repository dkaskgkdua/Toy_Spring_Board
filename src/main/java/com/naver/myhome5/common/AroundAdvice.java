package com.naver.myhome5.common;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	/*  PreceedingJoinPoint 인터페이스는 JoinPoint를 상속했기 때문에 JoinPoint가 가진 모든 메서드를 지원한다.
	 *  Around Advice에서만 ProceedingJoinPoint를 매개변수로 사용하는데
	 *  이 곳에서 proceed()메서드가 필요하기 때문이다.
	 *  Around Advice인 경우 비즈니스 메서드 실행 전과 후에 실행 되는데
	 *  비즈니스 메서드를 호출하기 위해서는
	 *  ProceedingJoinPoint의 proceed()메서드가 필요하다.
	 *  즉, 클라이언트의 요청을 가로챈 어드바이스는
	 *  클라이언트가 호출한 비즈니스 메서드(ServiceImpl의 get으로 시작하는 메서드)를
	 *  호출하기 위해 ProceedingJoinPoint 객체를 매개 변수로 받아 proceed() 메서드를 통해서
	 *  비즈니스 메서드를 호출할 수 있다.
	 *  proceed() 메서드 실행 후 메소드의 반환값을 리턴해야 한다.
	 *  */
	@Around("execution(* com.naver.myhome5..*Impl.get*(..))")
	public Object aroundLog(ProceedingJoinPoint proceeding) throws Throwable {
		/* 이 코드의 이전과 이후에 공통 기능을 위한 코드를 위치 시키면 된다.
		 * 대상 객체의 메서드 BoardServiceImpl.getListCOunt([]) 를 호출한다. */
		System.out.println("=======================================");
		System.out.println("비즈니스 메서드 수행 전");
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = proceeding.proceed();
		
		sw.stop();
		System.out.println("비즈니스 메서드 수행 후입니다.");
		Signature sig = proceeding.getSignature();
		
		//Object[] getArgs() : 클라이언트가 메서드를 호출할 때 넘겨준 인자 목록을 Object 배열로 리턴합니다.
		System.out.printf("[Around Advice의 after] : %s.%s(%s) \n", 
						  proceeding.getTarget().getClass().getSimpleName(),
						  sig.getName(),
						  Arrays.toString(proceeding.getArgs()));
		
		System.out.println("[Around Advice의 after]: "
				 		  + proceeding.getSignature().getName() + "() 메소드 수행 시간 : "
				 		  + sw.getTotalTimeMillis() + "(ms)초");
		
		System.out.println("[Around Advice의 after] : "
				          + proceeding.getTarget().getClass().getName());
		
		System.out.println("proceeding.proceed() 실행 후 반환값 : " + result);
		System.out.println("=============================================");
		
		return result;
		// return null 하면 에러남
	}
}
