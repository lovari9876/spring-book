package polymorphism;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("tv") // 이제 tv라는 id 요청 시 LgTV 호출!
public class LgTV implements TV {

	@Resource(name="apple") // 타입 말고, 이름으로 매칭해서 speaker 변수에 apple 객체 참조하여, 의존성 주입! 
	// @Inject도 같은 역할한다
	private Speaker speaker;
	
//	@Autowired
//	@Qualifier("apple") // 같은 타입 객체가 두 개 이상일 때, matching할 객체 이름으로 지정!
//	private Speaker speaker;
//	// 이렇게 autowired 해놓고서, SonySpeaker나 AppleSpeaker에 @Component 없으면 에러발생!!
//	// 메모리에 Speaker 타입 객체가 없으므로!
	
	public LgTV() {
		System.out.println("===> LgTV 객체 생성");
	}

	@Override
	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
}

// 다형성 적용하기 전!
//public class LgTV {
//	public void turnOn() {
//		System.out.println("LgTV---전원 켠다.");
//	}
//	public void turnOff() {
//		System.out.println("LgTV---전원 끈다.");
//	}
//	public void soundUp() {
//		System.out.println("LgTV---소리 올린다.");
//	}
//	public void soundDown() {
//		System.out.println("LgTV---소리 내린다.");
//	}
//}
