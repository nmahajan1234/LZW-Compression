 /*
 * Nayan Milind Mahajan (801024259)
 */ 


import java.util.ArrayList;
import java.util.HashMap;


public class CodeToAscii {
	
	

	/*
	 * Function: codeToAscii 
	 *  
	 * Purpose: implementation of pseudo code for decoding. Return a string
	 * 
	 */
	
	public String codeToAscii(ArrayList<Integer> input, int max_table_size, HashMap<Integer, String> hmap){
		int i = 0;
		int k = 256;
		String output = "";
		
		String new_string = "";
		int code = input.get(i);
		String str = hmap.get(code);
		
		output = str;
		System.out.print(output);
		i++;
		
		// Do decoding while characters are there in the string
		while( i < input.size()){
			
			code = input.get(i);
			// string is not defined in the hashmap table
			if(!(hmap.containsKey(code))){
				//new_string = string + string[0]
				new_string = str + str.charAt(0);
			}	
			// string is defined in the hashmap table
			else {
				//new_string =  table[size]
				new_string = hmap.get(code);
			}
	
			System.out.print(new_string);
			// appending the output in a string
			output += new_string;
			if(hmap.size() < Math.pow(2, max_table_size)){
				
				hmap.put(k, str + new_string.charAt(0));
				k++;
			}
			str = new_string;
			i++;
			
		}
		System.out.println();
		return output;
	}

}
