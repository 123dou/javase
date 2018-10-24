package adapter.example;

public class AutoPlayer implements MediaPlayer {
    MediaPlayer mediaPlayer;
	@Override
	public void paly(String autoType, String fileName) {
		if("mp3".equalsIgnoreCase(autoType))
			System.out.println("playing file.name " + fileName + "." + autoType);
		else if("vlc".equalsIgnoreCase(autoType) || "mp4".equalsIgnoreCase(autoType)) {
			mediaPlayer = new MediaAdapter(autoType);
			mediaPlayer.paly(autoType, fileName);
		} else 
			System.out.println("invalid media type");
	}

}
