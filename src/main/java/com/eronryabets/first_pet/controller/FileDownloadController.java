package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileDownloadController {

    @Autowired
    private FileDownloadService fileDownloadService;

    @RequestMapping("/download")
    public String showFiles(Model model){
        model.addAttribute("files",fileDownloadService.showFiles());
        return "showFiles";
    }

    @RequestMapping("/download/{fileName}")
    @ResponseBody
    public void show(@PathVariable("fileName") String fileName, HttpServletResponse response) {

        fileDownloadService.show(fileName,response);
    }

    @PostMapping("/download")
    public String addFile(@RequestParam("file") MultipartFile file
    ) throws IOException {

        fileDownloadService.addFile(file);
        return "redirect:/download";
    }

    @RequestMapping(value = "/download/delete/{fileName}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteFile(@PathVariable("fileName") String fileName){

        fileDownloadService.deleteFile(fileName);
        return "redirect:/download";
    }

    @RequestMapping("/financeReports/{fileName}")
    @ResponseBody
    public void downloadReport(@PathVariable("fileName") String fileName, HttpServletResponse response) {

        fileDownloadService.downloadReport(fileName,response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/logsAuth")
    public String logsView(Model model){

        model.addAttribute("files",fileDownloadService.logsView());
        return "logsAuth";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/user/logsAuth/{fileName}")
    @ResponseBody
    public void downloadLogs(@PathVariable("fileName") String fileName, HttpServletResponse response) {

        fileDownloadService.downloadLogs(fileName,response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/logsAuth/delete/{fileName}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteLogs(@PathVariable("fileName") String fileName){

        fileDownloadService.deleteLogs(fileName);
        return "redirect:/user/logsAuth";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/logsAuth/view/{fileName}")
    public String logViewPage(@PathVariable("fileName") String fileName, Model model){

        model.addAttribute("logs",fileDownloadService.logViewPage(fileName))
                .addAttribute("fileName", fileName);

        return "logViewPage";
    }

}


