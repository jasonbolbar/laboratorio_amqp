/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import amqp.Producer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jason
 */
public class LogicaProducer {
    
    public void correr(){
        Producer producer = new Producer();
        Scanner in = new Scanner(System.in);
        System.out.println("Número de lineas:");
        int numerolineas = Integer.parseInt(in.nextLine());
        int cont = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("~/corpus35MillonesDeTweets.csv"))) {
            for(String line; (line = br.readLine()) != null && cont <= numerolineas; ) {
                String[] parts = line.split(",");
                line = parts[parts.length -1];
                producer.sendMessage("AMQP", line.replaceAll("\"",""));
                cont ++;
            }
        } catch (IOException ex) {
            System.err.println("Error de lectura");
        }
        producer.sendMessage("AMQP", "######END######");
        producer.closeConnection();
    }
    
    
}
