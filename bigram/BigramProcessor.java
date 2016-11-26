package bigram;

import java.util.ArrayList;

public class BigramProcessor {

	private String tempWord = null;

	public String [] getBigrams(String line){
		int backIndex = 1;
		if(this.tempWord != null){
			line = String.format("%s %s",line,this.tempWord);
			backIndex = 2;
		}
		String [] words = splitStream(line);
		return createBigramList(words);
	}

	private String [] splitStream(String line){
		return TextCleaner.cleanText(line).split(" ");
	}

	private String [] createBigramList(String [] words){
		ArrayList <String> bigrams = new ArrayList<String>();
		for(int i = 0; i < words.length - 1; i++){
           bigrams.add(formatBigram(words[i],words[i + 1]));
        }
        return bigrams.toArray(new String[bigrams.size()]);
	}

	/**
     * Forma el string que hace la representación de un bigrama, en caso de dos
     * palabras, son unidas por un espacio y en caso de que sea punto-palabra o
     * coma-palabra estos seran unidos.
     *  
     * @param term1 primer termino del bigrama
     * @param term2 segundo termino del bigrama
     * 
     * @return bigrama creado
     */
    private String formatBigram(String term1, String term2){
        String formatBigram ;
        if(".".equals(term1) || ",".equals(term1) || ".".equals(term2) || ",".equals(term2)){
            formatBigram = String.format("%s%s",term1,term2);
        }else{
            formatBigram = String.format("%s %s",term1,term2);   
        }
        return formatBigram;
    }

}