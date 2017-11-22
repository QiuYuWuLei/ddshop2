package com.zhangli.ddshop.service.impl;

import com.zhangli.ddshop.common.util.FtpUtils;
import com.zhangli.ddshop.common.util.IDUtils;
import com.zhangli.ddshop.common.util.PropKit;
import com.zhangli.ddshop.service.FileService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public Map<String, Object> uploadImage(MultipartFile upfile) {
        Map<String,Object>map=new HashMap<>();
        String name="ftp.properties";
        String host= PropKit.use(name).get("ftp.address");
        int port=PropKit.use(name).getInt("ftp.port");
        String username=PropKit.use(name).get("ftp.username");
        String password=PropKit.use(name).get("ftp.password");
        String basePath=PropKit.use(name).get("ftp.basePath");
        //创建文件路径：基础路径+文件路径+
        String filePath=new DateTime().toString("/yyyy-MM-dd");

        String originalFileName=upfile.getOriginalFilename();
        String fileType=originalFileName.substring(originalFileName.lastIndexOf("."));
        String newName=IDUtils.genImageName()+fileType;

        InputStream inputStream=null;
        try {
            inputStream=upfile.getInputStream();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        //
        boolean bool= FtpUtils.uploadFile(host,port,username,password,basePath,filePath,newName,inputStream);
        if(bool){
            map.put("state","SUCCESS");
            map.put("title",newName);
            map.put("original",originalFileName);
            map.put("type",fileType);
            map.put("url",filePath+"/"+newName);
            map.put("size",upfile.getSize());
        }
        return  map;
    }
}
