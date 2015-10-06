package exercise.smcrClient;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by jacky on 15/10/6.
 */
public class SMCRClient {
    private String itsFilename;
    private long itsFileLength;
    private BufferedReader fileReader;
    private BufferedReader is;
    private PrintWriter os;

    public boolean parseCommandLine(List<String> args) {
        try {
            itsFilename = args.get(0);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public String filename() {
        return itsFilename;
    }

    public void setFilename(String filename) {
        itsFilename = filename;
    }

    public boolean prepareFile() {
        boolean filePrepared = false;
        File f = new File(itsFilename);

        if (f.exists()) {
            try {
                itsFileLength = f.length();
                fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                filePrepared = true;
            } catch (Exception e) {
                filePrepared = false;
                e.printStackTrace();
            }
        }
        return filePrepared;
    }

    public long getFileLength() {
        return itsFileLength;
    }

    public boolean connect() {
        boolean connectionStatus = false;
        try {
            Socket s = new Socket("localhost", 3000);
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            String headerLine = is.readLine();
            connectionStatus = (headerLine != null);//headerLine != null && headerLine.startsWith("SMCR");
        } catch (Exception e) {
            e.printStackTrace();
            connectionStatus = false;

        }
        return connectionStatus;
    }

    public boolean sendFile() {
        boolean fileSent = false;
        try {
            writeSendFileCommand();
            fileSent = true;
        } catch (Exception e) {
            fileSent = false;
        }
        return fileSent;
    }

    private void writeSendFileCommand() throws Exception {
        os.println("Sending");
        os.println(itsFilename);
        os.println(itsFileLength);
        char buffer[] = new char[(int) itsFileLength];
        fileReader.read(buffer);
        os.write(buffer);
        os.flush();

    }
}
