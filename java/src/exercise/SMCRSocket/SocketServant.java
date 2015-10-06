package exercise.SMCRSocket;

import java.io.*;
import java.net.Socket;

/**
 * Created by jacky on 15/10/5.
 */
public class SocketServant {
    public void serve(Socket s) throws Exception {
        throw new Exception("Not implemented");
    }

    public static OutputStream getPrintStream(Socket s) throws Exception {
        OutputStream os = s.getOutputStream();
        return new PrintStream(os);
    }

    public static BufferedReader getBufferReader(Socket s) throws Exception {
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        return new BufferedReader(isr);

    }

}
