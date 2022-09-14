package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.Role;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Value("${uploadAvatar.path}")
    private String uploadAvatarPath;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }

    public void userSave(User user, String username, String name, String surname,
                         String password, Map<String, String> form) {

        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        if(!password.isEmpty()){
            user.setPassword(passwordEncoder.encode(password));
        }

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void profileSave(User user, String name, String surname, String password,
                            MultipartFile avatar) throws IOException {
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (avatar != null && !avatar.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadAvatarPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + avatar.getOriginalFilename();
            avatar.transferTo(new File(uploadAvatarPath + "/" + resultFilename));

            if (!user.getAvatar().equals(avatar.getOriginalFilename())) {
                String path = "F:\\Work\\TestProjects\\first_pet\\uploads\\avatar\\";
                path = path.concat(user.getAvatar());
                try {
                    //Files.delete(Paths.get(uploadAvatarPath +"/"+ user.getAvatar()));
                    Files.delete(Paths.get(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            user.setAvatar(resultFilename);
        }

        userRepository.save(user);
    }

    public void userDelete(User user) {
        userRepository.delete(user);
    }

}
