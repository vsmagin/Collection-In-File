/**
 * The program reads a text file with words a outputs the number of words
 * in the file, number of names(name - word that starts with a capital letter and 
 * has 2 or more letter, letterMap - map of all the letters used and how many times they were used
 */


import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CollectionInFile {
	
	private TreeMap<Character, Integer> letterMap; //sorted Map of words in readFile
	private int wordsCount;
	private int namesCount;
	
	/**constructor creates a sorted map of words from a given file
	 * calculates number of words in the file
	 * calculates number of names in the file
	 */
	public CollectionInFile(File file) throws FileNotFoundException{
		
		letterMap = new TreeMap<Character, Integer>();
		Scanner input = new Scanner(file);
		while (input.hasNext()){
			String word = input.next();
			char letter = word.charAt(0);
			//update the wordsCount
			wordsCount++;	
			//update the namesCount
			if (word.length() > 1 && Character.isUpperCase(letter)) namesCount++;			
			//update the letterMap
			if (letterMap.get(letter) == null)	
				letterMap.put(letter, 1);
			else{
				int numTimes = letterMap.get(letter);
				letterMap.put(letter, numTimes+1);
			}		
		}
		input.close();
	}
	
	public TreeMap<Character, Integer> letterMap(){
		return this.letterMap;
	}
	
	public int wordsCount(){
		return this.wordsCount;
	}
	
	public int namesCount(){
		return this.namesCount;
	}
	
	
	public static void main(String[] args) throws Exception{
		File readFile = new File("words.txt");
		CollectionInFile collection = new CollectionInFile(readFile);
		
		//print results on the console:
		System.out.println("Total words count is: " + collection.wordsCount);
		System.out.println("Total names count is: " + collection.namesCount);
		System.out.println("MAP: " + collection.letterMap);
		
		//print results in a new file:
		File writeFile = new File("results.txt");
		PrintWriter output = new PrintWriter(writeFile);
		output.println("Total words count is: " + collection.wordsCount);
		output.println("Total names count is: " + collection.namesCount);
		output.println("First letters count: ");
		
		TreeSet<Character> letterSet = new TreeSet<Character>(collection.letterMap.keySet());
		for (char ch: letterSet)
			output.println(ch + " : " + collection.letterMap.get(ch));

		output.close();
	}	
}

