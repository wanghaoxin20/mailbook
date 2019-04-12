package cn.edu.cqupt.nmid.mailbook.service.impl;

import cn.edu.cqupt.nmid.mailbook.service.FileManageService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

/****
 * @author:MrWangx
 * @description
 * @Date 2019/2/23 22:15
 *****/
@Service
public class FileManageServiceImpl implements FileManageService {

    @Value("${ftp.host}")
    private String ftpHost;     //ftp服务器的host

    @Value("${ftp.port}")
    private Integer ftpPort;     //ftp服务器端口

    @Value("${ftp.username}")
    private String ftpUsername; //ftp服务器用户名

    @Value("${ftp.password}")
    private String ftpPassword; //ftp服务器密码

    @Value("${ftp.basepath}")
    private String ftpBasepath; //


    /**
     * @param files
     * @param filepath
     * @param filenames
     * @return
     */
    @Override
    public boolean uploadFile(MultipartFile[] files, String filepath, String... filenames) {
        boolean result = false;
        //判断filenames和files数量是否相等
        if (filenames.length != files.length) {
            return result;
        }
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(ftpHost, ftpPort);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(ftpUsername, ftpPassword);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.enterLocalPassiveMode();
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(ftpBasepath + filepath)) {
                //如果目录不存在创建目录
                String[] dirs = filepath.split("/");
                String tempPath = ftpBasepath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文档
            for (int i = 0; i < filenames.length; i++) {
                if (!ftp.storeFile(filenames[i], files[i].getInputStream())) {
                    System.out.println(filenames[i]);
                    return result;
                }
                files[i].getInputStream().close();
            }
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {

                }
            }
        }
        return result;
    }

    /**
     * @param filepath
     * @param filename
     * @param out
     * @return
     */
    @Override
    public boolean downloadFile(String filepath, String filename, OutputStream out) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(ftpHost, ftpPort);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(ftpUsername, ftpPassword);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(filepath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(filename)) {
                    ftp.retrieveFile(ff.getName(), out);
                    out.close();
                }
            }
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }


}
