
import amqp.AMQPConsumer;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.function.Function;

public class Core {
    
    private AMQPConsumer consumidor;
    private BufferedWriter writer;
    private BigramCounter contador;
    
    public void run(){
    	this.contador = new BigramCounter();
        construirConsumidor();
    }
    
    private void construirConsumidor(){
        this.consumidor= new AMQPConsumer();
        consumidor.setCallback(callback());
        consumidor.handleMessagesFrom("AMQPBigram");
    }
    
    private Function callback(){
        return (Function) (Object t) -> {
            String bigram = new String((byte[]) t);
            boolean finCola = message.equals("######END######");
            if(!finCola){
                try {
                    contador.processBigram(bigram)
                } catch (IOException ex) {
                    System.out.println("Mensaje no se pudo escribir");
                }
            } else {
            	contador.storeFrequencies();
                consumidor.closeConnection();
                try {
                    writer.close();
                } catch (IOException ex) {
                    System.err.println("Archivo no se pudo cerrar");
                }
            }
            return null;
        };
    }
    
}