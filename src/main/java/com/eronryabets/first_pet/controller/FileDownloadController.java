package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    @PostMapping("/download")
    public String addFile(@RequestParam("file") MultipartFile file
    ) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(folderPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            LocalDateTime ldt = LocalDateTime.now();
            DateTimeFormatter d1 = DateTimeFormatter.ofPattern("y.MM.d-HH.mm.ss");
            String time = String.valueOf(ldt.format(d1));
            String resultFilename = time + "." + file.getOriginalFilename();
            file.transferTo(new File(folderPath + "/" + resultFilename));
        }

        return "redirect:/download";
    }

    @RequestMapping(value = "/download/delete/{fileName}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteFile(@PathVariable("fileName") String fileName){
        if (fileName != null) {
            String path = "F:\\Work\\TestProjects\\first_pet\\downloads\\";
            path = path.concat(fileName);
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/download";
    }

}


