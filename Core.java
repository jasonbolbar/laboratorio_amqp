import amqp.Producer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Core {
    
    public void run(){
    	Producer producer = new Producer();
        Scanner in = new Scanner(System.in);
        System.out.println("Número de lineas:");
        int numerolineas = Integer.parseInt(in.nextLine());
        int cont = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("../corpus35MillonesDeTweets.csv"))) {
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
