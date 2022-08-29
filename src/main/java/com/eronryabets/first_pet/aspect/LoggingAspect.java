package com.eronryabets.first_pet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggingAspect {

    @Value("${logs.path}")
    private String logsPath;

    @Pointcut(value = "execution(* com.eronryabets.first_pet.service.UserService.loadUserByUsername(..))")
    public void loginUserAuth() {

    }

    @AfterReturning("loginUserAuth()")
    public void loginUserAuth(JoinPoint joinPoint) throws IOException {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Object tmp = null;
        if(methodSignature.getName().equals("loadUserByUsername")){
            Object[] arguments = joinPoint.getArgs();
            for(Object obj : arguments){
                tmp = obj;
            }
        }
        LocalDate ld = LocalDate.now();
        File logsFolder = new File(logsPath);
        if (!logsFolder.exists()) {
            logsFolder.mkdir();
        }
        File log = new File(logsPath  + ld + "-logs.txt")  ;
        if(!log.exists()){
            log.createNewFile();
        }
        LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter d1 = DateTimeFormatter.ofPattern("y.MM.d-HH:mm:ss");
                String time = String.valueOf(ldt.format(d1));
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(log, true))){
            writer.write(time + " - auth username : " + tmp + ";\n");
        }
    }

}
