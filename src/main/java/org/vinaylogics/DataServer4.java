package org.vinaylogics;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class DataServer4{

    private static final Logger LOGGER = Logger.getLogger(DataServer4.class.getName());

    public static void main(String[] args) throws IOException {

        try(ServerSocket listener = new ServerSocket(9090);
            Socket socket = listener.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
            LOGGER.info(() -> "Got new connection: " + socket);
            IntStream.range(1, 50).forEach(x -> extracted(x, out));
        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    private static void extracted(int x,PrintWriter out) {
        int key = (x%2) + 1;
        String s = key +","+ x;
        LOGGER.info(s);
        out.println(s);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }
}
