package pl.sda.taskapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.taskapp.dto.TaskDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //oznacza, że jest to test, który będzie używał beanów springów
class TaskServiceTest {

    @Autowired //anotacja ta oznacza, że wtrzykujemy bean springa
    //jest to wstrzykcięcie przez pole
    private TaskService taskService;

    @Test //wstrzyknięcie przez pole
    void shouldSveTaskInService() {

        //given
        var task = new TaskDto(); //od JAVA 11 można używać 'var' zamiast używać nazwy klasy
        task.setId(1L);
        task.setName("zostań programistą");
        task.setDescription("muszę dużo się uczyć");

        //when
        taskService.addTask(task);

        //then
        var savedTasks = taskService.getTasks();
        assertEquals(1, savedTasks.size());
        assertEquals(1L, savedTasks.get(0).getId());
        assertEquals("zostań programistą", savedTasks.get(0).getName());
        assertEquals("muszę dużo się uczyć", savedTasks.get(0).getDescription());
    }
}