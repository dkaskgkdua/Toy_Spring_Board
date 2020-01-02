package com.naver.myhome5.aop;
//공통으로 처리할 로직을 LogAdivce 클래스에 beforeLog()메서드로 구현한다.
public class LogAdvice {
	
	// LogAdvice 클래스의 공통 메서드를 aop에서 Advice라고 합니다.
	public void beforeLog() {
		System.out.println("=========>LogAdvice : 비즈니스 로직 수행 전 동작");
	}
}
