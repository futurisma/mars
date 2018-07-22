import java.io.*;
import java.net.*;
import java.util.*;

public class UDPReceiver {

    public UDPReceiver() {
      try {
        
        byte[] buffer = new byte[2048];

        java.util.Properties p = new java.util.Properties();
        try { p.load(new FileInputStream("UDPReceiver.properties") ); }
        catch (Exception e) {}

        int port = Integer.parseInt(p.getProperty("port") );
        DatagramSocket dsocket = new DatagramSocket(port);

        for (; ; ) {
          DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
          dsocket.receive(packet);

          String msg = new String(buffer, 0, packet.getLength());
          checkMsg(msg);
          System.out.println(packet.getAddress().getHostName() + " : " + msg);
        }

      } catch (Exception e) {
        System.err.println(e);
    }

  }

  void checkMsg (String s) {
    try {
      // check s.length() < 255
      java.util.StringTokenizer st = new java.util.StringTokenizer(s);
      System.out.println("checkMsg :: " + st.countTokens() + " length " + s.length() );

      while(st.hasMoreTokens() ) { System.out.println("checkMsg :: " + st.nextToken() ); } 

    } catch (Exception e) {
        System.err.println(e);
    }  
  }

  void storeLoc (double x, double y, String fname) {
      System.out.println("storeLoc :: ");
  }

  // fname = location.properties
  void getLoc (String fname) {
    try {
        System.out.println("getLoc :: " + fname );
        java.util.Properties l = new java.util.Properties();
        l.load(new FileInputStream(fname) );

        double x = Double.parseDouble(l.getProperty("x") );
        double y = Double.parseDouble(l.getProperty("y") );

    } catch (Exception e) {
        System.err.println(e);
    }
    
  }

  public static void main(String args[]) {

   UDPReceiver uget = new UDPReceiver();


  }
}

