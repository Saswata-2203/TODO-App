package com.saswata.todolist.Utils;

import com.saswata.todolist.DTO.TaskRequestDTO;
import com.saswata.todolist.DTO.TaskResponseDTO;
import com.saswata.todolist.Entity.TaskEntity;

public class AppUtils {

    public static TaskEntity mapDTOToEntity(TaskRequestDTO taskRequestDTO) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName(taskRequestDTO.getTaskName());
        taskEntity.setTaskDescription(taskRequestDTO.getTaskDescription());
        taskEntity.setTaskDueDate(taskRequestDTO.getTaskDueDate());
        return taskEntity;
    }

    public static TaskResponseDTO mapEntityToDTO(TaskEntity taskEntity) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTaskId(taskEntity.getTaskId());
        taskResponseDTO.setTaskName(taskEntity.getTaskName());
        taskResponseDTO.setTaskDescription(taskEntity.getTaskDescription());
        taskResponseDTO.setTaskDueDate(taskEntity.getTaskDueDate());
        return taskResponseDTO;
    }
}
