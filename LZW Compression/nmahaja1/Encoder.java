 /*
 * Nayan Milind Mahajan (801024259)
 */ 


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UTFDataFormatException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.math.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class Encoder {
	/*
	 * Declaring a hashmap to use it as a data structure.
	 * 
	 */
	static HashMap<Integer, String> hmap = new HashMap<Integer, String>();

	/* Function: f_read
	 * 
	 * Purpose: To read input data from input1.txt file and return in a string
	 */
	
	
	public String f_read(String s) throws IOException{
		String local_input = new String();
		String rd = "";
		File file = new File(s);

		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((rd = br.readLine()) != null){
			local_input += rd;
			local_input += "\n";  
		}
		br.close();
		return local_input;
	}


	/* Function: create_hmap
	 * 
	 * Purpose: Function to create hashmap. In this, I have mapped integer to ASCII values.
	 */ 
	
	
	public void create_hmap(){
		int i;
		String temp;

		for(i = 0 ; i < 256 ; i++){
			temp = "" + (char)i;
			hmap.put(i, temp);
		}
	}
	
	/*
	 * Function: filewrite
	 * 
	 * Purpose: Function to write output in input1.lzw file
	 */
	
	 public void filewrite(ArrayList<Integer> to_write) throws Exception{
		 	int i = 0;
			File file1 = new File("input1.lzw");
			OutputStream osw = new FileOutputStream(file1);
			Writer writer = new OutputStreamWriter(osw, "UTF-16BE");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
			try{
				while( i < to_write.size())
				{
					writer.write(to_write.get(i));
					i++;
				}
			}
			catch(Exception er){
				er.printStackTrace();
			}
			finally{
				writer.close();
				bw.close();
				osw.close();
			}
	 }
	 
	/*
	 * 
	 *  main function
	 */
	 

	public static void main(String[] args)throws Exception
	{
		Encoder e = new Encoder();
		Transform ts = new Transform();
		ArrayList<Integer> to_write = new ArrayList<Integer>();		// store encoded data
		int max_bit_length = Integer.parseInt(args[1]);

		if(!(max_bit_length > 0 && max_bit_length < 100))
			System.out.println("Invalid bit length");

		String input = "";

		/*
		 *   reading text file
		 */
		input = e.f_read(args[0]);

		/*
		 * 	 creating hashmap
		 */
		e.create_hmap();

		/*
		 *   implementation of LZW algorithm
		 */
		to_write = ts.transform(input, max_bit_length,hmap);

		/*
		 * writing to output file "input1.lzw"
		 */
		e.filewrite(to_write);

	}
}

