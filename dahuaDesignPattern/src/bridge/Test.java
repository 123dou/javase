package bridge;

public class Test {
    public static void main(String[] args) {
		Oneplus oneplus = new Oneplus();
		APK contact = new Contact();
		APK game = new Game();
		oneplus.setApk(contact);
		oneplus.install();
		oneplus.setApk(game);
		oneplus.install();
	}
}
