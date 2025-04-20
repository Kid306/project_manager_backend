package com.mdp.arc.img.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mdp.arc.img.entity.Image;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service("mdp.arc.imageCompressService")
@DS("arc-ds")
public class ImageCompressService {

	public static void main(String[] args) {
		

	}

	public List<Image> compressImages(List<Image> images){
		return images;
		
	}
	/**
	 * 压缩保存图片
	 * @param srcInputStream 文件输入流
	 * @param scale 压缩比例 默认为1 0.1~1之间
	 * @param outputQuality 压缩质量默认0.5f,0.1f~1之间, 0.5f,0.2f,01.f 一般按0.5f压缩
	 * @param savePath 保存路径包括文件名及后缀
	 * @return
	 */
	public Tips compressAndSaveImage(InputStream srcInputStream,float scale,float outputQuality,String savePath){
		
		try {
			Thumbnails.of(srcInputStream).
					scale(scale).
					outputQuality(outputQuality).toFile( savePath);
		} catch (IOException e) {
			return LangTips.errMsg("compress-img-err", "压缩并保存文件失败");
 		}
		return LangTips.okMsg();
		
	}
	
	/**
	 * 压缩保存图片(图片以base64格式上传时调用)
	 * @param fileContext 文件内容，扣除掉"data:image/png;base64,"
	 * @param scale 压缩比例 默认为1 0.1~1之间
	 * @param outputQuality 压缩质量默认0.5f,0.1f~1之间, 0.5f,0.2f,01.f 一般按0.5f压缩
	 * @param savePath 保存路径包括文件名及后缀
	 * @return
	 */
	public Tips compressAndSaveImage(byte[] fileContext,float scale,float outputQuality,String savePath){
		
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(fileContext);    //将b作为输入流；

			BufferedImage image = ImageIO.read(in);
			Thumbnails.of(image).scale(1f).outputQuality(outputQuality) 
			.toFile( savePath);
		} catch (IOException e) {
			return LangTips.errMsg("compress-img-err", "压缩并保存文件失败");
		}
		return LangTips.okMsg();
		
	}
}
