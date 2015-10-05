package exercise.SMCRSocket

/**
 * Created by jacky on 15/10/5.
 */
class HelloServant extends SocketServant {
    @Override
    void serve(Socket s) throws Exception {
        def ps = getPrintStream(s)
        ps.println("hello")

    }
}
