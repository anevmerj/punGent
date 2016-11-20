/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pungent;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Alexei
 */
public class randomIt {
    public String randomPunGenerator(Vector<String> collection){
           Random rand = new Random();
           int rnd_num = rand.nextInt() % collection.size();
           String rnd_pun = collection.elementAt(rnd_num);
           return rnd_pun;
        }
}
