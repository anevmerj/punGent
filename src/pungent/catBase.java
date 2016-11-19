/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pungent;
import java.util.Vector;

/**
 *
 * @author Alexei
 */
public class catBase extends parser{
    private Vector<String> cat= new Vector();
    private String pun;
    private Vector<String> associated_words= new Vector();
    
    
    catBase(String a, String b, String c){cat.add(a); pun =b;
        associated_words.add(c);}
    public void add_word(String a){associated_words.addElement(a);}
    public void add_cat(String a){cat.addElement(a);}
    public String get_pun(){return pun;}
    public Vector<String> get_word(){
        return associated_words;
    }
    public Vector<String> get_cat(){return cat;}
    
    public boolean is_word(String arg){
        for(int i = 0; i < associated_words.size();i++){
            if(arg.equals(associated_words.elementAt(i))){
                return true;
            }
        }        
        return false;
    }
    
}
