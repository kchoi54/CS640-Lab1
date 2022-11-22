import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class IperferServer {
    private int port; // port the host is receiving data
    
    /**
     * IperferServer constructor
     * Set class parameter.
     * @param port port the host is receiving data
     */
    public IperferServer(int port) {
        this.port = port;
    }

    /**
     * Receive TCP packets from client and calculates the network bandwidth
     */
    public void run() {
        //System.out.println("server mode    port: "+port);
        try {
            // server socket and output stream
            ServerSocket server = new ServerSocket(this.port);
            Socket socket = server.accept();
            InputStream in = socket.getInputStream();

            // create read buffer
            byte buffer[] = new byte[1000];
            long total_B_received = 0;

            // receive data
	    long n = 0;
            long start_time = System.currentTimeMillis();
            while((n = in.read(buffer)) >= 0) {
                total_B_received += n;
	    }
            long curr_time = System.currentTimeMillis();

            // close connection
            server.close();
            socket.close();

            // calculate bandwidth
            double rate = ((total_B_received*8.0)/1000.0) / (curr_time - start_time);
             
            // print summary
            System.out.printf("received=%dKB rate=%.3f Mbps\n", (total_B_received/1000), rate);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
