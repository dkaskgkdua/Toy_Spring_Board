package com.naver.myhome5.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.naver.myhome5.service.BoardService;
@Service
public class FileCheckTask {
	@Value("${savefoldername}")
	private String saveFolder;
	
	@Autowired
	private BoardService boardService;
	
	
	/* cron 사용법
	 * seconds(초:0~59) minutes(분:0~59) hours(시:0~23) day(일:1~31)
	 * months(달:1~12) day of week(요일:0~6) year(optional)             
	 *               초 분   시  일 달 요일  */
	@Scheduled(cron="0 43 11 * * *")
	public void checkFiles() throws Exception {
		boardService.delete_file(saveFolder);
	}
	/*
	@Scheduled(fixedDelay=100000)
	public void test() throws Exception {
		
		System.out.println("test");
	}
	*/
}
