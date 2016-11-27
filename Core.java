import amqp.AMQPConsumer;
import java.util.function.Function;
import bigram.BigramCounter;
public class Core {
    
    private AMQPConsumer consumidor;
    private BigramCounter contador;
    private boolean colaTerminada;
    public void run(){
        this.contador = new BigramCounter();
        this.consumidor= new AMQPConsumer();
        this.colaTerminada= false;
        consumidor.setCallback(callback());
        consumidor.handleMessagesFrom("AMQPBigram");
    }
    
    private Function callback(){
        return (Function) (Object t) -> {
            String bigram = new String((byte[]) t);
            boolean finCola = bigram.equals("######END######");
            if(finCola && !colaTerminada){
               this.colaTerminada = true;
               System.out.println("la cola original esta vacia");
            } 
            if(!(colaTerminada && consumidor.queueSize("AMQPBigram") == 0)){
              contador.processBigram(bigram);
            } else {
              System.out.println("Guardando cambios y cerrando conexion");
              contador.storeFrequencies();
              consumidor.closeConnection();
            }
            return null;
        };
    }
    
}