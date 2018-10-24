package bridge;

public abstract class PhoneFactory {
    protected APK apk;

	public APK getApk() {
		return apk;
	}

	public void setApk(APK apk) {
		this.apk = apk;
	}
    abstract void install();
}
