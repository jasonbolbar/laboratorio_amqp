import java.util.LinkedHashMap;

class BigramCounter {

	private final LinkedHashMap<String, Integer> bigramas;

	public BigramCounter(){
		this.bigramas = new LinkedHashMap<String, Integer>();
	}
    
    /**
     * Agrega un nuevo bigrama a la lista o actualiza el contador en caso
     * de que este bigrama esté presente
     * 
     * @param bigram Bigrama
     */
    public void processBigram(String bigram){
        if(bigramExist(bigram)){
            increaseBigramCount(bigram);
        }else{
            addNewBigram(bigram);
        }
    }

    public void storeFrequencies() {
    	new BigramStorage(this.bigramas).storeBigrams();
    }
    
    /**
     * Verifica si un bigrama existe dentro de la lista
     *  
     * @param bigram Bigrama
     */
    private boolean bigramExist(String bigram){
        return this.bigramas.containsKey(bigram);
    }

    /**
     * Incrementa el contador de bigramas en caso de que el bigrama
     * exista actualmente en la lista
     *  
     * @param bigram Bigrama
     */
    private void increaseBigramCount(String bigram) {
        putBigram(bigram, getBigram(bigram) + 1);
    }

    /**
     * Agrega un nuevo bigrama a la lista
     * 
     * @param bigram Bigrama
     */
    private void addNewBigram(String bigram) {
        putBigram(bigram, 1);
    }

    /**
     * Retorna el contador de un bigrama de la lista
     *  
     * @param bigram Bigrama
     * @return conteo para un bigrama proporcionado
     */
    private int getBigram(String bigram) {
        return this.bigramas.get(bigram);
    }
    
    /**
     * Actualiza el valor de un bigrama proporcionado
     *  
     * @param bigram Bigrama
     */
    private void putBigram(String bigram, int value){
        this.bigramas.put(bigram, value);
    }
}