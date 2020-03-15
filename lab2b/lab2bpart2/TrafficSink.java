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
    
    //set port to the same as the traffic generator
    try {
        //port number: 4446, temporarily used 4446 due to 4444 is temporarily used.
        System.out.println(4446);    
        DatagramSocket socket = new DatagramSocket(4446);
        byte[] buf = new byte[2048];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("Waiting to connect..."); 
                
        FileOutputStream fout =  new FileOutputStream("max_TrafficSinkOutput.txt");
	    pout = new PrintStream (fout);
			
	    int SeqNo = 1;
	    long lastTime = 0;
        while (true) {
            socket.receive(packet);

            //time to record is the current received time minus the last recieved packet time
            long packetTime = System.nanoTime();
            long recordedTime = packetTime - lastTime;
            if (SeqNo == 1) {
               recordedTime = 0;
            } 
            lastTime = packetTime;
            
            //Used system.out.println to see the output in the terminal. 
            System.out.println(SeqNo+ "\t"+  packet.getLength() + "\t" + recordedTime / 1000);
            //Used pout to see the output in the file
            pout.println(SeqNo+ "\t"+  packet.getLength() + "\t" + recordedTime / 1000); 
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
