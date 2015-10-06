package exercise.SMCRSocket;

import groovy.transform.Synchronized;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by jacky on 15/10/5.
 */


public class SocketService {
    private ServerSocket serverSocket = null;
    private Thread serverThread = null;
    private boolean run = true;
    private SocketServant itsServant;
    private SynchronousQueue serverThreads = new SynchronousQueue();


    class ServiceRunnable implements Runnable {
        private Socket itsSocket;

        ServiceRunnable(Socket socket) {
            itsSocket = socket;
        }

        @Override
        public void run() {
            try {
                itsServant.serve(itsSocket);
                serverThreads.remove(Thread.currentThread());
                itsSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void serve(int port, SocketServant servant) throws Exception {
        itsServant = servant;
        serverSocket = new ServerSocket(port);
        makeServerThread();
        serverThread.start();

        new Thread(() -> {
            System.out.println("another");
        }).start();

    }

    private void makeServerThread() {
        serverThread = new Thread(() -> {
            while (run) {
                acceptAndServeConnection();
            }
        });
    }

    private void acceptAndServeConnection() {
        try {
            Socket s = serverSocket.accept();
            Thread t = new Thread(new ServiceRunnable(s));
            t.start();
            serverThreads.add(t);
        } catch (Exception e) {

        }
    }

    public void close() throws Exception {
        run = false;

        serverThread.join(1000);
        serverSocket.close();
        while (serverThreads.size() > 0) {
            Thread t = (Thread) serverThreads.take();
            serverThreads.remove(t);
            t.join();

        }

    }
}
