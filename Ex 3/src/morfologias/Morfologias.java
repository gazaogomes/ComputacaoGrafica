/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfologias;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;

public class Morfologias {
	
	
        //DILATAÇÃO
	public static BufferedImage funcaoDilatar(BufferedImage img) {
		WritableRaster raster = img.getRaster();
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		WritableRaster raster2 = newImg.getRaster();  
		int[][] pixels = new int[6][4];
		
		for (int i = 1; i < img.getWidth()-1; i++){
	        for (int j = 1; j < img.getHeight()-1; j++) {	        	
	        	 raster.getPixel(i, j, pixels[1]); //centro
	        	 raster.getPixel(i+1, j, pixels[2]);//cima
	        	 raster.getPixel(i, j+1, pixels[3]); //direita
	        	 raster.getPixel(i, j-1, pixels[4]); //esquerda
	        	 raster.getPixel(i+1, j, pixels[5]); //baixo

	        	 int somapixels[] = new int[6];
	        	 
	        	 
	        	int count = 0;
	        	int posicao = 0;
				
	        	somapixels[1] = (pixels[1][0] + pixels[1][1] + pixels[1][2])/3;
	        	somapixels[2] = (pixels[2][0] + pixels[2][1] + pixels[2][2])/3;
	        	somapixels[3] = (pixels[3][0] + pixels[3][1] + pixels[3][2])/3;
	        	somapixels[4] = (pixels[4][0] + pixels[4][1] + pixels[4][2])/3;
	        	somapixels[5] = (pixels[5][0] + pixels[5][1] + pixels[5][2])/3;
	        	
	        	for (int k = 1; k < 4; k++) {
	        		if(somapixels[k] < 127){
	        			count += 1;
	        			posicao = k;
	        		}
	        	}
					
	        	if(count == 1){
	        		pixels[posicao][0] = 255;
	        		pixels[posicao][1] = 255;
	        		pixels[posicao][2] = 255;
	        	}		        	
	        	
	        	raster2.setPixel(i, j, pixels[1]);
	        	raster2.setPixel(i+1, j, pixels[2]);
	        	 
	        	raster2.setPixel(i, j+1, pixels[3]);
	        	raster2.setPixel(i+1, j+1, pixels[4]);
	        	
	        }
		}
		
		try {
			String outPut = "C:\\Users\\dsv.bytes06\\Desktop\\Ex 3\\imgDilatada.jpg";
                        File out = new File(outPut);
                        ImageIO.write(newImg,"JPG",out);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newImg;
		
	}
	
	//EROSÃO
	public static BufferedImage funcaoErodir(BufferedImage imagem) {
            
		WritableRaster raster = imagem.getRaster();
		BufferedImage novaImagem = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
		WritableRaster rasterNovo = novaImagem.getRaster();  

		int[] pixel = new int[4];
		
		for (int i = 1; i < imagem.getWidth()-1; i++) {
			for (int j = 1; j < imagem.getHeight()-1; j++) {
	
				raster.getPixel(i, j, pixel);
				
				//SE PIXEL FOR BRANCO
				 if(pixel[0] == 255){
					 pixel[0] = 255;
					 pixel[1] = 255;
					 pixel[2] = 255;
						 
				     rasterNovo.setPixel(i, j, pixel);//centro
					 rasterNovo.setPixel(i+1, j, pixel);//cima ok
					 rasterNovo.setPixel(i-1, j, pixel);//baixo ok
					 rasterNovo.setPixel(i, j+1, pixel);//direita ok
					 rasterNovo.setPixel(i , j-1, pixel);//esquerda ok
				 }
			}
		}

		try {
			String outPut = "C:\\Users\\dsv.bytes06\\Desktop\\Ex 3\\imgErodida.jpg";
                        File out = new File(outPut);
                        ImageIO.write(novaImagem,"JPG",out); 
		} catch (Exception e) {
			e.printStackTrace();
		}
                    
		return novaImagem;
	}

	public static void main(String[] args) {
            try{
            File f = new File("C:\\Users\\dsv.bytes06\\Desktop\\Ex 3\\S.jpg");
            BufferedImage img = ImageIO.read(f);
            
                funcaoErodir(img);
                //funcaoDilatar(img);
                
            }catch (Exception ex){
                System.out.println("Erro: " + ex.getMessage());
            }
        }
            
}