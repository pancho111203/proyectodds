package game.sound;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
	
	private HashMap<String,Clip> clips;
	
	public static SoundManager soundmanager;
	
	public SoundManager() {
		clips = new HashMap<String,Clip>();		
	}
	
	public static SoundManager getSingleton(){
		if(soundmanager==null){
			soundmanager = new SoundManager();
		}
		return soundmanager;
	}
	
	public void add(String name,URL sound){
		try {
		     URL defaultSound = sound;
		     File soundFile = new File(defaultSound.toURI());
		     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		     clips.put(name,AudioSystem.getClip());	 
		     clips.get(name).open(audioInputStream);
		} catch (Exception ex) {
			System.err.println("Sound file: "+name+" couldn't be loaded.");
		     ex.printStackTrace();
		}
	}
	
	public void play(String id){
		if (!clips.get(id).isRunning()){
			clips.get(id).setFramePosition(0);
			clips.get(id).start();
		}
	}
	
	public void stop(int id){
		clips.get(id).stop();
		clips.get(id).setFramePosition(0);
	}
	
	public void loop(int id, int times){
		clips.get(id).loop(times);
	}
	
	public int size(){
		return clips.size();
	}
}
