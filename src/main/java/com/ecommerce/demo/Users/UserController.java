
package com.ecommerce.demo.Users;

import com.ecommerce.demo.ApiResponse;
import com.ecommerce.demo.Dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(new ApiResponse(true,"create a new  category"), HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public List<User> listuser(){
        return userService.listuser();
    }

    @GetMapping("/List/{user_id}")
    public Optional<User> finduserid(@PathVariable("user_id") long userid){
        return userService.findbyid(userid);
    }

    @GetMapping("email/{email}")
    public Optional<User> finduseremail(@PathVariable("email") String email){
        return userService.findbyemail(email);
    }

    @PutMapping("update/{user_id}")
    public ResponseEntity<ApiResponse> updateuser(@PathVariable("user_id") Long userid ,@RequestBody User user) throws Exception {
        System.out.println("user_id : " +userid);

        if (!userService.findId(userid)) {
            return new ResponseEntity<>(new ApiResponse(false, "user does not exist"), HttpStatus.NOT_FOUND);
        }

        if (user.getFirstName() == null && user.getLastName() == null && user.getMobileNumber() == null && (user.getType() == null || user.getType().isEmpty())) {
            return new ResponseEntity<>(new ApiResponse(false, "At least one field must be provided."), HttpStatus.BAD_REQUEST);
        }

        userService.edituser(userid, user);
        return new ResponseEntity<>(new ApiResponse(true, "user updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("delete/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable("user_id") long userid){
        System.out.println("delete by userid "+ userid);
        userService.deleteByid(userid);
        return ResponseEntity.ok("delete success");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        return userService.authenticate(loginDTO);
    }
}

