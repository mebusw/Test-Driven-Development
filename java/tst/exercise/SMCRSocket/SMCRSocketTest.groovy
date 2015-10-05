package exercise.SMCRSocket

/**
 * Created by jacky on 15/10/5.
 */
class SMCRSocketTest extends groovy.util.GroovyTestCase {
    void setUp() {
        super.setUp()

    }

    void testOneConnection() {
        def ss = new SocketService()
        ss.serve 2000
        connect 2000
        ss.close()
        assertEquals 1, ss.connections()
    }

    void connect(int port) {
        try {
            def socket = new Socket("localhost", port)
            Thread.sleep(200);
            socket.close()
        } catch (Exception e) {
            fail("could not connect")
        }
    }
}
