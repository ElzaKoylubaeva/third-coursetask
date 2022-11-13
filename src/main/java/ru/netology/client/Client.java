package ru.netology.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("Started");
        String host = "localhost";
        int port = 8989;
        List<String> data = List.of(
                "{ \"type\": \"ADD\", \"task\": \"Первая\" }",
                "{ \"type\": \"ADD\", \"task\": \"Вторая\" }",
                "{ \"type\": \"REMOVE\", \"task\": \"Первая\" }",
                "{ \"type\": \"ADD\", \"task\": \"Третья\" }",

                "{ \"type\": \"RESTORE\" }",
                "{ \"type\": \"RESTORE\" }"
        );
        for (String e : data) {

            try (Socket clientSocket = new Socket(host, port);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("send: " + e);
                out.println(e);

                String line = in.readLine();
                System.out.println("result: " + line);
            }
        }
        System.out.println("Finished");
    }
}
