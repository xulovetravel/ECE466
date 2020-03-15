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

class TrafficGenerator_2 {  
	public static void main (String[] args) { 
		
		
		BufferedReader bis = null; 
		String currentLine = null; 
		PrintStream pout = null;
        //localhost address: 127.0.0.1
        String hostname = "127.0.0.1";
        int port = 4445;
        long[] time_to_wait = new long[100001]; 
        int[] bufsize = new int[100001];
        long currentTime = 0;
        long lastTime = 0;
        int i = 0;
        float time_init = 0;
		try {  
			
			/*
			 * Open input file as a BufferedReader
			 */ 
            InetAddress addr = InetAddress.getByName(hostname);	
			File fin = new File("Bel.data"); 
			FileReader fis = new FileReader(fin);  
			bis = new BufferedReader(fis);  
            //lastTime = currentTime;
         
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
				
				/*
				 *  Convert each element to desired data type 
				 */
				float Ftime 	= Float.parseFloat(col1);
				int Fsize 	= Integer.parseInt(col2);
				float time_diff_init = Ftime - time_init;
				long time_diff = (long) (time_diff_init * 1000 * 1000 * 1000);
                time_init = Ftime;
                time_to_wait[i] = time_diff;
                bufsize[i]=Fsize;
                
				/*
				 *  Add packet
				 */
		        //byte[] buf=new byte[Fsize];
			    //DatagramPacket packet=new DatagramPacket(buf,Fsize,addr,4444);
			    //frames.add(i, packet);
                i++;

             }

                /*
				 *  Send packet
				 */
             DatagramSocket socket=new DatagramSocket();
             //long lastTime = 0;
                 for(i=0;i<100001;i++){
	                  currentTime=System.nanoTime();

	          while(currentTime-time_to_wait[i]<lastTime){
		              currentTime=System.nanoTime();
	          }
            //if exceeds 1024, has to limit to 1024. 
	        while(bufsize[i]>1024){
		        byte[] buf=new byte[1024];
		        DatagramPacket packet=new DatagramPacket(buf,buf.length,addr,4445);
		        socket.send(packet);
		        bufsize[i]=bufsize[i]-1024;
	        }
	        byte[] buf= new byte[bufsize[i]];
	        DatagramPacket packet=new DatagramPacket(buf,buf.length,addr,4445);
	        socket.send(packet);
	        lastTime=System.nanoTime();
           }
				
			
               
		}  catch (IOException e) {  
			// catch io errors from FileInputStream or readLine()  
			System.out.println("IOException: " + e.getMessage());  
		} finally {  
			// Close files   
			if (bis != null) { 
				try { 
					bis.close(); 
					//pout.close();
				}catch (IOException e) { 
					System.out.println("IOException: " +  e.getMessage());  
				} 
			} 
		} 
	}  
}
