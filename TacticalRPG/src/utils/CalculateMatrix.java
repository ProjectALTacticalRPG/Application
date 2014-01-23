package utils;

import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CalculateMatrix {


	ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();
	int color = (255<<24)+(255<<16)+(0<<8)+148;
	int white = (255<<24)+(255<<16)+(255<<8)+255;
	int[] rgbs;
	
	public ArrayList<Rectangle> calculateMatrix(String imgPath){

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
					if(v == color){
						boxes.add(createBox(x, y, imgW, imgH));
					}
				}
			}
			
			return boxes;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	private Rectangle createBox(int x, int y, int imgW, int imgH){
		int w = x;
		int h = y;
		while(w < imgW && rgbs[y*(imgW) + w] == color){
			rgbs[y*(imgW) + w] = white;
			++w;
		}
		Rectangle line = new Rectangle(x, y, w-x, h-y);
		
		while(h < imgH){
			++h;
			w=line.x;
			while(w < imgW){
				if(rgbs[y*(imgW) + w] != color && w >= line.x && w <= line.x+line.width)
				{
					return line;
				}
				++w;
			}
			for(int i = line.x; i < line.x+line.width; i++){
				rgbs[h*(imgW) + x] = white;
			}
			line.setSize(line.width, line.height+1);
		}
		
		return line;
		
	}
}