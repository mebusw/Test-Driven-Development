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
        def connectionCount = 0
        def connectionCounter = new SocketServent() {
            @Override
            void serve(Socket port) throws Exception {
                connectionCount++
            }
        }

        ss.serve 2000, connectionCounter
        connect 2000
        ss.close()

        assertEquals 1, connectionCount
    }

    void testManyConnection() {
        def ss = new SocketService()
        def connectionCount = 0
        def connectionCounter = new SocketServent() {
            @Override
            void serve(Socket port) throws Exception {
                connectionCount++
            }
        }

        ss.serve 2000, connectionCounter
        10.times {
            connect 2000
        }
        ss.close()

        assertEquals 10, connectionCount
    }

    void testSendMessage() {
        def ss = new SocketService()
        ss.serve 2000, new HelloServer()

        def socket = new Socket("localhost", 2000)
        def is = socket.getInputStream()
        def isr = new InputStreamReader(is)
        def br = new BufferedReader(isr)
        def answer = br.readLine()
        socket.close()
        ss.close()

        assertEquals "hello", answer


    }

    void connect(int port) {
        try {
            def socket = new Socket("localhost", port)
            Thread.sleep(100);
            socket.close()
        } catch (Exception e) {
            fail("could not connect")
        }
    }
}
