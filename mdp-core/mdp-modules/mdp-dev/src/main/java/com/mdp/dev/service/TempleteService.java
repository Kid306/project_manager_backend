package com.mdp.dev.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.util.Map;

/**
 * @author chenyc
 *
 */
@Service
public class TempleteService {
	
	@Autowired
	FreeMarkerConfigurer freeMarkerConfigurer;
	
	
	public Template getTemplate(String name)
			throws IOException { 
		Template temp = freeMarkerConfigurer.getConfiguration().getTemplate(name); // 在模板文件目录中寻找名为"name"的模板文件
		return temp; // 此时FreeMarker就会到类路径下的"pathPrefix"文件夹中寻找名为"name"的模板文件
	}
	  
	/**
	 * 打印文件到硬盘
	 * @param fullOutFileName 要生成的带路径的文件名
	 * @param templateFileName 模板名字 如pubDao.ftl
	 * @param rootMap 数据容器
	 * @throws TemplateException
	 * @throws IOException
	 */
	 public void toFile(String fullOutFileName,String templateFileName,Map<String,Object> rootMap) {
		 Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fullOutFileName), "UTF-8"));
			getTemplate(templateFileName).process(rootMap, out);
		} catch (UnsupportedEncodingException e) {
			 
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			 
			e.printStackTrace();
		} catch (TemplateException e) {
			 
			e.printStackTrace();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}finally{
			if(out!=null ){
				try {
					out.close();
				} catch (IOException e) {
					out=null;
				}
			}
		}
	}
}
