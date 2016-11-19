/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pungent;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.Vector;
/**
 *
 * @author Scaret Camille Chao
 */
public class PunGent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception{
        // TODO code application logic here
        String fileToRead = "testChart.csv";
        String sendToArray = null;
        Vector<catBase> collection = new Vector();
        String[] stuff = new String[3];
        String pun = null;
        String cat = null;
        String word = null;
        
        try{
            Reader readerFile = new FileReader(fileToRead);
            BufferedReader br = new BufferedReader(readerFile);
            while((sendToArray = br.readLine()) != null){
                stuff = sendToArray.split(",");
                pun = stuff[0];
                cat = stuff[1];
                word = stuff[2];
                if((pun.equals(""))){
                    if(!(cat.equals(""))){
                        collection.lastElement().add_cat(cat);
                    }
                    if(!(word.equals(""))){
                        collection.lastElement().add_word(word);
                    }
                   
                }
                else{
                    collection.addElement(new catBase(pun,cat,word));
                }
                
            }
            
        }catch(Exception err){
            System.out.println(err);
        }         
        
    }
    
}
