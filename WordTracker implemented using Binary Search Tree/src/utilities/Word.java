package utilities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Madhu
 *
 */
public class Word implements Comparable<Word>, Serializable{
	
	private static final long serialVersionUID = -6588548159721358466L;
	
	//Attributes
	private String word;
	private ArrayList<String> lfPairs = new ArrayList<String>(); //each string will contain 'filename'#'linenumber'|'linenumber'|...
	
	//Constructors
	public Word(String word, String fileName, int lineNumber)
	{
		this.setWord(word);
		String fileLine = fileName + "#" + lineNumber;
		lfPairs.add(fileLine);
	}
	
	public Word()
	{
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public ArrayList<String> getLfPairs() {
		return lfPairs;
	}

	public void setLfPair(ArrayList<String> lfPairs) {
		this.lfPairs = lfPairs;
	}

	

	@Override
	public int compareTo(Word o) { //just compare the word
		if (this.word.compareToIgnoreCase(o.word) > 0) {
			return 1;
		} else if (this.word.compareToIgnoreCase(o.word) < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}
