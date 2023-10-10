package br.com.rocketseat.learning.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel user){
        var userExistent = userRepository.findByUserName(user.getUserName());
        
        if(userExistent != null){
            return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Usuário já existe.");
        }

        var userCreated = this.userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userCreated);
    }
}
