/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import amqp.AMQPConsumer;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.function.Function;

/**
 *
 * @author jason
 */
public class LogicaConsumer {
    
    private AMQPConsumer consumidor;
    private BufferedWriter writer;
    
    public void correr(){
        try {
             this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("salida.txt")));
        } catch (FileNotFoundException ex) {
            System.err.println("No se pudo leer el archivo");
        }
        construirConsumidor();
    }
    
    
    private void construirConsumidor(){
        this.consumidor= new AMQPConsumer();
        consumidor.setCallback(callback());
        consumidor.handleMessagesFrom("AMQP");
    }
    
    private Function callback(){
        return (Function) (Object t) -> {
            String message = new String((byte[]) t);
            boolean finCola = message.equals("######END######");
            if(!finCola){
                try {
                    writer.write(message);
                    writer.newLine();
                } catch (IOException ex) {
                    System.out.println("Mensaje no se pudo escribir");
                }
            } else {
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
