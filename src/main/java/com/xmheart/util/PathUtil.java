package com.xmheart.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by kouga on 2017/8/19.
 */
@Component
public class PathUtil {
    
    private static PathUtil instance;
    
    public static PathUtil getInstance() {
        return instance;
    }
    
    /**
     * 需要配置，图片和视频服务器
     */
    public String ORIGIN = "";
    
    /**
     * 不能有默认的无参构造方法
     */
    @Autowired
    public PathUtil(@Value("${hostname}") String hostName) {      
        ORIGIN = hostName;
        instance = this;
    }
    
	public static final String WEBSERVER_PATH = System.getProperty("catalina.base");
	public static final String WEBAPP_PATH  = WEBSERVER_PATH + File.separator +"webapps" + File.separator;
	
	public static final String IMG_FOLDER_PATH = "images_storage" + File.separator;
	public static final String IMG_STORAGE_PATH = WEBAPP_PATH + File.separator + IMG_FOLDER_PATH + File.separator;
	
	public static final String VIDEO_FOLDER_PATH = "videos_storage" + File.separator;
	public static final String VIDEO_STORAGE_PATH = WEBAPP_PATH + File.separator + VIDEO_FOLDER_PATH + File.separator;

    /**
     * 获取到classes目录
     *
     * @return path
     */
    public static String getClassPath() {
        String systemName = System.getProperty("os.name");

        //判断当前环境，如果是Windows 要截取路径的第一个 '/'
        if (!StringUtils.isBlank(systemName) && systemName.indexOf("Windows") != -1) {
            return PathUtil.class.getResource("/").getFile().toString().substring(1);
        } else {
            return PathUtil.class.getResource("/").getFile().toString();
        }
    }
    
    public static String getImagesPath() {
    	String path = PathUtil.getWebRootPath() + File.separator +"images";
    	return path;
    }

    /**
     * 获取到项目的路径
     *
     * @return path
     */
    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取 root目录
     *
     * @return path
     */
    public static String getWebRootPath() {
        File file = new File(getClassPath());
        File parent = file.getParentFile().getParentFile();
        return parent.getPath();
    }

    /**
     * 文本换行，因为Linux  和 Windows 的换行符不一样
     *
     * @return
     */
    public static String nextLine() {
        String nextLine = System.getProperty("line.separator");
        return nextLine;
    }

    /**
     * 获取images 路径
     *
     * @return
     */
    public static String getImagePath() {
        return getWebRootPath() + File.separator + "images" + File.separator;
    }


}
