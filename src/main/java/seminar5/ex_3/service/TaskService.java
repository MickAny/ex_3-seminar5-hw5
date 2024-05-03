package seminar5.ex_3.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import seminar5.ex_3.aspects.TrackUserAction;
import seminar5.ex_3.model.Task;
import seminar5.ex_3.model.TaskStatus;
import seminar5.ex_3.model.User;
import seminar5.ex_3.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Получение списка всех задач
     *
     * @return список задач
     */
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * Создание новой задачи
     *
     * @param task задача
     * @param user пользователь кому дать задачу
     */
    @TrackUserAction
    public void create(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    /**
     * Вставка новой задачи в базу данных
     *
     * @param title         имя задачи
     * @param description   текст задачи
     * @param id            ID задачи
     */
    @TrackUserAction
    public void taskInsert(String title, String description, int id) {
        taskRepository.addTask(title, description, id);
    }

    /**
     * Получение списка задач по названию
     *
     * @param title название задачи
     * @return      список задач
     */
    public List<Task> getByTitle(String title) {
        return taskRepository.getByTitle(title);
    }

    /**
     * Получение списка задач по статусу
     *
     * @param taskStatus статус задачи
     * @return          список задач
     */
    public List<Task> getByStatus(TaskStatus taskStatus) {
        return taskRepository.getByTaskStatus(taskStatus);
    }

    /**
     * Удаление задачу
     *
     * @param task задача
     */
    @TrackUserAction
    public void delTask(Task task) {
        taskRepository.delete(task);
    }

    /**
     * Удаление задачу по ID
     *
     * @param id ID задачи
     */
    @TrackUserAction
    public void delTask(int id) {
        taskRepository.delete(getById(id));
    }

    /**
     * Получение задачи по ID
     *
     * @param id    ID задачи
     * @return      задача
     */
    public Task getById(int id) {
        return taskRepository.getById(id);
    }

    /**
     * Обновление задачу
     *
     * @param task задача
     */
    @TrackUserAction
    public void updateTask(Task task) {
        taskRepository.updateTask(task.getTitle(), task.getText(), task.getTaskStatus(), task.getId());
    }

    /**
     * Обновление задачи по ограниченным параметрам
     *
     * @param id    ID задачи для обновления
     * @param title новое название
     * @param desc  новое описание
     */
    @TrackUserAction
    public void updateTask(int id, String title, String desc) {
        Task task = taskRepository.getById(id);
        if (task != null) {
            task.setTitle(title);
            task.setText(desc);
            taskRepository.save(task);
        } else {
            throw new RuntimeException("error");
        }
    }

    /**
     * Обновление статуса задачи по ID
     *
     * @param id         ID задачи
     * @param taskStatus новый статус задачи
     */
    public void updateStatusById(int id, TaskStatus taskStatus) {
        Task task = getById(id);
        task.setTaskStatus(taskStatus);
        updateTask(task);
    }

}
