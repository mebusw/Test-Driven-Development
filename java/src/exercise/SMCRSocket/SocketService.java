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
    private boolean run = true;
    private SocketServent itsServent;

    public void serve(int port, SocketServent servent) throws Exception {
        itsServent = servent;
        serverSocket = new ServerSocket(port);
        serverThread = new Thread(() -> {
            while(run) {
                try {
                    Socket s = serverSocket.accept();
                    itsServent.serve(s);
                    s.close();
                } catch (Exception e) {

                }
            }
        });
        serverThread.start();
    }

    public void close() throws Exception {
        run = false;
        serverSocket.close();
    }

    public int connections() {
        return count;
    }
}
