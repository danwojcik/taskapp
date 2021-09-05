package pl.sda.taskapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.taskapp.dto.TaskDto;
import pl.sda.taskapp.service.TaskService;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController //dziedziczy po component, tworzy beana dla rest controllera
@RequestMapping("/v1/task") //adres pod którym będzie dostępny zasób
public class TaskController {

    private TaskService taskService;

//    @Autowired //wstrzyknięcie przez konstruktor
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }

    @Autowired //wstrzyknięcie przez setter
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping //metoda GET protokołu HHTP
    public List<TaskDto> getTasks() {
        return taskService.getTasks();
    }
    
    @PostMapping //metoda POST protokołu HTTP
    public void addTask(@RequestBody TaskDto taskDto) {
        taskService.addTask(taskDto);
    }

    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDto taskDto) {
        taskService.updateTask(taskId, taskDto);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/{taskId}")
    public TaskDto getTask(@PathVariable("taskId") Long taskId) {
        return taskService.getTask(taskId);
    }
}
