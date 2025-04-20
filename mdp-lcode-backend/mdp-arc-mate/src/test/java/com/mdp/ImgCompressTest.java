package com.mdp;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ImgCompressTest {

	public static void main(String[] args) {
		try {
			//1.jpg压缩测试
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/1.jpg") 
			.scale(1f) 
			.outputQuality(1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/1_1f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/1.jpg") 
			.scale(1f) 
			.outputQuality(0.5f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/1_0.5f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/1.jpg") 
			.scale(1f) 
			.outputQuality(0.2f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/1_0.2f.jpg");
			
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/1.jpg") 
			.scale(1f) 
			.outputQuality(0.1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/1_0.1f.jpg");
			
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/1.jpg") 
			.scale(0.5f) 
			.outputQuality(0.5f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/1_0.5f_0.5f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/1.jpg") 
			.scale(0.5f) 
			.outputQuality(0.2f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/1_0.5f_0.2f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/1.jpg") 
			.scale(0.5f) 
			.outputQuality(0.1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/1_0.5f_0.1f.jpg");
			
			
			//2.jpg压缩测试
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/2.jpg") 
			.scale(1f) 
			.outputQuality(0.5f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/2_0.5f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/2.jpg") 
			.scale(1f) 
			.outputQuality(0.2f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/2_0.2f.jpg");
			
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/2.jpg") 
			.scale(1f) 
			.outputQuality(0.1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/2_0.1f.jpg");
			
			
			//3.jpg压缩测试
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/3.jpg") 
			.scale(1f) 
			.outputQuality(0.5f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/3_0.5f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/3.jpg") 
			.scale(1f) 
			.outputQuality(0.2f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/3_0.2f.jpg");
			
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/3.jpg") 
			.scale(1f) 
			.outputQuality(0.1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/3_0.1f.jpg");
			
			//4.jpg压缩测试
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/4.jpg") 
			.scale(1f) 
			.outputQuality(1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/4_1f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/4.jpg") 
			.scale(1f) 
			.outputQuality(0.5f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/4_0.5f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/4.jpg") 
			.scale(1f) 
			.outputQuality(0.2f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/4_0.2f.jpg");
			
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/4.jpg") 
			.scale(1f) 
			.outputQuality(0.1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/4_0.1f.jpg");
			
			//11.jpg风景图片压缩测试
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/11.jpg") 
			.scale(1f) 
			.outputQuality(0.5f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/11_0.5f.jpg");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/11.jpg") 
			.scale(1f) 
			.outputQuality(0.2f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/11_0.2f.jpg");
			
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/11.jpg") 
			.scale(1f) 
			.outputQuality(0.1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/11_0.1f.jpg");
			
			
			//12.png png图片压缩测试
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/12.png") 
			.scale(1f) 
			.outputQuality(0.5f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/12_0.5f.png");
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/12.png") 
			.scale(1f) 
			.outputQuality(0.2f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/12_0.2f.png");
			
			
			Thumbnails.of("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/12.png") 
			.scale(1f) 
			.outputQuality(0.1f) 
			.toFile("C:/Users/陈裕财/wsp/mdp-arc/src/test/resources/商品图片/压缩后/12_0.1f.png");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
