package com.teluguskillhub.springsecurity.Service;

import com.teluguskillhub.springsecurity.Payload.Request.TaskRequest;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface TaskService {
    public TaskRequest saveTask(Long userId, TaskRequest taskRequest);
    public List<TaskRequest> getAllTasks(Long userId);
    public TaskRequest getTask(Long userId, Long taskId);
    public void deleteTask(Long userId, Long taskId);
}
