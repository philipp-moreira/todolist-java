package br.com.rocketseat.learning.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraController")
public class MinhaPrimeiraController {
    
    @GetMapping("/primeiraMensagem")
    public String primeiraMensagem(){
        return "Funcionou";
    }
}
