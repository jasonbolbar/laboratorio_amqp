/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amqp;

import java.io.IOException;

/**
 *
 * @author jason
 */
public class Producer extends Client{
    
    public void sendMessage(String queue, String message){
        try {
            channel.basicPublish("", queue, null, message.getBytes());
        } catch (IOException ex) {
            System.err.println("No se pudo enviar el mensaje");
        }
    }
    
}
