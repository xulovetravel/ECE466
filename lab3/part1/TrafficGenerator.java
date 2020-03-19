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
		//PrintStream pout = null;
        //localhost address: 127.0.0.1
        String hostname = "localhost";
        ArrayList<DatagramPacket> frames = new ArrayList<DatagramPacket>();
        long[] waitTime = new long[125000]; 
		int port = 4445;
		int rate = Integer.parseInt(args[0]);
		try {  
			
			/*
			 * Open input file as a BufferedReader
			 */ 

            InetAddress addr = InetAddress.getByName(hostname);	
			File fin = new File("poisson3.data"); 
			FileReader fis = new FileReader(fin);  
			bis = new BufferedReader(fis);  
            int i = 0;
            float time_init = 0;
			/*
			 * Open file for output 
			 */
			//FileOutputStream fout =  new FileOutputStream("output.txt");
			//pout = new PrintStream (fout);

			/*
			 *  Read file line-by-line until the end of the file 
			 */
			while ( (currentLine = bis.readLine()) != null) { 
				
				/*
				 *  Parse line and break up into elements 
				 */
				StringTokenizer st = new StringTokenizer(currentLine); 
				String col1 = st.nextToken(); 
				String col2 = st.nextToken(); 
				String col3  = st.nextToken(); 
				
				/*
				 *  Convert each element to desired data type 
				 */
				int SeqNo 	= Integer.parseInt(col1);
				float Ftime 	= Float.parseFloat(col2);
				int Fsize 	= Integer.parseInt(col3);
				
				float time_diff = Ftime - time_init;
                long time_diff2 = (long) time_diff;
                //microseoncds to nanoseconds conversion
                long result_time = time_diff2 * 1000;
                waitTime[i] = result_time;
                time_init = Ftime;
                System.out.println(Ftime);
                System.out.println(Fsize);
				/*
				 *  Add packet
				 */
		        byte[] buf=new byte[Fsize];
			    DatagramPacket packet=new DatagramPacket(buf,buf.length,addr,4445);
                //created a new class called frame to store the time, sequence number and packet. 
			    frames.add(i,packet);
                i++;
             }

                /*
				 *  Send packet
				 */
                DatagramSocket socket = new DatagramSocket();
                long lastSendTime = System.nanoTime();
			    for (i=0; i< frames.size(); i++) {

			    	//calculate the time and wait until the corresponding time
			    	long sendTime = lastSendTime + waitTime[i];
			    	long currentTime = System.nanoTime();
			    	while ( currentTime < sendTime ){
			    		currentTime = System.nanoTime();
			    	}

			    	lastSendTime = currentTime;
			    	socket.send(frames.get(i));   
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
					//pout.close();
				} catch (IOException e) { 
					System.out.println("IOException: " +  e.getMessage());  
				} 
			} 
		} 
	}  
}

