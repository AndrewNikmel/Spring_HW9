package Spring_HW.sem9.tasks.repository;

import Spring_HW.sem9.tasks.model.Task;
import Spring_HW.sem9.tasks.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TaskRepository  extends JpaRepository<Task, Long> {
    //Достанем все задачи по статусу
    List<Task> getTaskByStatus(TaskStatus status);
    //Измененная функция обновления данных по айди
    @Modifying
    @Query("update Task f set f.shortDesc = ?1, f.description = ?2, f.status = ?3 where f.id = ?4")
    void updateTaskById(String shortDesc, String description, TaskStatus status, Long id);
}