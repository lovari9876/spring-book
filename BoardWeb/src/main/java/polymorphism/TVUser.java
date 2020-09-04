package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		// 1. Spring 컨테이너를 구동한다.
		// 	spring container 중 하나인 GenericXmlApplicationContext 안에 
		// 	factory 디자인 패턴이 이미 들어있다!
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext-polymorphism.xml");
	
		// 2. Spring 컨테이너로부터 필요한 객체를 요청(LookUp)한다.
		TV tv = (TV)factory.getBean("tv"); // Object return해서 형변환
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		// 3. Spring 컨테이너를 종료한다.
		factory.close();
	}
}	
	
// 1. 다형성 적용 전	
// 다형석 적용 이전에는 유지 보수 어려움. 높은 결합도 때문에 메서드들이 클래스에 착 결합되어 있다. 매번 다른 메서드 호출해야 함.
//	public static void main(String[] args) {
//		SamsungTV tv = new SamsungTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
//	}
	
//	public static void main(String[] args) {
//		LgTV tv = new LgTV();
//		tv.turnOn();
//		tv.soundUp();
//		tv.soundDown();
//		tv.turnOff();
//	}

	
// 2. factory 패턴 적용 전: 클라이언트가 매번 객체 생성을 해줘야한다.=> 소스 수정해줘야 한다.
//	public static void main(String[] args) {
//		TV tv = new SamsungTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
//	}

	
// 3. factory 디자인 패턴 적용: 클라이언트는 소스 수정 없이 객체 생성 요청만 보내면 된다.
//	  <= bean configuration xml file 적용 전	
//	public static void main(String[] args) {
//		BeanFactory factory = new BeanFactory();
//		TV tv = (TV)factory.getBean(args[0]); // Object return해서 형변환
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
//	}


