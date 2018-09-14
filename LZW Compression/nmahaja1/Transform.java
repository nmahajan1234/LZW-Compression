 /*
 * Nayan Milind Mahajan (801024259)
 */ 



import java.util.ArrayList;
import java.util.HashMap;


public class Transform {
	
	
	/*
	 * Function: transform 
	 *  
	 * Purpose: implementation of pseudo code for encoding. Return the arraylist of integers
	 */
	public ArrayList<Integer> transform(String input, int max_bit_length, HashMap<Integer, String> hmap){

		
		ArrayList<Integer> conv_data = new ArrayList<Integer>();		// store encoded data

		int i, j, k;
		int output = 0;
		i = 0;
		k = 256;
		String symbol, str;
		symbol = str = "";
		boolean flag = false;

		while(i < (input.length() - 1)){
			symbol = input.charAt(i) + "";
			

			for ( j = 0; j < hmap.size() ; j++)
				if(String.valueOf(str + symbol).equals(hmap.get(j))){
					flag =  true;
					break;
				}
			if(flag){
				str = str + symbol;
			}
			else{
				//  search code in hashmap and print output
				if(hmap.size() < Math.pow(2, max_bit_length)){
					for( j = 0; j < hmap.size() ; j++){
						if(str.equals(hmap.get(j))){
							output = j;
							System.out.println(j);
						}
					}
				}
				//	str string + symbol to hashmap
				hmap.put(k, str + symbol);
				k++;

				//	add the code to output arraylist
				conv_data.add(output);

				//	string = symbol
				str = symbol;
			}

			i++;
			flag = false;
		}
		if(hmap.size() < Math.pow(2, max_bit_length)){
			for( j = 0; j < hmap.size() ; j++){
				if(str.equals(hmap.get(j))){
					output = j;
					System.out.println(j);
				}
			}
		}
		//		add the code to output arraylist
		conv_data.add(output);
		
		
		return conv_data;
	}
}
