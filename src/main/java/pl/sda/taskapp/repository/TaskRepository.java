package pl.sda.taskapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.taskapp.entity.Task;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> { }
