import java.io.*;
import java.net.*;
import java.util.Properties;


/*
 *   Usage :: java -cp . UDPSend "hello java"
 */

public class UDPSend {

    public static void main(String args[]) {

        try {
            if (args.length != 1) throw new IllegalArgumentException("Need message as parameter");

            Properties p = new Properties();
            try { p.load(new FileInputStream("UDPSend.properties") ); }
            catch (Exception e) {}

            String host = p.getProperty("host");
            int port = Integer.parseInt(p.getProperty("port") );

            byte[] message;

            String msg = args[0];

            message = msg.getBytes();

            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);

            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            dsocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }

    }

}