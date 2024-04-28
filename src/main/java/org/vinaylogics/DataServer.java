package org.vinaylogics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class DataServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(9090);
             Socket socket = listener.accept()) {

            System.out.println("Got new connection: " + socket.toString());
            String filePath = Objects.requireNonNull(DataServer.class.getClassLoader().getResource("avg")).getPath();


            try( BufferedReader br = new BufferedReader(new FileReader(filePath));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String line;
                while ((line = br.readLine()) != null) {

                    out.println(line);
                    Thread.sleep(50);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
