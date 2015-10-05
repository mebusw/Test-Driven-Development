package exercise.SMCRSocket;

import java.net.Socket;

/**
 * Created by jacky on 15/10/5.
 */
public interface SocketServent {
    void serve(Socket port) throws Exception;
}
