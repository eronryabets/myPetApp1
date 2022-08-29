package com.eronryabets.first_pet.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class FileDownloadController {
    @Value("${download.path}")
    private String folderPath;

    @Value("${financeReports.path}")
    private String financeReports;

    @Value("${logs.path}")
    private String logsPath;

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

    @RequestMapping("/financeReports/{fileName}")
    @ResponseBody
    public void downloadReport(@PathVariable("fileName") String fileName, HttpServletResponse response) {

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
            FileInputStream fis = new FileInputStream(financeReports+fileName);
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/logsAuth")
    public String logsView(Model model){
        File folder = new File(logsPath);
        File[] listOfFiles = folder.listFiles();
        model.addAttribute("files",listOfFiles);
        return "logsAuth";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/user/logsAuth/{fileName}")
    @ResponseBody
    public void downloadLogs(@PathVariable("fileName") String fileName, HttpServletResponse response) {

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
            FileInputStream fis = new FileInputStream(logsPath+fileName);
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/logsAuth/delete/{fileName}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteLogs(@PathVariable("fileName") String fileName){
        if (fileName != null) {
            String path = "F:\\Work\\TestProjects\\first_pet\\logs\\";
            path = path.concat(fileName);
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/user/logsAuth";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/logsAuth/view/{fileName}")
    public String logViewPage(@PathVariable("fileName") String fileName, Model model)
            throws IOException {
        String path = "F:\\Work\\TestProjects\\first_pet\\logs\\";
        Path log = Paths.get(path + fileName);
        ArrayList<String> logList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(log)))){
            String line;
            while ((line = reader.readLine()) != null) {
                logList.add(line);
            }
        }
        model.addAttribute("logs",logList)
                .addAttribute("fileName", fileName);

        return "logViewPage";
    }

}


