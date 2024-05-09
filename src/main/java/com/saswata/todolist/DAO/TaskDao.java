package com.saswata.todolist.DAO;

import com.saswata.todolist.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<TaskEntity,Integer> {
    TaskEntity findByTaskName(String taskName);

    TaskEntity findByTaskId(Integer taskId);
}
