package exercise.SMCRSocket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jacky on 15/10/5.
 */


class SocketService {
    private ServerSocket serverSocket = null;
    private Thread serverThread = null;
    private boolean run = true;
    private SocketServant itsServant;

    public void serve(int port, SocketServant servant) throws Exception {
        itsServant = servant;
        serverSocket = new ServerSocket(port);
        makeServerThread();
        serverThread.start();
    }

    private void makeServerThread() {
        serverThread = new Thread(() -> {
            while(run) {
                acceptAndServeConnection();
            }
        });
    }

    private void acceptAndServeConnection() {
        try {
            Socket s = serverSocket.accept();
            itsServant.serve(s);
            s.close();
        } catch (Exception e) {

        }
    }

    public void close() throws Exception {
        run = false;
        serverSocket.close();
    }
}
