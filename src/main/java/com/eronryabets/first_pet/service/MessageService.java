package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.Message;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public Iterable<Message> findAll(){
        return messageRepository.findAll();
    }

    public Iterable<Message> findByTag(String filter){
        return messageRepository.findByTag(filter);
    }

    public void add(User user, String text, String tag,
                    MultipartFile file) throws IOException {
        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }

        messageRepository.save(message);
    }

    public void deleteMessage(Message message) {
        if (message.getFilename() != null) {
            String path = "F:\\Work\\TestProjects\\first_pet\\uploads\\";
            path = path.concat(message.getFilename());
            try {
                //Files.delete(Paths.get(uploadPath +"/"+ message.getFilename()));
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        messageRepository.deleteById(message.getId());
    }

}

