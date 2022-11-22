import java.io.OutputStream;
import java.net.Socket;

public class IperferClient {
    private String host_name; // hostname or ip address of the server
    private int port; // port the host is receiving data
    private int time; // duration in seconds for which data should be generated

    /**
     * IperferClient constructor
     * Set class parameters.
     * @param host_name hostname or ip adrress of the server which receives data
     * @param port port the host is receiving data
     * @param time duration in seconds for which data should be generated
     */
    public IperferClient(String host_name, int port, int time) {
        this.host_name = host_name;
        this.port = port;
        this.time = time;
    }

    /**
     * Send TCP packets to host for given time and calculate the network bandwidth.
     */
    public void run() {
        try {
            // create socket and output stream
            Socket socket = new Socket(this.host_name, this.port);
            OutputStream out = socket.getOutputStream();

            // create data chunk
            byte data[] = new byte[1000];
            long total_KB_sent = 0;

            // send packet
            long curr_time = 0;
	    long start_time = System.currentTimeMillis();
            while(curr_time - start_time < this.time*1000) {
                out.write(data);
                total_KB_sent+=1;
		curr_time = System.currentTimeMillis();
            }

            // close connection
            socket.close();

            // calculate bandwidth
            double rate = (total_KB_sent*8.0) / (curr_time - start_time); 

            // print summary
            System.out.printf("sent=%dKB rate=%.3f Mbps\n", total_KB_sent, rate);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
