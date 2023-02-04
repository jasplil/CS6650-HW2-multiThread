import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class creates a thread using runnable interface
 */
public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
    }

    // Read data from client
    @Override
    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            // get the output stream of client
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // get the input stream of client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                // print the received message from client
                System.out.printf(" Sent from the client: %s\n", line);
                out.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
