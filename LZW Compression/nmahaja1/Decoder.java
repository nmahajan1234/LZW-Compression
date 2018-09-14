 /*
 * Nayan Milind Mahajan (801024259)
 */ 



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;


public class Decoder {
	
	/*
	 * Declaring a hashmap to use it as a data structure.
	 * 
	 */
	
	static HashMap<Integer, String> hmap = new HashMap<Integer, String>();

	
	/* Function: file_read
	 * 
	 * Purpose: To read input data from input1.lzw file and return in a integer arraylist.
	 */
	
	public ArrayList<Integer> file_read(String s) throws Exception{

		int rd;

		ArrayList<Integer> to_read = new ArrayList<Integer>();		
		File file1 = new File(s);
		InputStream isr = new FileInputStream(file1);
		Reader read = new InputStreamReader(isr, "UTF-16BE");
		BufferedReader br = new BufferedReader(read);
		try{
			while((rd = br.read()) != -1)
			{
				to_read.add(rd);

			}
		}
		catch(Exception er){
			er.printStackTrace();
		}
		finally{
			read.close();
			br.close();
			isr.close();
		}
		return to_read;
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
	 * Function: file_writer
	 * 
	 * Purpose: Function to write output in input1.txt file
	 */
	
	
	public void file_writer(String output) throws Exception{
		File file1 = new File("input1_decoded.txt");
		OutputStream osw = new FileOutputStream(file1);
		Writer writer = new OutputStreamWriter(osw);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
		
		try{
				writer.write(output);
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
	public static void main(String args[]) throws Exception {
		Decoder d = new Decoder();
		CodeToAscii c = new CodeToAscii();
		ArrayList<Integer> input = new ArrayList<Integer>();
		String to_write = "";

		//read input data from lzw file
		input = d.file_read(args[0]);


		//create hashmap
		d.create_hmap();

		// implementation of decompression algorithm
		int max_table_size = Integer.parseInt(args[1]);
		to_write = c.codeToAscii(input, max_table_size, hmap);
		
		// writing output to the input1_decoded.txt
		d.file_writer(to_write);
	}



}