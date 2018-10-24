package adapter.example;

public class Test {
    public static void main(String[] args) {
		MediaPlayer autoPlayer = new AutoPlayer();
		autoPlayer.paly("vlc", "alone");
		autoPlayer.paly("mp3", "alone");
		autoPlayer.paly("mp4", "alone");
	}
}
