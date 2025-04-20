package com.mdp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
//@SpringCloudApplication
//@EnableSwagger2
public class ArcApplication   {
	
	
	public static void main(String[] args) {
		SpringApplication.run(ArcApplication.class,args);

	}

	/**
	 * 文件上传配置
	 *
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//  单个数据大小
		factory.setMaxFileSize(DataSize.ofMegabytes(5)); // KB,MB
		/// 总上传数据大小
		factory.setMaxRequestSize(DataSize.ofMegabytes(1));
		return factory.createMultipartConfig();
	}

}
