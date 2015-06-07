package auxiliar;

import game.sound.SoundManager;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class AssetManager {
	
	private ArrayList<String> images;
	private ArrayList<String> sounds;
	
	private static AssetManager assetmanager;
	private static String statesreaded="";
	
	
	private HashMap<String,BufferedImage> image;
	private SoundManager sound;
	private BufferedReader br;
	//private File savedgames[];
	//private File configFile;
	
	public AssetManager() {
		images= new ArrayList<String>();
		sounds= new ArrayList<String>();
		
		
		image = new HashMap<String,BufferedImage>();
		sound = SoundManager.getSingleton();
		
	}
	
	public void load(String state){
		if(!statesreaded.contains(state)){
			statesreaded+=state;
			readResources(state);
			try{
				
				for(int i=0;i<images.size();i++){
					image.put(images.get(i), ImageIO.read(AssetManager.class.getResource("/images/"+images.get(i)+".png")));
				}
				
				for(int i=0;i<sounds.size();i++){
					sound.add(sounds.get(i), AssetManager.class.getResourceAsStream("/sounds/"+sounds.get(i)+".wav"));
				}
				
			}catch(IOException e){
				System.err.println("Error cargando algún asset: "+e);
			}

			System.out.println(images.toString()+" "+sounds.toString());
		}
	}

	public static AssetManager getSingleton(){
		if(assetmanager==null){
			assetmanager = new AssetManager();
		}
		return assetmanager;
	}
	
	public SoundManager getSounds(){
		return sound;
	}
	
	public void playSound(String id){
		sound.play(id);
	}
	
	public void playSound(String id, int pos){
		sound.playfrom(id, pos);
	}
	
	public void stop(String id) {
		sound.stop(id);
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
	
	public BufferedImage[] getAllImages(){
		return image.values().toArray(new BufferedImage[image.size()]);	
	}
	
	private void readResources(String state){
		boolean actState=false,img=false,sound=false;
		try{
			br = new BufferedReader(new FileReader(new File("./res/resources")));
	        String line = br.readLine();
	        
	        
	        while (line != null) {
	        	
	        	if(line.equals("+"+state))actState=true;
	        	
	        	if(actState&&img){
	        		
	        		String file="";
	        		
	        		for(int i=0;i<line.length();i++){
	        			if(line.charAt(i)=='/'){
	        				images.add(file);
	        				file="";
	        			}else file+=line.charAt(i);
	        		}
	        		
	        		img=false;
	        	}
	        	
	        	if(actState&&sound){
	        		
	        		String file="";
	        		
	        		for(int i=0;i<line.length();i++){
	        			if(line.charAt(i)=='/'){
	        				sounds.add(file);
	        				file="";
	        			}else file+=line.charAt(i);
	        		}
	        		actState=false;sound=false;
	        		break;
	        	}

	        	if(actState&&line.equals("-img"))img=true;
	        	
	        	if(actState&&line.equals("-sound"))sound=true;
	        	
	        	line = br.readLine();
	        }
	        
			br.close();
			
		}catch(IOException e){System.err.println(e); }
	}

	
}
