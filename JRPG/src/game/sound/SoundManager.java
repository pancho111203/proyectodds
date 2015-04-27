package game.sound;

import game.input.Keyboard;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;

public class SoundManager {
	
	//comentarios en drive
	private Clip[] clips;
	private static final String SOUNDS[]={"footsteps-4.wav","Shot.wav"};
	private static final int slength=2;
	
	public static SoundManager soundmanager;
	
	public SoundManager() {
		try {
			clips = new Clip[slength];
			for(int i=0;i<slength;i++){
		     URL defaultSound = SoundManager.class.getResource("/sounds/"+SOUNDS[i]);
		     File soundFile = new File(defaultSound.toURI());
		     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		     clips[i] = AudioSystem.getClip();	 
		     clips[i].open(audioInputStream);
			}

		} catch (Exception ex) {
		     ex.printStackTrace();
		}
	}
	
	public static SoundManager getSingleton(){
		if(soundmanager==null){
			soundmanager = new SoundManager();
		}
		return soundmanager;
	}
	
	public void play(int id){
		if (!clips[id].isRunning()){
			clips[id].setFramePosition(0);
			clips[id].start();
		}
	}
	
	public void stop(int id){
		clips[id].stop();
		clips[id].setFramePosition(0);
	}
	
	public void loop(int id, int times){
		clips[id].loop(times);
	}
}
