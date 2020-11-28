package com.grossery.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	private static final Logger logger = Logger.getLogger(UploadService.class);
	public String uploadFile(MultipartFile uploadedFile) throws IOException{
		
		InputStream in = uploadedFile.getInputStream();
	    File currDir = new File(".");
	    String path = currDir.getAbsolutePath();
	    String fileLocation = path.substring(0, path.length() - 1) + uploadedFile.getOriginalFilename();
	    logger.info("path is--"+fileLocation);
	    FileOutputStream f = new FileOutputStream(fileLocation);
	    int ch = 0;
	    while ((ch = in.read()) != -1) {
	        f.write(ch);
	    }
	    f.flush();
	    f.close();
		return fileLocation;
	}
	
	public boolean deleteFile(String path){
		
		File myObj = new File(path); 
	    if (myObj.delete()) { 
	      return true;
	    } else {
	      return false;
	    } 
		
		
	}

}
