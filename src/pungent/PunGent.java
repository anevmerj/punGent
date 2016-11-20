/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pungent;
import java.util.Vector;
import java.util.Random;
/**
 *
 * @author Scaret Camille Chao
 */
public class PunGent {
    
     public static void main(String arg){
         
     }
    
String randomPunGenerator(Vector<String> collection){
           Random rand = new Random();
           int rnd_num = rand.nextInt() % PunGent.collection.size();
           String rnd_pun = PunGent.collection.elementAt(randNumGen());
           return rnd_pun;
        }
    /**
     * @param args the command line arguments
     */
    
}
