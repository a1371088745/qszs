package com.wt.controller;

import com.wt.entity.JsonData;
import com.wt.entity.User;
import com.wt.service.FileService;
import com.wt.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/file")
@Controller
public class FileController {
    @Autowired
    private FileServiceImpl fileService;
    @RequestMapping("/toFileUpload")
    public String toFileUpload(){
        return "fileUpload";
    }

    @RequestMapping("/toFileDownload")
    public String toFileDownload(){
        return "fileDownload";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String  upload(@RequestParam("file")MultipartFile file, HttpSession session){
        if(file.isEmpty()){
            return "上传失败，请选择文件";
        }
        String fileName=file.getOriginalFilename();
        String filePath=System.getProperty("user.dir")+"/src/main/resources/static/file/";
        File dest =new File(filePath+fileName);
        try {
            dest.createNewFile();
            file.transferTo(dest);
            fileService.UploadFile(fileName);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @RequestMapping("/readAllFile")
    @ResponseBody
    public JsonData readAllFile(){
        List<com.wt.entity.File> files = fileService.readAllFile();
        return  JsonData.buildSuccess(files);
    }

    @RequestMapping("/fileDownload")
    @ResponseBody
    public void fileDownload(HttpServletResponse response,String fileName) {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/file/";
        File dest = new File(filePath + fileName);
        if (dest.exists()) {
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream outputStream=null;
            try {
                fis = new FileInputStream(dest);
                bis = new BufferedInputStream(fis);
                outputStream = response.getOutputStream();
                int len = 0;
                while ((len = bis.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
