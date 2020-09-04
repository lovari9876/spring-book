package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUserDuplication {
	public static void main(String[] args) {
		// 1. Spring 컨테이너를 구동한다.
		// 	spring container 중 하나인 GenericXmlApplicationContext 안에 
		// 	factory 디자인 패턴이 이미 들어있다!
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext-polymorphism.xml");
	
		// 2. Spring 컨테이너로부터 필요한 객체를 요청(LookUp)한다.
		TV tv1 = (TV)factory.getBean("tv"); // Object return해서 형변환
		TV tv2 = (TV)factory.getBean("tv");
		TV tv3 = (TV)factory.getBean("tv");
		
		// =>>> 결과: 여러번 객체 생성 요청 시에도 한번만 객체가 생성된다.
		
		// 3. Spring 컨테이너를 종료한다.
		factory.close(); 
	}
}	
