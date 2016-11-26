import amqp.AMQPConsumer;
import java.util.function.Function;
import bigram.BigramProcessor;
import amqp.Producer;

public class Core {
    
    private AMQPConsumer consumidor;
    private BigramProcessor procesador;
    private Producer productor;
    
    public void run(){
    	this.procesador = new BigramProcessor();
        this.consumidor= new AMQPConsumer();
        this.productor= new Producer();
        consumidor.setCallback(callback());
        consumidor.handleMessagesFrom("AMQP");
    }
    
    private Function callback(){
        return (Function) (Object t) -> {
            String line = new String((byte[]) t);
            String [] bigramas = procesador.getBigrams(line);
            for(String bigrama : bigramas ) {
		       productor.sendMessage("AMQPBigram", bigrama);
			}
            return null;
        };
    }
    
}