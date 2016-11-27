/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.function.Function;

public class AMQPConsumer extends Client{
    
    private Consumer consumer;
    private Function callback;
    
    public void handleMessagesFrom(String queue){
        setConsumer();
        try {
            //channel.basicQos(1);
            channel.basicConsume(queue, true, consumer);
        } catch (IOException ex) {
            System.err.println("El mensaje no se pudo recibir");
            closeConnection();
        }
    }
    
    public void setCallback(Function callback){
        this.callback = callback;
    }
   
    
    private void setConsumer() {
        this.consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                callback.apply(body);
            }
        };
    }
    
}
