package exercise.SMCRSocket

/**
 * Created by jacky on 15/10/5.
 */
class HelloServer implements SocketServent {
    @Override
    void serve(Socket s) throws Exception {
        def ps = new PrintStream(s.getOutputStream())
        ps.println("hello")

    }
}
