package com.teluguskillhub.springsecurity.ServiceImpl;

import com.teluguskillhub.springsecurity.Entities.Task;
import com.teluguskillhub.springsecurity.Entities.User;
import com.teluguskillhub.springsecurity.Exception.TaskNotFoundException;
import com.teluguskillhub.springsecurity.Exception.TaskUserAssociationException;
import com.teluguskillhub.springsecurity.Exception.UserNotFoundException;
import com.teluguskillhub.springsecurity.Payload.Request.TaskRequest;
import com.teluguskillhub.springsecurity.Repository.TaskRepository;
import com.teluguskillhub.springsecurity.Repository.UserRepository;
import com.teluguskillhub.springsecurity.Service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public TaskRequest saveTask(Long userId, TaskRequest taskRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException(String.format("User with %d not found", userId))
        );
        Task task = modelMapper.map(taskRequest, Task.class);
        //setting user for that task, before storing in DB.
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskRequest.class);
    }

    @Override
    public List<TaskRequest> getAllTasks(Long userId) {
        userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException(String.format("User with %d not found", userId))
        );
        List<Task> taskList = taskRepository.findAllByUserId(userId);
        return taskList
                .stream()
                .map(
                        task -> modelMapper.map(task,TaskRequest.class)
                )
                .toList();
    }

    @Override
    public TaskRequest getTask(Long userId, Long taskId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException(String.format("User not found with %d", userId))
        );
        Task task = taskRepository.findById(taskId).orElseThrow(
                ()->new TaskNotFoundException(String.format("Task with %d not found", taskId))
        );

        //Throwing exception when particular task is not associated with passed user
        if(userId != task.getUser().getId()){
            throw new TaskUserAssociationException(String.format("Task with id:%d is not associated with User with id:", taskId, userId));
        }
        return modelMapper.map(task, TaskRequest.class);
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException(String.format("User not found with %d", userId))
        );
        Task task = taskRepository.findById(taskId).orElseThrow(
                ()->new TaskNotFoundException(String.format("Task with %d not found", taskId))
        );

        //Throwing exception when particular task is not associated with passed user
        if(userId != task.getUser().getId()){
            throw new TaskUserAssociationException(String.format("Task with id:%d is not associated with User with id:%d", taskId, userId));
        }
        taskRepository.deleteById(taskId);
    }
}
