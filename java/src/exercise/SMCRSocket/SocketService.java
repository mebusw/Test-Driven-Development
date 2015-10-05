package exercise.SMCRSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jacky on 15/10/5.
 */
class SocketService {
    private ServerSocket serverSocket = null;
    private int count = 0;
    private Thread serverThread = null;

    public void serve(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        serverThread = new Thread(() -> {
            try {
                Thread.sleep(200);
                Socket s = serverSocket.accept();
                s.close();
                count++;
            } catch (Exception e) {

            }

        });
        serverThread.start();
    }

    public void close() throws Exception {
        serverSocket.close();
    }

    public int connections() {
        return count;
    }
}
