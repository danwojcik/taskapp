package pl.sda.taskapp.mapper;

import org.springframework.stereotype.Service;
import pl.sda.taskapp.dto.TaskDto;
import pl.sda.taskapp.entity.Task;

@Service
public class TaskMapper {

    public TaskDto mapToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .build();
    }

    public Task mapToTask(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .build();
    }
}
