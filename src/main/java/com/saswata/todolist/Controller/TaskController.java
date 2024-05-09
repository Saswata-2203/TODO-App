package com.saswata.todolist.Controller;

import com.saswata.todolist.DTO.TaskRequestDTO;
import com.saswata.todolist.DTO.TaskResponseDTO;
import com.saswata.todolist.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<TaskResponseDTO> addTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = taskService.addTask(taskRequestDTO);
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> taskResponseDTOList = taskService.getAllTasks();
        return new ResponseEntity<>(taskResponseDTOList, HttpStatus.OK);
    }

    @GetMapping("/{taskName}")
    public ResponseEntity<TaskResponseDTO> getTaskByName(@PathVariable String taskName) {
        TaskResponseDTO taskResponseDTO = taskService.getTaskByName(taskName);
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Integer taskId, @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = taskService.updateTask(taskId, taskRequestDTO);
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
