import java.io.*; 
import java.util.*; 
import java.net.*;
import java.lang.*;

/* 
 *  The program reads an input file "data.txt"  that has entries of the form 
 *  0	0.000000	I	536	98.190	92.170	92.170
 *  4	133.333330	P	152	98.190	92.170	92.170
 * 	1	33.333330	B	136	98.190	92.170	92.170
 *
 * The file is read line-by-line, values are parsed and assigned to variables,
 * values are  displayed, and then written to a file with name "output.txt"  
 */

class TrafficGenerator {  
	public static void main (String[] args) { 
		
		
		BufferedReader bis = null; 
		String currentLine = null; 
		PrintStream pout = null;
        //localhost address: 127.0.0.1
        String hostname = "localhost";
        int port = 4445;
        float t;
        int nPack;
        int l;
		try {  
			
			/*
			 * Open input file as a BufferedReader
			 */ 
            InetAddress addr = InetAddress.getByName(hostname);	
            t = 100;
            nPack = 100;
            l = 100;
            FileOutputStream fout =  new FileOutputStream("GeneratorOutput.txt");
	        pout = new PrintStream (fout);
            DatagramSocket socket=new DatagramSocket();
            long lastSendTime = System.nanoTime();
            int i = 0;
            long time_init = 0;
            int seqNo = 1;
            long j=0;
            long currentTime2;
			/*
			 * Open file for output 
			 */
			//FileOutputStream fout =  new FileOutputStream("output.txt");
			//pout = new PrintStream (fout);

			/*
			 *  Read file line-by-line until the end of the file 
			 */
            
			while (true) { 
                //DatagramSocket socket = new DatagramSocket();
                //long lastSendTime = System.nanoTime();
			    for (i=0; i< nPack; i++) {
			        /*
				    *  Add packet
				    */
		            byte[] buf=new byte[l];
			        DatagramPacket packet=new DatagramPacket(buf,l,addr,port);

			    	//calculate the time and wait until the corresponding time
			    	
			    	long currentTime = System.nanoTime();
                    long recordTime = currentTime - time_init;
                    if (seqNo == 1) {
                       recordTime = 0;
                    } 
			        time_init = currentTime;
			        //convert time to ms.
                    recordTime = recordTime/1000;
			        pout.println(seqNo+ "\t"+  recordTime + "\t" + l);
			        socket.send(packet);
			        seqNo++;
			    	
				}
                float waitTime = t * 1000 * 1000;
                long sendTime= lastSendTime + (long) waitTime;
                currentTime2 = System.nanoTime();
				while ( currentTime2 < sendTime ){
			    		currentTime2 = System.nanoTime();
			    }
			    	lastSendTime = currentTime2;  
			}
               
		} 

       catch (IOException e) {  
			// catch io errors from FileInputStream or readLine()  
			System.out.println("IOException: " + e.getMessage());  
		} finally {  
			// Close files   
			if (bis != null) { 
				try { 
					bis.close(); 
					pout.close();
				} catch (IOException e) { 
					System.out.println("IOException: " +  e.getMessage());  
				} 
			} 
		} 
	}  
}

