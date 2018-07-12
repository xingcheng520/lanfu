package com.shengtian.lanfu.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component  
@ConfigurationProperties(prefix="myProps")
public class MyProps {
	 private String filePath;
	 private String  LogoPath;
	 private String filebannerPath;

	

	public String getLogoPath() {
		return LogoPath;
	}

	public void setLogoPath(String logoPath) {
		LogoPath = logoPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilebannerPath() {
		return filebannerPath;
	}

	public void setFilebannerPath(String filebannerPath) {
		this.filebannerPath = filebannerPath;
	}  
	
}
