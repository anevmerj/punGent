package com.example.shawnalee.pungen;

import java.util.Vector;

/**
 *
 * @author Alexei
 */
public class catBase{
    private Vector<String> cat= new Vector();
    private String pun;
    private Vector<String> associated_words= new Vector();


    catBase(String a, String b, String c){cat.add(a); pun =b; associated_words.add(c);}
    catBase(String b){cat.add("myPuns"); pun = b;}
    public void add_word(String a){associated_words.addElement(a);}

    public void add_cat(String a){cat.addElement(a);}

    public String get_pun(){return pun;}

    public Vector<String> get_word(){
        return associated_words;
    }

    public Vector<String> get_cat(){return cat;}

    public boolean is_word(String user_input){
        for(int i = 0; i < associated_words.size();i++){
            if(user_input.equals(associated_words.elementAt(i))){
                return true;
            }
        }
        return false;
    }

}
