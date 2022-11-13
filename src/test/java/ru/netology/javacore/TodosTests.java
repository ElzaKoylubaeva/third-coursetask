package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.javacore.dao.Todos;

import java.util.ArrayList;
import java.util.List;

public class TodosTests {

    private Todos todos;

    @BeforeEach
    public void object() {
        todos = new Todos();
    }

    // проверить, что задача добавилась
    @Test
    void test_taskAdd_when_no_data_then_should_added() {
        String todoExpected = "Go for a run";

        todos.addTask(todoExpected);

        List<String> tasks = todos.getTasks();
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertEquals(todoExpected, tasks.get(0));
    }

    // проверить что при добавлении 8 задачи, она не добавится
    @Test
    void test_addTask_when_add_8th_task_should_not_add_task() {
        List<String> todosExpected = new ArrayList<>();
        for (int i = 0; i < Todos.MAX_NUMBER_TASK; i++) {
            todosExpected.add(String.valueOf(i));
        }

        for (int i = 0; i < Todos.MAX_NUMBER_TASK + 1; i++) {
            todos.addTask(String.valueOf(i));
        }

        List<String> tasks = todos.getTasks();
        Assertions.assertEquals(7, tasks.size());
        Assertions.assertEquals(todosExpected, tasks);
    }

    // проверить, что существующая задача удаляется
    @Test
    void test_removeTask_when_added_task_should_be_removed() {
        // что дано/ инициализация
        String todo = "Go for a run";
        todos.addTask(todo);
        // вызов проверяемого метода
        todos.removeTask(todo);
        // проверки
        Assertions.assertEquals(0, todos.getTasks().size());
    }

    // проверить, что удаление не существующей задачи не вызывает ошибок
    @Test
    void test_removeTask_when_not_existed_task_is_tried_to_remove() {
        todos.addTask("Go for a walk");
        String notAddedTask = "Go for a run";
        todos.removeTask(notAddedTask);

        Assertions.assertEquals(1, todos.getTasks().size());
    }

    // проверить, что получение всех задач возвращает строку в алфавитном порядке через разделитель
    @Test
    void test_getAllTasks_return_String_using_the_delimiter() {
        List<String> todosExpected = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            todosExpected.add(String.valueOf(i));
        }

        for (int i = 5; i >= 0; i--) {
            todos.addTask(String.valueOf(i));
        }

        Assertions.assertEquals(String.join(" ", todosExpected), todos.getAllTasks());
    }
}
