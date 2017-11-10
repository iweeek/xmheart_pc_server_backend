package com.xmheart.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	static 	public String uploadFile(String path, MultipartFile mFile) throws IOException {

	    String fileName = UUID.randomUUID().toString();
	    String oriFileName = mFile.getOriginalFilename();
	    if (oriFileName != null) {
    	    String ext = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
    	    ext = ext.toLowerCase();
    	    
    	    fileName = fileName + ext;
	    }
	    
		String filePath = path + File.separator + fileName;
		File file = new File(filePath);

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}

		if (!file.exists()) {
			file.createNewFile();
		}

		mFile.transferTo(file);
		
		return fileName;
	}
}
