package audio;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Sound{
	AudioClip clip;

	

	public Sound(String sound) {
		try {
			sound = "res/" + sound;
			clip = Applet.newAudioClip(new File(sound).toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


	public void PlaySound() {
		clip.play();
	}

	public void stopSound() {
		clip.stop();
	}
}
