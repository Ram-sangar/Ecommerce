package com.ecommerce.demo.Users;

import com.ecommerce.demo.Dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> listuser() {
        return userRepository.findAll();
    }

    public Optional<User> findbyid(long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findbyemail(String email) {
        return userRepository.findByEmail(email);
    }

    public void edituser(Long userId, User updateuser) throws Exception {

        Optional<User> maybeUser = userRepository.findById(userId);

        if(maybeUser.isPresent()) {
            User user = maybeUser.get();
            if (updateuser.getEmail() != null) {
                user.setEmail(updateuser.getEmail());
            }
            if (updateuser.getType() != null) {
                user.setType(updateuser.getType());
            }
            if (updateuser.getFirstName() != null) {
                user.setFirstName(updateuser.getFirstName());
            }
            if (updateuser.getLastName() != null) {
                user.setLastName(updateuser.getLastName());
            }
            if (updateuser.getMobileNumber() != null) {
                user.setMobileNumber(updateuser.getMobileNumber());
            }
            userRepository.save(user);
        }
        else{
            throw new Exception("Product Not Found");
        }

    }

    public void deleteByid(long userid) {
        userRepository.deleteById(userid);
    }

    public String authenticate(LoginDTO login) {
        Optional<User> maybeUser = userRepository.findByEmail(login.getEmail());
        if(maybeUser.isPresent()) {
            User user = maybeUser.get();
            String password = login.getPassword();
            String encodePassword = user.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodePassword);
            if (isPwdRight) {
                return "Login Success";
            } else {
                return "Login Failed";
            }

        }else{
            return"Email not Exists";
        }
    }

    public boolean findId(Long userid) {
        return userRepository.findById(userid).isPresent();
    }
}

