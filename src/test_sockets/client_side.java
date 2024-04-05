package test_sockets;
import java.net.*;
import java.io.*;

public class client_side {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 4444);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String userInput;
        String serverResponse;

        // Read input from the user and send it to the server
        while (true) {
            // Read user input
            System.out.print("Enter message (type 'stop' to end): ");
            userInput = br.readLine();

            // Send user input to server
            dout.writeUTF(userInput);
            dout.flush();

            // Terminate loop if user inputs "stop"
            if (userInput.equals("stop")) {
                break;
            }

            // Read server response
            serverResponse = din.readUTF();
            System.out.println("Server Says: " + serverResponse);
        }

        // Close streams and socket
        dout.close();
        din.close();
        br.close();
        s.close();
    }
}

