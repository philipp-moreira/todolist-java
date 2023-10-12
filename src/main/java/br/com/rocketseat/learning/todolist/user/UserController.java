package br.com.rocketseat.learning.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody UserModel user){
        var userExistent = userRepository.findByUserName(user.getUserName());
        
        if(userExistent != null){
            return buildReturn("Usuário já existe.", HttpStatus.BAD_REQUEST);
        }

        var passwordHasred = BCrypt
                                .withDefaults()
                                .hashToString(12, user.getPassword().toCharArray());
        
        user.setPassword(passwordHasred);

        var userCreated = this.userRepository.save(user);

        return buildReturn(userCreated, HttpStatus.CREATED);
    }

    private ResponseEntity buildReturn(Object object, HttpStatus httpStatusCode){
        return ResponseEntity
                .status(httpStatusCode)
                .body(object);
    }
}
