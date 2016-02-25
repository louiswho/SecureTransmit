

import java.util.*;
import java.io.*;

public class LempelZivWelch {
private static String inputFilePath = "C:/Users/Louis/eclipse workspace/LempelZivWelch/src/input.txt";	
private static String compressOutputFilePath = "C:/Users/Louis/eclipse workspace/LempelZivWelch/src/encoded.txt";
private static String decompressOutputFilePath = "C:/Users/Louis/eclipse workspace/LempelZivWelch/src/decoded.txt";
	
	 public static void main(String[] args) throws IOException {
		 FileInputStream inputFile = null;
			String inputString = "";
			
			try{
				
				inputFile = new FileInputStream(inputFilePath);
				char c;
				while((inputFile.available()) > 0) {
					c = (char) inputFile.read();
					inputString += c;
					
				}
			
			//compressDecompress.compress(inputString, compressOutputFilePath);
				 /**compress
				   * expected input - String of text
				   * output - file containing list of ascii encodings
				   * ex:
				   * in - "banana_bandana"
				   * out - [98 97 110 257 97 95 256 110 100 259 10]

				   */
				
			//compressDecompress.decompress(inputString, decompressOutputFilePath);			
			  /**decompress
			   * expected input - String containing list of ascii codes
			   * output - file containing decoded text
			   * ex:
			   * in - [98 97 110 257 97 95 256 110 100 259 10]
			   * out - "banana_bandana"
			   */
			   
				
				
		}	finally	{
			
			if (inputFile != null) {
	            inputFile.close();
	         }
		}
			
		}
		
	    
	
	
    
 
   
}