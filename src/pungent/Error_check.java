/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pungent;
import pungent.catBase;
import java.util.Vector;

/**
 *
 * @author Alexei
 */
public class Error_check {
    public static boolean main(String arg, Vector<catBase> collection){
        for(int i = 0;i<collection.size();i++){
            if(collection.elementAt(i).is_word(arg)){
                return true;
            }
        }
        return false;
    }
}
