package pl.sda.taskapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.taskapp.dto.TaskDto;
import pl.sda.taskapp.entity.Task;
import pl.sda.taskapp.mapper.TaskMapper;
import pl.sda.taskapp.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Deprecated
    private List<TaskDto> tasks = new ArrayList<>();
    private final TaskRepository taskRepository;
    private final SmsSenderService smsSenderService;

    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, SmsSenderService smsSenderService) {

        this.taskRepository = taskRepository;
        this.smsSenderService = smsSenderService;
        this.taskMapper = taskMapper;
    }

//        @PostConstruct //metoda wywołuje się po stworzeniu beana i może posłużyć do ustawienia jakiejś wartości
//        void init() {
//            Random random = new Random();
//            tasks.add(new TaskDto(1L, "wyniesc smieci", "najlepiej dzis"));
//            tasks.add(new TaskDto(2L, "kupic lody", "najlepiej dzis"));
//        }

    public void addTask(TaskDto taskDto) {

        var task = Task.builder()
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .build();
        taskRepository.save(task);
        smsSenderService.sendSms("Dodano nowe zadanie: " + task.getName() + ", opis zadania: " + task.getDescription());
    }

    public void showTasks() {
        System.out.println(tasks);
    }

    public List<TaskDto> getTasks() {

        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<TaskDto> findTask(Long taskId) {

        return tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst();
    }

    public void updateTask(Long taskId, TaskDto newTask) {

        taskRepository.findById(taskId)
                .ifPresent(oldTask -> {
                    oldTask.setName(newTask.getName());
                    oldTask.setDescription(newTask.getDescription());
                    taskRepository.save(oldTask);
                });
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto getTask(Long taskId) {

        return taskRepository.findById(taskId)
                .map(taskMapper::mapToDto)
                .orElse(null);
    }
}
