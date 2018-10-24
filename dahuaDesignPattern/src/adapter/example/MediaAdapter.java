package adapter.example;

public class MediaAdapter implements MediaPlayer {
    AdvancedMidiaPlayer AMP;
    public MediaAdapter(String autoType) {
    	if("vlc".equalsIgnoreCase(autoType))
    		AMP = new VlcPlayer();
    	else if("mp4".equalsIgnoreCase(autoType))
    		AMP = new Mp4Player();
    }
	@Override
	public void paly(String autoType, String fileName) {
		if("vlc".equalsIgnoreCase(autoType)) {
			AMP.playVlc(fileName);
		} else if("mp4".equalsIgnoreCase(autoType)) {
			AMP.playMp4(fileName);
		} else {
			System.out.println("don't support this type");
		}
	}

}
