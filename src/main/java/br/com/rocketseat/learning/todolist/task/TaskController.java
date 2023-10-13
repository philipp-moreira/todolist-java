package br.com.rocketseat.learning.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rocketseat.learning.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private ITaskRepository taskRepository;

    @GetMapping("/")
    public ResponseEntity get(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var idUserConverted = (UUID) idUser;
        var tasks = taskRepository.findByIdUser(idUserConverted);

        return buildReturn(tasks, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel task, HttpServletRequest request) {

        var dateIsValid = datesOfTaskIsValid(task);
        if (dateIsValid.length() > 0) {
            return buildReturn(dateIsValid, HttpStatus.BAD_REQUEST);
        }

        var idUser = request.getAttribute("idUser");
        task.setId((UUID) idUser);

        var taskRegistered = taskRepository.save(task);

        return buildReturn(taskRegistered, HttpStatus.OK);
    }

    @PutMapping("/{idTask}")
    public ResponseEntity put(@RequestBody TaskModel task, HttpServletRequest request, @PathVariable UUID idTask){

        var dateIsValid = datesOfTaskIsValid(task);
        if(dateIsValid.length() > 0){
            return buildReturn(dateIsValid, HttpStatus.BAD_REQUEST);
        }

        var taskExistent = taskRepository.findById(idTask).orElse(null);
        if(taskExistent == null){
            return buildReturn("Tarefa não localizada.", HttpStatus.BAD_REQUEST);
        }

        var idUser = request.getAttribute("idUser");
        var idUserConverted = (UUID)idUser;

        if(!taskExistent.getIdUser().equals(idUserConverted)){
            return buildReturn("Usuário não tem permissão para alterar esta tarefa.", HttpStatus.BAD_REQUEST);
        }
        
        task.setIdUser(idUserConverted);
        task.setId(idTask);

        var taskUpdated = taskRepository.save(task);

        return buildReturn(taskUpdated, HttpStatus.OK);
    }

    @PatchMapping("/{idTask}")
    public ResponseEntity pacth(@RequestBody TaskModel task, @PathVariable UUID idTask) {

        var taskExistent = taskRepository.findById(idTask).orElse(null);
        if (taskExistent == null) {
            return buildReturn("Task informada não localizada.", HttpStatus.BAD_REQUEST);
        }

        Utils.copyNonNullProperties(task, taskExistent);

        var taskUpdated = taskRepository.save(taskExistent);

        return buildReturn(taskUpdated, HttpStatus.OK);
    }

    private ResponseEntity buildReturn(Object object, HttpStatus httpStatusCode) {
        return ResponseEntity
                .status(httpStatusCode)
                .body(object);
    }

    private String datesOfTaskIsValid(TaskModel task) {
        String response = "";
        var currentDateTime = LocalDateTime.now();

        if (currentDateTime.isAfter(task.getStartAt()) || currentDateTime.isAfter(task.getEndAt())) {
            response = "A data de ínicio/término deve ser maior que a data atual.";
        }

        if (task.getStartAt().isAfter(task.getEndAt())) {
            response += "A data de ínicio deve ser menor que a data de término.";
        }

        return response;
    }
}
