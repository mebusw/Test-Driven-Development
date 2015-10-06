package exercise.smcrClient

import exercise.SMCRSocket.SocketServant
import exercise.SMCRSocket.SocketService

/**
 * Created by jacky on 15/10/6.
 */
class SMCRClientTest extends GroovyTestCase {
    private SMCRClient c
    private SocketService smc

    void setUp() {
        c = new SMCRClient()
    }

    void tearDown() {
        try {
            smc.close()
        } catch (Exception e) {

        }
    }

    void testParseCommandLine() {
        c.parseCommandLine(["filename"])
        assertEquals "filename", c.filename()
    }

    void testInvalidParseCommandLine() {
        def result = c.parseCommandLine([])
        assertFalse result
    }

    void testFileDoesNotExist() {
        c.setFilename("notExist")
        def prepared = c.prepareFile()
        assertFalse prepared
    }

    void testCountBytesInFile() {
        def f = createTestFile("testfile", "some text")

        c.setFilename("testfile")
        def prepared = c.prepareFile()
        f.delete()

        assertTrue prepared
        assertEquals 9, c.getFileLength()
    }


    void testSendFile() {
        def f = createTestFile("testSendFile", "I'm sending this file")
        def servant = new SocketServant() {
            public boolean fileReceived = false;
            public String filename = "noname";
            public long fileLength = -1;
            private PrintWriter os;
            private BufferedReader is;
            public char[] content;

            @Override
            void serve(Socket s) throws Exception {
                try {
                    is = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    os = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                    os.println("SMCR test server");
                    os.flush();
                    parse(is.readLine());

                    s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            void parse(String cmd) {
                if (cmd != null) {
                    if (cmd.equals("Sending")) {
                        filename = is.readLine();
                        fileLength = Long.parseLong(is.readLine());
                        content = new char[(int) fileLength];
                        is.read(content, 0, (int) fileLength);
                        fileReceived = true;
                    }
                }

            }

        }

        smc = new SocketService()
        smc.serve(3000, servant)

        c.setFilename("testSendFile")

        assertTrue(c.connect())
        assertTrue(c.prepareFile())
        assertTrue(c.sendFile())


        Thread.sleep(50)
        assertTrue servant.fileReceived
        assertEquals "testSendFile", servant.filename
        assertEquals 21, servant.fileLength
        assertEquals "I'm sending this file", new String(servant.content)

        f.delete()
        this.smc.close()
    }


    private File createTestFile(String filename, String content) {
        def f = new File(filename)
        def stream = new FileOutputStream(f)
        stream.write(content.getBytes())
        stream.close()
        return f
    }


}