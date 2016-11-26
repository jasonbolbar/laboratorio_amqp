package bigram;

public class BigramProcessor {


	public String [] getBigrams(String line){
		return TextCleaner.cleanText(line).split(" ");
	}

}