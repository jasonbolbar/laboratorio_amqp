/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author jason
 */
public class Main {
    
    public static void main (String [] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Lógica a Correr:");
        System.out.println("1. Productores");
        System.out.println("2. Consumidores:");
        switch(Integer.parseInt(in.nextLine())){
            case 1:
                new LogicaProducer().correr();
                break;
            case 2:
                new LogicaConsumer().correr();
                break;
            default:
                System.out.println("No existe esa opcion");
        }
    }
    
}
