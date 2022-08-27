package com.eronryabets.first_pet.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileDownloadController {
    @Value("${download.path}")
    private String folderPath;

    @RequestMapping("/download")
    public String showFiles(Model model){
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        model.addAttribute("files",listOfFiles);
        return "showFiles";
    }

    @RequestMapping("/download/{fileName}")
    @ResponseBody
    public void show(@PathVariable("fileName") String fileName, HttpServletResponse response) {

        if (fileName.contains(".doc")) response.setContentType("application/msword");
        if (fileName.contains(".docx")) response.setContentType("application/msword");
        if (fileName.contains(".xls")) response.setContentType("application/vnd.ms-excel");
        if (fileName.contains(".ppt")) response.setContentType("application/ppt");
        if (fileName.contains(".pdf")) response.setContentType("application/pdf");
        if (fileName.contains(".zip")) response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" +fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(folderPath+fileName);
            int len;
            byte[] buf = new byte[1024];
            while((len = fis.read(buf)) > 0) {
                bos.write(buf,0,len);
            }
            bos.close();
            response.flushBuffer();
        }
        catch(IOException e) {
            e.printStackTrace();

        }
    }

}
