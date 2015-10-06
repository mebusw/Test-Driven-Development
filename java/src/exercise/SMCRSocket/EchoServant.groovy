package exercise.SMCRSocket

/**
 * Created by jacky on 15/10/5.
 */
class EchoServant extends SocketServant {
    @Override
    void serve(Socket s) throws Exception {
        def token = getBufferReader(s).readLine()
        System.out.println(token)
        getPrintStream(s).println(token)
    }

}
