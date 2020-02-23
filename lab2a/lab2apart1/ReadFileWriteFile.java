import java.io.*; 
import java.util.*; 

/* 
 *  The program reads an input file "data.txt"  that has entries of the form 
 *  0	0.000000	I	536	98.190	92.170	92.170
 *  4	133.333330	P	152	98.190	92.170	92.170
 * 	1	33.333330	B	136	98.190	92.170	92.170
 *
 * The file is read line-by-line, values are parsed and assigned to variables,
 * values are  displayed, and then written to a file with name "output.txt"  
 */

class ReadFileWriteFile {  
	public static void main (String[] args) { 
		
		
		BufferedReader bis = null; 
		String currentLine = null; 
		PrintStream pout = null;
		int Pcount =0;
        int Bcount = 0;
        int Icount = 0;
        int Psize = 0;
        int Bsize = 0;
        int Isize = 0;
		try {  
			
			/*
			 * Open input file as a BufferedReader
			 */ 
			File fin = new File("movietrace.data"); 
			FileReader fis = new FileReader(fin);  
			bis = new BufferedReader(fis);  
			
			/*
			 * Open file for output 
			 */
			FileOutputStream fout =  new FileOutputStream("output.txt");
			pout = new PrintStream (fout);
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
				String col4 = st.nextToken(); 
				
				/*
				 *  Convert each element to desired data type 
				 */
				int SeqNo 	= Integer.parseInt(col1);
				float Ftime 	= Float.parseFloat(col2);
				String Ftype 	= col3;
				int Fsize 	= Integer.parseInt(col4);
				
              if(Ftype.equals("I")) {
                   Isize = Fsize + Isize;
                   Icount++;
                }
                
                 else if(Ftype.equals("B")) {
                   Bsize = Fsize + Bsize;
                   Bcount++;
                }
                  else if(Ftype.equals("P")) {
                   Psize = Fsize + Psize;
                   Pcount++;
                }


				/*
				 *  Display content of file 
				 */
				//System.out.println("SeqNo:  " + SeqNo); 
				//System.out.println("Frame time:   " + Ftime); 
				//System.out.println("Frame type:        " + Ftype); 
				//System.out.println("Frame size:       " + Fsize + "\n"); 
             
				
				/*
				 *  Write line to output file 
				 */
				pout.println(SeqNo+ "\t"+  Ftime + "\t" + Ftype + "\t" + Fsize); 
				
			} 
           int IMean = Isize / Icount;
                int BMean = Bsize / Bcount;
                int PMean = Psize / Pcount;
				System.out.println("Iframe size:  " +IMean + "\n");
                System.out.println("Bframe size:  " +BMean + "\n");
                System.out.println("Fframe size:  " +PMean + "\n");
              
		} catch (IOException e) {  
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

