package ru.netology.javacore.server;

import ru.netology.javacore.configuration.ConfigApplication;
import ru.netology.javacore.dao.Todos;
import ru.netology.javacore.logger.Logger;
import ru.netology.javacore.model.Query;
import ru.netology.javacore.parser.GsonParser;
import ru.netology.javacore.service.TaskService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

    private final int port;
    private final Logger logger;
    private final TaskService taskService;
    private final GsonParser gsonParser;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        ConfigApplication configApplication = new ConfigApplication(todos);
        this.taskService = new TaskService(configApplication);
        this.logger = configApplication.getLogger();
        this.gsonParser = GsonParser.getInstance();
    }

    public void start() throws IOException {
        logger.log("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) { // открываем серверный сокет
            logger.log("Server started");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(
                             socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                    // get request from client
                    Query query = gsonParser.parseJsonToQuery(in.readLine());
                    logger.log("received query: " + query);

                    // do job
                    taskService.runTask(query);

                    // send response
                    String taskResult = taskService.getTaskResult();
                    logger.log("send response: " + taskResult);
                    out.println(taskResult);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
