package com.zhangli.upload;

import com.zhangli.ddshop.common.util.FtpUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FtpUploadTest {
  /*  @Test
    public void  testFTPClient() throws IOException {
        //创建FTPClient客户端
        FTPClient ftpClient=new FTPClient();
        //创建FTP连接
        ftpClient.connect("101.132.177.253",21);
        //登录
        ftpClient.login("ftpuser1","zl1994529zl");
        //读取本地文件
        FileInputStream fileInputStream=new FileInputStream(new File("d:\\3.png"));
        //配置上传参数
        ftpClient.changeWorkingDirectory("/home/ftpuser1/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //上传文件
        ftpClient.storeFile("hello.jpg", fileInputStream);
        //关闭连接
        fileInputStream.close();
        ftpClient.logout();
    }*/

  @Test
  public void testFtpUtils() throws IOException {
      FileInputStream  fileInputStream=new FileInputStream(new File("d://3.png"));
      FtpUtils.uploadFile("101.132.177.253",21,"ftpuser1","zl1994529zl","/home/ftpuser1/www/images","/2017/11/17","hello2.png",fileInputStream);
    fileInputStream.close();
  }
}
