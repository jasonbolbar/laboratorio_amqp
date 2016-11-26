package amqp;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

class Client {
    
    
    private final static String HOST = "rabbitmq.jasonbolbar.com";
    
    private ConnectionFactory factory;
    private Connection connection;
    protected Channel channel;

    public Client(){
        createfactory();
        createConnection();
        createChannel();
    }
  
    
    private void createfactory(){
        this.factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername("jbolanos");
        factory.setPassword("vpcf9650@");
    }
    
    private void createConnection(){
        try {
            this.connection = factory.newConnection();
        } catch (IOException | TimeoutException ex) {
            System.err.println("Error al crear una conexion");
        }
    }
    
    private void createChannel(){
        try {
            this.channel = connection.createChannel();
        } catch (IOException ex) {
            System.err.println("Error al crear un Canal");
        }
    }
    
   
    public void closeConnection(){
        try {
            if(channel.isOpen()){
                channel.close();
            }
            if(connection.isOpen()){
                connection.close();
            }
        } catch (IOException | TimeoutException ex) {
            System.err.println("Error al cerrar la conexión");
        }
    }
    
}
