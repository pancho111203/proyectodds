package game.sound;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

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
	
	public void add(String name,InputStream sound){
		try {
			 InputStream bufferedIn = new BufferedInputStream(sound);
		     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
		     clips.put(name,AudioSystem.getClip());	 
		     clips.get(name).open(audioInputStream);
		} catch (Exception ex) {
			System.err.println("Sound file: "+name+" couldn't be loaded.");
		     ex.printStackTrace();
		}
	}
	
	public Clip getSound(String id){
		try {
		     return clips.get(id);
		} catch (Exception ex) {
			System.err.println("Sound file couldn't be loaded.");
		     ex.printStackTrace();
		     return null;
		}
	}
	
	public void play(String id){
		if (!clips.get(id).isRunning()){
			clips.get(id).setFramePosition(0);
			clips.get(id).start();
		}
	}
	
	public void playfrom(String id,int pos){
		if (!clips.get(id).isRunning()){
			clips.get(id).setFramePosition(pos);
			clips.get(id).start();
		}
	}
	public void setVol(String id, float vol){
		FloatControl cont =(FloatControl) clips.get(id).getControl(FloatControl.Type.MASTER_GAIN);
		cont.setValue(vol);
	}
	public void stop(String id){
		clips.get(id).stop();
		clips.get(id).setFramePosition(0);
	}
	
	public void loop(String id, int times){
		clips.get(id).loop(times);
	}
	
	public int size(){
		return clips.size();
	}
}
