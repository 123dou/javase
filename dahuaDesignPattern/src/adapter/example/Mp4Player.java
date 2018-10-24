package adapter.example;

public class Mp4Player implements AdvancedMidiaPlayer {

	@Override
	public void playVlc(String fileName) {

	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("playing mp4 file.name " + fileName + "." + "mp4");	}

}
