package com.hhhao.note;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

//Security会默认提供一个用户认证（/login界面）
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
@MapperScan("com.hhhao.note.mapper")
public class Application {
	/**
	 * springboot对上传文件大小的控制
	 * 
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
//		单个数据的大小，单位mb
		factory.setMaxFileSize(DataSize.ofMegabytes(20));
//		总上传数据大小限制
		factory.setMaxRequestSize(DataSize.ofMegabytes(200));
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
