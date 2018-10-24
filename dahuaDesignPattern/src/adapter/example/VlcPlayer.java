package adapter.example;

public class VlcPlayer implements AdvancedMidiaPlayer {

	@Override
	public void playVlc(String fileName) {
		System.out.println("playing vlc file.name " + fileName + "." + "vlc");
	}

	@Override
	public void playMp4(String fileName) {
		
	}

}
