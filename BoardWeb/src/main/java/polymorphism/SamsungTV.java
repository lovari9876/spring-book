package polymorphism;

public class SamsungTV implements TV {

	private Speaker speaker; // 볼륨조절 기능 위해~ 객체 참조
	private int price;

	// 언제 생성되는지 알기 위해 기본 생성자 추가
	public SamsungTV() {
		System.out.println("===> SamsungTV(1) 객체 생성");
	}
	
// === DI through setter injection===================
	public void setSpeaker(Speaker speaker) {
		System.out.println("===> setSpeaker() 호출");
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("===> setPrice() 호출");
		this.price = price;
	}


// === DI through constructor injection===================
//	// 멤버 변수 초기화 위해서 매개변수가 있는 생성자 만들기
//	// for Dependency Injection
//	public SamsungTV(Speaker speaker) {
//		System.out.println("===> SamsungTV(2) 객체 생성");
//		this.speaker = speaker;
//	}
//
//	// 다중 변수 매핑: 인자 여러 개
//	// for Dependency Injection
//	public SamsungTV(Speaker speaker, int price) {
//		System.out.println("===> SamsungTV(3) 객체 생성");
//		this.speaker = speaker;
//		this.price = price;
//	}

	public void initMethod() {
		// initial method는 멤버 변수를 초기화 = 객체 초기화
		System.out.println("객체 초기화 작업 처리...");
	}

	public void destroyMethod() {
		System.out.println("객체 삭제 전에 처리할 로직 처리...");
	}

	public void powerOn() {
		System.out.println("SamsungTV---전원 켠다. (가격: " + price + ")");
	}

	public void powerOff() {
		System.out.println("SamsungTV---전원 끈다.");
	}

	public void volumeUp() {
//		speaker = new SonySpeaker();
		speaker.volumeUp();
	}

	public void volumeDown() {
//		speaker = new SonySpeaker();
		speaker.volumeDown();
	}
}
