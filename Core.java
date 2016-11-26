
import amqp.AMQPConsumer;
import java.util.function.Function;
import bigram.BigramCounter;

public class Core {
    
    private AMQPConsumer consumidor;
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