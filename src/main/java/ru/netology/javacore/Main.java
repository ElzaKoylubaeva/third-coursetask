package ru.netology.javacore;

import ru.netology.javacore.dao.Todos;
import ru.netology.javacore.server.TodoServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Todos todos = new Todos();
        TodoServer server = new TodoServer(8989, todos);
        server.start();
    }
}