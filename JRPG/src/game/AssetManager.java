package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class AssetManager {
	
	//TODO cargar estos arrays desde un archivo que dependa del nivel o estado actual
	private final String images[]={"level","pilar_pequeño","spritesheet","selectorp","bg","botonsito"};
	private final String sounds[]={"footsteps","shoot"};
	
	private static AssetManager assetmanager;
	
	private HashMap<String,BufferedImage> image;
	private HashMap<String,URL> sound;
	//private File savedgames[];
	//private File configFile;
	
	public AssetManager() {
		image = new HashMap<String,BufferedImage>();
		sound = new HashMap<String,URL>();
		
		load();
	}
	
	public void load(){
		try{
			
			for(int i=0;i<images.length;i++){
				System.out.println(images[i]+" "+ImageIO.read(AssetManager.class.getResource("/images/"+images[i]+".png")).toString());
				image.put(images[i], ImageIO.read(AssetManager.class.getResource("/images/"+images[i]+".png")));
			}
			
			for(int i=0;i<sounds.length;i++){
				sound.put(sounds[i], AssetManager.class.getResource("/sounds/"+sounds[i]+".wav"));
			}
			
		}catch(IOException e){
			System.err.println("Error cargando algún asset: "+e);
		}
	}

	public static AssetManager getSingleton(){
		if(assetmanager==null){
			assetmanager = new AssetManager();
		}
		return assetmanager;
	}
	
	public URL getSound(String key){
		return sound.get(key);
	}
	
	public BufferedImage getImage(String key){
		return image.get(key);
	}
	
	public int getSoundsSize(){
		return sound.size();
	}
	
	public int getImagesSize(){
		return image.size();
	}
	
	public URL[] getAllSounds(){
		return sound.values().toArray(new URL[sound.size()]);	
	}
	
	public BufferedImage[] getAllImages(){
		return image.values().toArray(new BufferedImage[image.size()]);	
	}
	
}
