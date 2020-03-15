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


class TrafficGenerator_3 {  
	public static void main (String[] args) { 
		BufferedReader bis = null; 
		String currentLine = null; 
		String hostname = "127.0.0.1";
		int port = 4445;
		
		try {  
			//get host name from the internet
			//hostname = args[0];
			InetAddress addr = InetAddress.getByName(hostname);	
			
			//read input and create output file
            File fin = new File("movietrace.data"); 
			FileReader fis = new FileReader(fin);  
			bis = new BufferedReader(fis);  
			
			//socket to send on
			DatagramSocket socket = new DatagramSocket();

			//intial varibales
			int i = 0;
			int max_packet_size = 65507;
			long lastTime = System.nanoTime();
			
			float Etime = 33;  //33 microseconds
			long waitTime = (long) Etime*1000000; 

			while ( (currentLine = bis.readLine()) != null) { 
				//Parse line and break up into elements
				StringTokenizer st = new StringTokenizer(currentLine); 
				String col1 = st.nextToken(); 
				String col2 = st.nextToken(); 
				String col3  = st.nextToken(); 
				String col4  = st.nextToken(); 
				
				//Convert each element to desired data type 
				int SeqNo 	= Integer.parseInt(col1);
				float t2 	= Float.parseFloat(col2);  
				int Fsize 	= Integer.parseInt(col4);
				
				//calculate the time and wait until the corresponding time
			    long sendTime = lastTime + waitTime;
			    long currentTime = System.nanoTime();
			    while ( currentTime < sendTime ){
			    	currentTime = System.nanoTime();
			    }
			    lastTime = currentTime;

				//send the frame as packets up to 65507. 
				while (Fsize > 0) {
					int currentSize = Fsize % max_packet_size;
					if (currentSize == 0 ) {
						currentSize = max_packet_size;
					}
					byte [] data = new byte[currentSize];
					DatagramPacket packet = new DatagramPacket(data, currentSize, addr, port);
					socket.send(packet);
					Fsize = Fsize - currentSize;
				}
			} 
		} catch (IOException e) {  
			System.out.println("IOException: " + e.getMessage());  
		} finally {  
			if (bis != null) { 
				try { 
					bis.close(); 
				} catch (IOException e) { 
					System.out.println("IOException: " +  e.getMessage());  
				} 
			} 
		}
		 
	}  

}

