package utils;

import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Cette classe permet de parser un masque de collision donné en paramètre 
 * et de récupérer celles-ci ainsi que les spawns
 */
public class CalculateMatrix {
	ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();
	ArrayList<Rectangle> spawns = new ArrayList<Rectangle>();
	int colorCollisions = (255<<24)+(255<<16)+(0<<8)+148; //Valeur par défaut
	int colorSpawn = (255<<24)+(255<<16)+(50<<8)+0; //Valeur par défaut
	int white = (255<<24)+(255<<16)+(255<<8)+255;
	int[] rgbs;
	
	public CalculateMatrix(int colorCollisions, int colorSpawn){
		this.colorCollisions = colorCollisions;
		this.colorSpawn = colorSpawn;
	}
	
	public CalculateMatrix(){
		
	}
	
	public void calculateMatrix(String imgPath){

		BufferedImage in;
		try {
			in = ImageIO.read(new File(imgPath));
			int imgW = in.getWidth();
			int imgH = in.getHeight();
			rgbs = new int[imgW*imgH];
			in.getRGB(0,0,imgW,imgH,rgbs,0,imgW);

			int x=0, y = 0;
			int v;
			
			for(y = 0; y < imgH; ++y){
				for(x = 0; x < imgW; ++x){
					v = rgbs[y*(imgW) + x];
					if(v == colorCollisions){
						boxes.add(createBox(x, y, imgW, imgH, colorCollisions));
					}
					else if(v == colorSpawn){
						spawns.add(createBox(x, y, imgW, imgH, colorSpawn));
					}
				}
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private Rectangle createBox(int x, int y, int imgW, int imgH, int color){
		int w = x;
		int h = y;
		while(w < imgW && rgbs[y*(imgW) + w] == color){
			rgbs[y*(imgW) + w] = white;
			++w;
		}
		Rectangle line = new Rectangle(x, y, w-x, 1);
		
		while(h < imgH-1){
			++h;
			w=line.x;
			while(w < line.x+line.width){
				if(rgbs[h*(imgW) + w] != color && w >= line.x)
				{
					return line;
				}
				++w;
			}
			for(int i = line.x; i < line.x+line.width; i++){
				rgbs[h*(imgW) + i] = white;
			}
			line.setSize(line.width, line.height+1);
		}
		
		return line;
		
	}
	
	public ArrayList<Rectangle> getCollisions(){
		return boxes;
	}
	
	public ArrayList<Rectangle> getSpawns(){
		return spawns;
	}
}
