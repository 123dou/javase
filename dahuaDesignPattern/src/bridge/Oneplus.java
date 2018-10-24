package bridge;

public class Oneplus extends PhoneFactory{

	void install() {
		System.out.println("安装一加服务");
		apk.install();
	}

}
