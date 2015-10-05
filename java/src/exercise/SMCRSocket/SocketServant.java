package exercise.SMCRSocket;

import java.io.*;
import java.net.Socket;

/**
 * Created by jacky on 15/10/5.
 */
public class SocketServant {
    void serve(Socket port) throws Exception {
    }

    protected OutputStream getPrintStream(Socket s) throws Exception {
        OutputStream os = s.getOutputStream();
        return new PrintStream(os);
    }

    protected BufferedReader getBufferReader(Socket s) throws Exception {
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        return new BufferedReader(isr);

    }

}
