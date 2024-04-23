package seminar5.ex_3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import seminar5.ex_3.model.Task;
import seminar5.ex_3.model.TaskStatus;
import seminar5.ex_3.service.TaskService;
import seminar5.ex_3.service.UserService;

@Controller
@RequiredArgsConstructor
public class TasksController {

    private final UserService userService;

    private final TaskService taskService;

    /**
     * Получение главной страницы html
     *
     * @param model взаимодействие с шаблонизатором
     * @return      главная страница html
     */
    @GetMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "index.html";
    }

    /**
     * Изменение статуса задачи на IN_PROGRESS
     *
     * @param model взаимодействие с шаблонизатором
     * @param id    ID задачи
     * @return      главная страница html
     */
    @GetMapping("/turnTaskToInProgress/{id}")
    public String turnTaskToInProgress(Model model, @PathVariable("id") int id) {
        taskService.updateStatusById(id, TaskStatus.IN_PROGRESS);
        return getMainPage(model);
    }

    /**
     * Изменение статуса задачи на COMPLETED у задачи
     *
     * @param model взаимодействие с шаблонизатором
     * @param id    ID задачи
     * @return      главная страница html
     */
    @GetMapping("/turnTaskToCompleted/{id}")
    public String turnTaskToCompleted(Model model, @PathVariable("id") int id) {
        taskService.updateStatusById(id, TaskStatus.COMPLETED);
        return getMainPage(model);
    }

    /**
     * Изменение статуса задачи на NOT_STARTED у задачи
     *
     * @param model взаимодействие с шаблонизатором
     * @param id    ID задачи
     * @return      главная страница html
     */
    @GetMapping("/turnTaskToNotStarted/{id}")
    public String turnTaskToNotStarted(Model model, @PathVariable("id") int id) {
        taskService.updateStatusById(id, TaskStatus.NOT_STARTED);
        return getMainPage(model);
    }

    /**
     * Удаление задачи
     *
     * @param model взаимодействие с шаблонизатором
     * @param id    ID задачи
     * @return      главная страница html
     */
    @GetMapping("/deleteTask/{id}")
    public String deleteTask(Model model, @PathVariable("id") int id) {
        taskService.delTask(id);
        return getMainPage(model);
    }

    /**
     * Получение формы редактирования задачи
     *
     * @param task задача
     * @return      форма html для редактирования задачи
     */
    @GetMapping("/edit-task/{task}")
    public String getUpdateTaskForm(@PathVariable("task") Task task) {
        return "edit-task";
    }

    /**
     * Редактирование задачи в базе данных
     *
     * @param task принимаемая задача из редакции
     * @param id   ID задачи
     * @return      главная страница html
     */
    @PostMapping("/edit-task/{id}")
    public String updateTask(Task task, @PathVariable("id") int id) {
        taskService.updateTask(id, task.getTitle(), task.getText());
        return "redirect:/main";
    }

    /**
     * Получение формы создания задачи
     *
     * @param task  передаваемая задача
     * @param id    ID задачи
     * @param model связь с шаблонизатором
     * @return      возвращаем форму html для создания задачи
     */
    @GetMapping("/createTask/{id}")
    public String getCreateTaskForm(Task task, @PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        return "task-create";
    }

    /**
     * Создание новой задачи
     *
     * @param task  передаваемая задача из браузера
     * @param id    ID задачи
     * @param model связь с шаблонизатором
     * @return      редирект на главную страницу html
     */
    @PostMapping("/createTask/{id}")
    public String createTask(Task task, @PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        taskService.taskInsert(task.getTitle(), task.getText(), id);
        return "redirect:/main";
    }
}
