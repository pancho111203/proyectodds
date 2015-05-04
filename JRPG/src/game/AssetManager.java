package game;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class AssetManager {
	
	//TODO cargar estos arrays desde un archivo que dependa del nivel o estado actual
	private ArrayList<String> images;
	private ArrayList<String> sounds;
	
	private static AssetManager assetmanager;
	private static String state;
	
	private HashMap<String,BufferedImage> image;
	private HashMap<String,URL> sound;
	private BufferedReader br;
	//private File savedgames[];
	//private File configFile;
	
	public AssetManager(String state) {
		images= new ArrayList<String>();
		sounds= new ArrayList<String>();
		
		this.state=state;
		
		image = new HashMap<String,BufferedImage>();
		sound = new HashMap<String,URL>();
		
		load();
	}
	
	public void load(){
		readResources();
		System.out.println(images.toString()+" "+sounds.toString());
		try{
			
			for(int i=0;i<images.size();i++){
				image.put(images.get(i), ImageIO.read(AssetManager.class.getResource("/images/"+images.get(i)+".png")));
			}
			
			for(int i=0;i<sounds.size();i++){
				sound.put(sounds.get(i), AssetManager.class.getResource("/sounds/"+sounds.get(i)+".wav"));
			}
			
		}catch(IOException e){
			System.err.println("Error cargando algún asset: "+e);
		}
	}

	public static AssetManager getSingleton(String state){
		if(!state.equals(AssetManager.state)){
			assetmanager=null;
		}
		if(assetmanager==null){
			assetmanager = new AssetManager(state);
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
	
	private void readResources(){
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
