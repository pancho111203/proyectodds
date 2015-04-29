package game;


import java.io.File;
import java.util.HashMap;

public class AssetManager {

	//cada nuevo archivo se añade a estos arrais, lo demas va solo :D
	private final String images[]={"level","pilar_pequeño","spritesheet"};
	private final String sounds[]={"footsteps","shoot"};
	public static AssetManager assetmanager;
	
	private HashMap<String,File> image;
	private HashMap<String,File> sound;
	//private File savedgames[];
	//private File configFile;
	
	public AssetManager() {
		for(int i=0;i<images.length;i++){
			image.put(images[i], new File("/images/"+images[i]+".png"));
		}
		for(int i=0;i<sounds.length;i++){
			image.put(sounds[i], new File("/sounds/"+sounds[i]+".wav"));
		}
	}

	public static AssetManager getSingleton(){
		if(assetmanager==null){
			assetmanager = new AssetManager();
		}
		return assetmanager;
	}
	
	public File getSound(String key){
		return sound.get(key);
	}
	
	public File getImage(String key){
		return image.get(key);
	}
	
	public int getSounds(){
		return sound.size();
	}
	
	public int getImages(){
		return image.size();
	}
}
