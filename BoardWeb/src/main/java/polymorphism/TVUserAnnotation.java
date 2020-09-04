package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUserAnnotation {
	public static void main(String[] args) {
		// 1. Spring 컨테이너를 구동한다.
		// 	spring container 중 하나인 GenericXmlApplicationContext 안에 
		// 	factory 디자인 패턴이 이미 들어있다!
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext-annotation.xml");
	
		// 2. Spring 컨테이너로부터 필요한 객체를 요청(LookUp)한다.
		TV tv = (TV)factory.getBean("tv"); // Object return해서 형변환
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		// 3. Spring 컨테이너를 종료한다.
		factory.close();
	}

// speaker 객체가 2개 이상일 경우 발생하는 exception!!===============
// => 해당하는 객체 개수 1개를 만족하지 않아서 생긴 exception
//	UnsatisfiedDependencyException://
//	Error creating bean with name'tv':
//	Unsatisfied dependency expressed through field'speaker';
//	nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
//	No qualifying bean of type'polymorphism.Speaker'available:
//	expected single matching bean but found 2:apple,sony
}	
