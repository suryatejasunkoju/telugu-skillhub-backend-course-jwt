package com.teluguskillhub.springsecurity.Controller;

import com.teluguskillhub.springsecurity.Payload.Request.TaskRequest;
import com.teluguskillhub.springsecurity.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService taskService;

    //save task
    @PostMapping("/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskRequest saveTask(@PathVariable(name = "userId") Long userId, @RequestBody TaskRequest taskRequest){
        TaskRequest taskRequest1 = taskService.saveTask(userId, taskRequest);
        return taskRequest1;
    }
    //get all tasks
    @GetMapping("/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskRequest> getAllTasks(@PathVariable(name = "userId") Long id){
        return taskService.getAllTasks(id);
    }
    //get individual tasks
    @GetMapping("/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskRequest getTask(@PathVariable("userId")Long userId, @PathVariable("taskId") Long taskId){
        return taskService.getTask(userId, taskId);
    }

    //delete individual tasks
    @DeleteMapping("/{taskId}/tasks/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTask(@PathVariable("taskId")Long taskId, @PathVariable("userId") Long userId){
        taskService.deleteTask(taskId, userId);
        return "Task deleted Successfully !!!";
    }
}
