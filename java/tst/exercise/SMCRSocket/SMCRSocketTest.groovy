package exercise.SMCRSocket

/**
 * Created by jacky on 15/10/5.
 */
class SMCRSocketTest extends groovy.util.GroovyTestCase {
    private ss

    void setUp() {
        super.setUp()
        ss = new SocketService()
    }

    void tearDown() {
        ss.close()

    }
    void testOneConnection() {
        def connectionCount = 0
        def connectionCounter = new SocketServant() {
            @Override
            void serve(Socket s) throws Exception {
                connectionCount++
            }
        }

        this.ss.serve 2000, connectionCounter
        connect 2000

        assertEquals 1, connectionCount
    }

    void testManyConnection() {
        def connectionCount = 0
        def connectionCounter = new SocketServant() {
            @Override
            void serve(Socket port) throws Exception {
                connectionCount++
            }
        }

        ss.serve 2000, connectionCounter
        10.times {
            connect 2000
        }

        assertEquals 10, connectionCount
    }

    void testSendMessage() {
        ss.serve 2000, new HelloServant()

        def socket = new Socket("localhost", 2000)
        def is = socket.getInputStream()
        def isr = new InputStreamReader(is)
        def br = new BufferedReader(isr)
        def answer = br.readLine()
        socket.close()

        assertEquals "hello", answer
    }

    void testReceiveMessage() {
        ss.serve 2000, new EchoServant()

        def socket = new Socket("localhost", 2000)
        def is = socket.getInputStream()
        def isr = new InputStreamReader(is)
        def br = new BufferedReader(isr)
        def os = socket.getOutputStream()
        def ps = new PrintStream(os)

        ps.println "myMsg"

        def answer = br.readLine()
        socket.close()

        assertEquals "myMsg", answer
    }

    void testMultiThreaded(){
        ss.serve 2000, new EchoServant()

        def s1 = new Socket("localhost", 2000)
        def br1 = SocketServant.getBufferReader(s1)
        def ps1 = SocketServant.getPrintStream(s1)

        def s2 = new Socket("localhost", 2000)
        def br2 = SocketServant.getBufferReader(s2)
        def ps2 = SocketServant.getPrintStream(s2)

        ps2.println("msg2")
        def answer2 = br2.readLine()
        s2.close()

        ps1.println("msg1")
        def answer1 = br1.readLine()
        s1.close()

        assertEquals "msg1", answer1
        assertEquals "msg2", answer2

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
