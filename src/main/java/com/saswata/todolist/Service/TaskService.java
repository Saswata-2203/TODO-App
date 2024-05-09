package com.saswata.todolist.Service;

import com.saswata.todolist.DAO.TaskDao;
import com.saswata.todolist.DTO.TaskRequestDTO;
import com.saswata.todolist.DTO.TaskResponseDTO;
import com.saswata.todolist.Entity.TaskEntity;
import com.saswata.todolist.Utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public TaskResponseDTO addTask(TaskRequestDTO taskRequestDTO) {
        TaskEntity task = AppUtils.mapDTOToEntity(taskRequestDTO);
        taskDao.save(task);
        TaskResponseDTO taskResponseDTO = AppUtils.mapEntityToDTO(task);
        return taskResponseDTO;
    }

    public List<TaskResponseDTO> getAllTasks() {
        List<TaskEntity> taskEntityList = taskDao.findAll();
        return StreamSupport.stream(taskEntityList.spliterator(), false)
                .map(AppUtils::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskByName(String taskName) {
        TaskEntity taskEntity = taskDao.findByTaskName(taskName);
        return AppUtils.mapEntityToDTO(taskEntity);
    }

    public TaskResponseDTO updateTask(Integer taskId, TaskRequestDTO taskRequestDTO) {
        TaskEntity taskEntity = taskDao.findByTaskId(taskId);
        taskEntity.setTaskName(taskRequestDTO.getTaskName());
        taskEntity.setTaskDescription(taskRequestDTO.getTaskDescription());
        taskEntity.setTaskDueDate(taskRequestDTO.getTaskDueDate());
        taskDao.save(taskEntity);
        return AppUtils.mapEntityToDTO(taskEntity);
    }

    public void deleteTask(Integer taskId) {
        taskDao.deleteById(taskId);
    }
}
