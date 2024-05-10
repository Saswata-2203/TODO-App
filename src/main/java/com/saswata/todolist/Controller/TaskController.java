package com.saswata.todolist.Controller;

import com.saswata.todolist.DTO.TaskRequestDTO;
import com.saswata.todolist.DTO.TaskResponseDTO;
import com.saswata.todolist.Service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "add a new task to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "task added successfully",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = TaskResponseDTO.class))
                    }),
            @ApiResponse(responseCode = "400",description = "validation error")
    })
    @PostMapping("/add")
    public ResponseEntity<TaskResponseDTO> addTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = taskService.addTask(taskRequestDTO);
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch all task object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "task fetched successfully",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = TaskResponseDTO.class))
                    }),
            @ApiResponse(responseCode = "400",description = "validation error")
    })
    @GetMapping("/all")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> taskResponseDTOList = taskService.getAllTasks();
        return new ResponseEntity<>(taskResponseDTOList, HttpStatus.OK);
    }

    @Operation(summary = "Find task by taskName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "task found",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = TaskResponseDTO.class))
                    }),
            @ApiResponse(responseCode = "404",description = "task not found")
    })
    @GetMapping("/{taskName}")
    public ResponseEntity<TaskResponseDTO> getTaskByName(@PathVariable String taskName) {
        TaskResponseDTO taskResponseDTO = taskService.getTaskByName(taskName);
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "update a task in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "task updated successfully",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = TaskResponseDTO.class))
                    }),
            @ApiResponse(responseCode = "404",description = "task not found")
    })
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Integer taskId, @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = taskService.updateTask(taskId, taskRequestDTO);
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "delete a task from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "task deleted successfully"),
            @ApiResponse(responseCode = "404",description = "task not found")
    })
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
