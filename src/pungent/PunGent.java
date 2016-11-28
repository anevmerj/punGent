package com.example.shawnalee.pungen;

/**
 * Created by Shawna Lee on 2016-11-19.
 */

import java.util.Vector;
import java.util.Random;
import android.support.v7.app.AppCompatActivity;
/**
 *
 * @author Scaret Camille Chao
 */
public class PunGen {

    public static void main(String arg){

    }

    String randomPunGenerator(Vector<String> possiblePuns){
        Random rand = new Random();
        int rnd_num = rand.nextInt() % possiblePuns.size();
        String rnd_pun = possiblePuns.elementAt(rnd_num);
        return rnd_pun;
    }
    /**
     * @param args the command line arguments
     */

}
