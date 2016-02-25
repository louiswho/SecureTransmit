

import java.util.*;
import java.io.*;

public class compressDecompress {


	/**
	 * Compression Algorithm
	 * @param inputString - String of text
	 * @param outputFilePath  
	 * @throws IOException
	 * 
	 * Psuedocode:
	 * initialize the dictionary for ascii codes 0-255 and corresponding characters
	 * 
	 * w = NULL;
   	 * 	while (read a char c) do
     * 		if (wc exists in dictionary) then
     *     		w = wc;
     *  	else (wc does not exist in dictionary)
     *      	add wc to the dictionary;
     *      	return the code for w;
     *      	w = c;
     *  	endif
   	 *		done
   	 * return the code for w;

	 */
	
    public static void compress(String inputString, String outputFilePath) throws IOException  {
       
    	/******************************************************
    	 * create initial dictionary mapping of an eight
    	 *  bit character set (ASCII) to the numbers 0-255 */
    	
        int dictionarySize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char)i, i);
        }
        /************end initial dictionary mapping****************/
        
        
        /*************compression algorithm************************/
        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char c : inputString.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary.
                dictionary.put(wc, dictionarySize++);
                w = "" + c;
            }
        }
 
        // Output the code for w.
        if (!w.equals("")) {
            result.add(dictionary.get(w));
        }
        /****************************************************/
        
        writeCompression(result, outputFilePath);
			
    }
    
    /** 
     * Decompression Algorithm
     * @param inputString - string of ascii codes
     * @param outputFilePath
     * @throws IOException
     * 
     * Psuedocode:
     * initialize the dictionary for ascii codes 0-255 and corresponding characters
     * 
     * read a code;
     * current entry - dictionary entry for the code;
     * return current entry;
     * 
     * while (read a code) 
     * 		previous = current entry
     * 		if (code exists in dictionary) 
     * 			current entry = dictionary entry for the code;
     * 		else (code does not exist in dictionary)
     *			current entry = previous + previous[0];
	 *
	 * return current entry;
	 * 
	 * add (previous + current entry[0]) to the dictionary;	
     */
    
    public static void decompress(String inputString, String outputFilePath) throws IOException {
    	
    	/*********parse input containing String of integers representing ascii codes
    	 * into Integer ArrayList*/
    	
    	Scanner scanner = new Scanner(inputString);
    	List<Integer> compressed = new ArrayList<Integer>();
    	while (scanner.hasNextInt()) {
    		compressed.add(scanner.nextInt());
    	}
    	scanner.close();
   
    	
    	/******************************************************
    	 * create initial dictionary mapping of an eight
    	 *  bit character set (ASCII) to the numbers 0-255 */
    	
        int dictionarySize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char)i);
        }
        /************end initial dictionary mapping****************/
        
        /********************************************************/
        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictionarySize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
 
            result.append(entry);
 
            // Add previous + current entry[0] to the dictionary.
            dictionary.put(dictionarySize++, w + entry.charAt(0));
 
            w = entry;
        }
        /**********************************************************/
        
        String outputString = result.toString();
        
 	
		//System.out.println(outputString);
		
		writeDecompression(outputString, outputFilePath);
    }
    

    
    /**
     * writeCompression
     * 		writes ascii codes to file
     * 
     * @param encodedList - ArrayList of ascii codes
     * @param outputPath - file path for output file
     * @throws IOException
     */
	private static void writeCompression(List<Integer> encodedList, String outputPath)  throws IOException  {
    	File outputFile = new File(outputPath);
			if(!outputFile.exists()) {
				outputFile.createNewFile();
		} 
		
		FileWriter fWrite = new FileWriter(outputFile.getAbsoluteFile());
		BufferedWriter bufferWriter = new BufferedWriter(fWrite);  
    
    
		for(Integer e : encodedList){
			System.out.println(e);
			bufferWriter.write(e.toString());
			bufferWriter.newLine();
			bufferWriter.flush();
		}	
		bufferWriter.close();
    
	//System.out.println(encodedList);
    }
    
 	/**
 	 * writeDecompression
 	 * 		writes decoded string to file
 	 * 
 	 * @param decodedString - string containing decoded text
 	 * @param outputPath - file path for output file
 	 * @throws IOException
 	 */
	
		private static void writeDecompression(String decodedString, String outputPath)  throws IOException  {
	    	File outputFile = new File(outputPath);
				if(!outputFile.exists()) {
					outputFile.createNewFile();
			} 
			
			FileWriter fWrite = new FileWriter(outputFile.getAbsoluteFile());
			BufferedWriter bufferWriter = new BufferedWriter(fWrite);  
	    
	    
			for(int i = 0; i < decodedString.length(); i++){
				char c;
				c = decodedString.charAt(i);
				bufferWriter.write(c);
			}
			bufferWriter.close();
	    
		System.out.println(decodedString);
	    }
        
    }
	

