package exercise.SMCRSocket;

import java.net.Socket;

/**
 * Created by jacky on 15/10/5.
 */
public interface SocketServant {
    void serve(Socket port) throws Exception;
}
