import java.io.*; 
import java.util.*; 
import java.net.*;

/* 
 *  The program reads an input file "data.txt"  that has entries of the form 
 *  0	0.000000	I	536	98.190	92.170	92.170
 *  4	133.333330	P	152	98.190	92.170	92.170
 * 	1	33.333330	B	136	98.190	92.170	92.170
 *
 * The file is read line-by-line, values are parsed and assigned to variables,
 * values are  displayed, and then written to a file with name "output.txt"  
 */

public class TrafficSink {
  public static void main(String[] args){
    PrintStream pout = null;
    
    //get ip address 
    //String receiver_hostname =  InetAddress.getLocalHost().getHostAddress();  
    //System.out.println("Running at: "+receiver_hostname);
    
    //set port to the same as the traffic generator
    int port = 4444;
    try {
        System.out.println(port);    
        DatagramSocket socket = new DatagramSocket(port);
        byte[] buf = new byte[2048];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("Waiting ..."); 
                
        FileOutputStream fout =  new FileOutputStream("TrafficSinkOutput2.txt");
	    pout = new PrintStream (fout);
			
	    int SeqNo = 1;
	    long last_receive_time = 0;
        while (true) {
            socket.receive(packet);

            //time to record is the current received time minus the last recieved packet time
            long got_packet_time = System.nanoTime();
            long time_to_record = got_packet_time - last_receive_time;
            if (SeqNo == 1) {
               time_to_record = 0;
            } 
            last_receive_time = got_packet_time;
            
            //unit of time: microseconds. 
            time_to_record = time_to_record/1000;
            System.out.println(SeqNo+ "\t"+  packet.getLength() + "\t" + time_to_record);
            pout.println(SeqNo+ "\t"+  packet.getLength() + "\t" + time_to_record); 
            SeqNo++;
        }
    }
    catch (IOException e) {
           System.out.println("IOException: " + e.getMessage());  
    }
    finally {
            pout.close();
    }
  }
}
