package com.eronryabets.first_pet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class FileDownloadService {
    @Value("${download.path}")
    private String folderPath;

    @Value("${financeReports.path}")
    private String financeReports;

    @Value("${logs.path}")
    private String logsPath;

    String pathDownloads = "F:\\Work\\TestProjects\\first_pet\\downloads\\";
    String pathLogs = "F:\\Work\\TestProjects\\first_pet\\logs\\";

    public File[] showFiles(){
        File folder = new File(folderPath);
        return folder.listFiles();
    }

    public void show(String fileName, HttpServletResponse response){

        this.downloadOrShowFiles(folderPath,fileName,response);
    }

    public void addFile(MultipartFile file) throws IOException {
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
    }

    public void deleteFile(String fileName){

        this.deleteFilesOrLogs(fileName,pathDownloads);
    }

    public void downloadReport(String fileName, HttpServletResponse response){

        this.downloadOrShowFiles(financeReports,fileName,response);
    }

    public File[] logsView(){
        File folder = new File(logsPath);
        return folder.listFiles();
    }

    public void downloadLogs(String fileName, HttpServletResponse response){

        this.downloadOrShowFiles(logsPath,fileName,response);
    }

    public void deleteLogs(String fileName){

        this.deleteFilesOrLogs(fileName,pathLogs);
    }

    public ArrayList<String> logViewPage(String fileName){
        String path = "F:\\Work\\TestProjects\\first_pet\\logs\\";
        Path log = Paths.get(path + fileName);
        ArrayList<String> logList = new ArrayList<>();
        try {
            try(BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(log)))){
                String line;
                while ((line = reader.readLine()) != null) {
                    logList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logList;
    }

    public void downloadOrShowFiles(String path, String fileName, HttpServletResponse response){
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
            FileInputStream fis = new FileInputStream(path+fileName);
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

    public void deleteFilesOrLogs(String fileName, String path){
        if (fileName != null) {
            path = path.concat(fileName);
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
