package test_sockets;
import java.net.* ;
import java.io.* ;

public class server_side {
   
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        DataInputStream din = null;
        DataOutputStream dout = null;
        BufferedReader br = null;
        
        try {
            ss = new ServerSocket(4444);
            s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
            
            String clientMessage;
            String serverResponse;

            // Read input from the client and send response
            while (true) {
                // Read client message
                clientMessage = din.readUTF();
                System.out.println("Client Says: " + clientMessage);

                // Terminate loop if client sends "stop"
                if (clientMessage.equals("stop")) {
                    break;
                }

                // Read server response
                System.out.print("Enter response: ");
                serverResponse = br.readLine();

                // Send response to client
                dout.writeUTF(serverResponse);
                dout.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close streams and socket
                if (din != null)
                    din.close();
                if (dout != null)
                    dout.close();
                if (br != null)
                    br.close();
                if (s != null)
                    s.close();
                if (ss != null)
                    ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

