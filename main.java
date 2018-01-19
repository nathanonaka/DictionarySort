import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class main {

	public static void main(String[] args)
	{
		String[]			words;
		
		words = loadWords("A10-1000-words.txt");
		
		ArraySort<String>		as = new ArraySort<>();
		StringComparator	strCmp = new StringComparator();
		
		as.heapSort(words, strCmp);
		//as.mergeSort(words, strCmp);
		//as.quickSort(words, strCmp);
		
		checkOrder(words);
		
	}
	static String[] loadWords(String inFile) {
		ArrayList<String>	words = new ArrayList<>();
		String[]			word_arr;
		BufferedReader 		br = null;
		
		try {
			FileInputStream inStream = new FileInputStream(inFile);
			
			br = new BufferedReader(new InputStreamReader(inStream));
			String			line;
			
			line = br.readLine();
			while (line != null) {
				words.add(line);
				line = br.readLine();
			}
			
			br.close();
		}
		catch (IOException ioe) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			System.out.println("Error reading file: " + ioe);
			fail("Error reading file.");
		}
		word_arr = new String[words.size()];
		words.toArray(word_arr);
		
		return word_arr;
	}
	
	static void checkOrder(String[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			assertTrue(arr[i].compareTo(arr[i+1]) <= 0);
		}		
	}

}
