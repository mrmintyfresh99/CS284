package hw6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that checks Anagrams.
 * @author Mathew Seedhom
 * I pledge my honor that I have abided by the Stevens Honor System.
 */
public class Anagrams {

	/**
	 * Data Fields
	 */
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
	                          67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	/**
	 * Constructors
	 */
	public Anagrams() {
		letterTable = new HashMap<Character,Integer>();
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	/*
	 * Builds a Letter Table for hashing
	 */
	private void buildLetterTable() {
		Character[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for (int i = 0; i < 26; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}
	
	/**
	 * A hash functions for storing anagrams
	 * @param s The word to check
	 */
	private void addWord(String s) {
		if (anagramTable.containsKey(myHashCode(s))) {
			ArrayList<String> temp = anagramTable.get(myHashCode(s));
			temp.add(s);
			anagramTable.replace(myHashCode(s), temp);
		} else {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(s);
			anagramTable.put(myHashCode(s), temp);
		}
	}
	
	/**
	 * The functions that generates unique keys for hash functions
	 */
	private Long myHashCode(String s) {
		int i = 0;
		long key = 1;
		while (i < s.length()) {
			Character a = s.charAt(i);
			key = key * letterTable.get(a);
			i++;
		}
		return key;
	}
	
	/**
	 * Opens the text document to check for anagrams
	 * @param s The word
	 * @throws IOException The file could not be read
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); String strLine;
		while ((strLine = br.readLine()) != null) { 
			this.addWord(strLine);
		} 
		br.close();
	}
	
	/**
	 * Gets the key with the most anagrams
	 * @return Keys and anagrams
	 */
	protected ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long,ArrayList<String>>> temp = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int max = 0;
		for (Map.Entry<Long,ArrayList<String>> entry: anagramTable.entrySet()) {
			if (entry.getValue().size() > max) {
				temp.clear();
				temp.add(entry);
				max = entry.getValue().size();
			} else {
				if (entry.getValue().size() == max) {
					temp.add(entry);
				}
			}
		}
		return temp;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		long key = maxEntries.get(0).getKey();
		System.out.println("Time: "+ seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue()); 
		System.out.println("Length of list of max anagrams: "+ length);
	}
}

