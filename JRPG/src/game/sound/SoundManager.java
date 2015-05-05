package game.sound;


import game.AssetManager;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
	
	//falta hacer que se pueda pedir sonidos por el nombre a traves del AM(no le pongo ToDo por que no hay prisa) 
	private Clip[] clips;
	private AssetManager AM;
	private static String state;
	public static SoundManager soundmanager;
	
	public SoundManager(String state) {
		this.state=state;
		AM = AssetManager.getSingleton(this.state);
		try {
			clips = new Clip[AM.getSoundsSize()];
			for(int i=0;i<AM.getSoundsSize();i++){
		     URL defaultSound = AM.getAllSounds()[i];
		     File soundFile = new File(defaultSound.toURI());
		     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		     clips[i] = AudioSystem.getClip();	 
		     clips[i].open(audioInputStream);
			}

		} catch (Exception ex) {
		     ex.printStackTrace();
		}
	}
	
	public static SoundManager getSingleton(String state){
		if(soundmanager==null||SoundManager.state!=state){
			soundmanager = new SoundManager(state);
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
