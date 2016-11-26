
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
        this.consumidor= new AMQPConsumer();
        consumidor.setCallback(callback());
        consumidor.handleMessagesFrom("AMQPBigram");
    }
    
    private Function callback(){
        return (Function) (Object t) -> {
            String bigram = new String((byte[]) t);
            boolean finCola = bigram.equals("######END######");
            if(!finCola){
                contador.processBigram(bigram);
            } else {
            	contador.storeFrequencies();
                consumidor.closeConnection();
            }
            return null;
        };
    }
    
}