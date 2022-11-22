import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * CS640: Lab1
 * Iperfer
 * 
 * @author KJ Choi
 */
public class Iperfer {
    public static void main(String args[]) {
        boolean is_client_mode = false; // true for client mode, false for server mode
        int port = 0; // port should be a in range 1024 <= server port <= 65535
        String host_name = ""; // hostname or ip address of the server
        int time = 0; // time in seconds

        boolean invalid_argument = false; // exits if arguments are invalid
        // error if no argument given
        if(args.length<=0){
            invalid_argument = true;
        }

        // check for mode
        if(args[0].equals("-c") && args.length == 7) { // client mode, if "-c" and total 7 arguments are given
            is_client_mode = true;
        } else if (args[0].equals("-s") && args.length == 3) { // server mode, if "-s" and total 3 arguments are given
            is_client_mode = false;
        } else {
            invalid_argument = true;
        }

        // parse and validate arguments
        if(is_client_mode) {
            // parse host_name
            if(args[1].equals("-h")){ 
                host_name = args[2];

                // validate hostname using InetAddress getbyName()
                try {
                    // throw error if hostname is null
                    if(host_name.equals("")) {
                        throw new UnknownHostException();
                    }

                    // throw UnknownHostException if host could not be found
                    InetAddress ia = InetAddress.getByName(host_name);
                } catch (UnknownHostException e) {
                    System.err.println("Error: invalid hostname or ip");
                    System.exit(-1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                invalid_argument = true;
            }

            // parse port number
            if(args[3].equals("-p")){
                boolean nfe = false; // true if NumberFormatException
                try {
                    port = Integer.parseInt(args[4]);
                } catch (NumberFormatException e) {
                    nfe = true;
                }
                // error if port is not a number or not in range 1024 <= port <= 65535
                if(nfe || port < 1024 || port > 65535) {
                    System.err.println("Error: port number must be in the range 1024 to 65535");
                    System.exit(-1); 
                }
            } else {
                invalid_argument = true;
            }

            // parse time
            if(args[5].equals("-t")){
                boolean nfe = false; // true if NumberFormatException
                try {
                    time = Integer.parseInt(args[6]);
                } catch (NumberFormatException e) {
                    nfe = true;
                }

                if(nfe || time <= 0){ // error if time is less than or equal to zero
                    System.err.println("Error: time must be a number greater than zero");
                    System.exit(-1);
                }
            } else {
                invalid_argument = true;
            }
        } else {
            // parse port number
            if(args[1].equals("-p")){
                boolean nfe = false; // true if NumberFormatException
                try {
                    port = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    nfe = true;
                }
                // error if port is not a number or not in range 1024 <= port <= 65535
                if(nfe || port < 1024 || port > 65535) {
                    System.err.println("Error: port number must be in the range 1024 to 65535");
                    System.exit(-1); 
                }
            } else {
                invalid_argument = true;
            }
        }

        // exit if arguments invalid
        if(invalid_argument) {
            System.err.println("Error: invalid arguments");
            System.exit(-1);
        }

        if(is_client_mode) {
            // run client mode
            IperferClient client = new IperferClient(host_name, port, time);
            client.run();
        } else {
            // run server mode
            IperferServer server = new IperferServer(port);
            server.run();
        }
    }
}
