import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase que se encarga de almacenar una lista de bigramas dentro del archivo
 * bigramas.txt
 */
class BigramStorage {
    
    private LinkedHashMap<String, Integer> bigrams;
    
    private static final String BIGRAM_FILENAME = "bigramas.txt" ;
    
    /**
     * Constructor de la clase
     * @param bigrams lista de brigramas
     */
    public BigramStorage(LinkedHashMap<String, Integer> bigrams) {
       this.bigrams = bigrams;
    }
    
    /**
     * Ordena la lista de bigramas segun su conteo y almacena la lista en el
     * archivo
     * @throws IOException el archivo no se encuentra o no se pudo escribir
     */
    public void storeBigrams(){
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(BIGRAM_FILENAME)));
            for( Map.Entry<String, Integer> entry : bigrams.entrySet()){
                writer.write(String.format("%s -> %s", entry.getKey(), entry.getValue()));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            System.err.println("Archivo no se pudo cerrar");
        }
    }
    
    
    
}