package seminar5.ex_3.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seminar5.ex_3.model.Task;
import seminar5.ex_3.model.TaskStatus;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Поиск задач по названию
     *
     * @param title назание задачи
     * @return Список задач с одинаковыми названиями
     */
    List<Task> getByTitle(String title);

    /**
     * Получение задач по статусу выполнения
     *
     * @param taskStatus статус задачи
     * @return список задач с одинаковым статусом
     */
    List<Task> getByTaskStatus(TaskStatus taskStatus);

    /**
     * Получение задач по ID
     *
     * @param id ID задачи
     * @return задача
     */
    Task getById(int id);

    /**
     * Кастомный запрос на изменение существующей задачи
     *
     * @param title       новое название задачи
     * @param description новое описание задачи
     * @param taskStatus  новый статус задачи
     * @param id          ID задачи
     */
    @Modifying
    @Transactional
    @Query("UPDATE tasks SET title=:title, text=:description, taskStatus=:taskStatus WHERE id=:id")
    void updateTask(String title, String description, TaskStatus taskStatus, int id);

    /**
     * Кастомный запрос на создание новой задачи
     *
     * @param title       Название задачи
     * @param description опиание задачи
     * @param id          ID задачи
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks VALUES (DEFAULT, :title, :description, 'NOT_STARTED', :id)", nativeQuery = true)
    void addTask(@Param("title") String title, @Param("description") String description, @Param("id") int id);

}
