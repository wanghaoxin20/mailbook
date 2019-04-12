package cn.edu.cqupt.nmid.mailbook.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

/****
 *****/
public interface FileManageService {


    /**
     * @param files
     * @param filepath
     * @param filenames
     * @return
     */
    boolean uploadFile(MultipartFile[] files, String filepath, String... filenames);


    /**
     * @param filepath
     * @param filename
     * @param out
     * @return
     */
    boolean downloadFile(String filepath, String filename, OutputStream out);


}
