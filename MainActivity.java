package com.example.shawnalee.pungen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.Display;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.lang.String;
import android.widget.TextView;
import java.util.Vector;
import java.util.Random;

public class MainActivity extends PunGen{


    EditText input;
    LinearLayout mainLayout;
    Button pun_me;
    Button categories;
    Button ranpun;
    TextView output;

    Vector uservector;

    Vector<catBase> collection = new Vector();
    int i = 0; //id for categories button
    int k = 0;

    String user_input;

    Display screen;

        public void parser(){

            String fileToRead = "PunGen.csv";
            String sendToArray = null;
            String[] stuff = new String[3];
            String pun;
            String cat;
            String word;

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


    boolean primaryErrorCheck(String user_input){
        boolean errorFlag = true;
        for(int i = 0; i < user_input.length(); i++)
        {
            if(!(Character.isLetter(user_input.charAt(i))))
            {
                errorFlag = false;
            }
        }
        return errorFlag;
    }

    boolean Error_check(String arg, Vector<catBase> collection){
        for(int i = 0;i<collection.size();i++){
            if(collection.elementAt(i).is_word(arg)){
                return true;
            }
        }
        return false;
    }

    public Vector<String> punOut(String word, Vector<catBase> collection){
        Vector<String> puns = new Vector();
        for(int i = 0;i<collection.size();i++){
            if(collection.elementAt(i).is_word(word)){
                puns.add(collection.elementAt(i).get_pun());
            }
        }
        return puns;
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screen = getWindowManager().getDefaultDisplay();

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        input = new EditText(getApplication());
        input.setLayoutParams(new LinearLayout.LayoutParams(450,100));
        mainLayout.addView(input);

        pun_me = new Button(this);
        pun_me.setText("Pun Me");
        mainLayout.addView(pun_me);

        categories = new Button(this);
        categories.setText("Categories");
        mainLayout.addView(categories);

        ranpun = new Button(this);
        ranpun.setText("Random Pun");
        mainLayout.addView(ranpun);

        uservector = new Vector();

        output = new TextView(this);
        mainLayout.addView(output);

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categories.setId(i);
                if(v.getId() == i){
                    Intent i = new Intent(getApplicationContext(), categories.class);
                    startActivity(i);
                }
            }
        });


        /*pun_me.setOnClickListener(new View.OnClickListener() { //Needs to check error in user input. It will output either a pun or an error
            @Override
            public void onClick(View v) {
                user_input = input.getText().toString();
            }
        });*/
            pun_me.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean error1, error2;
                    Vector<String> possiblePuns = new Vector();
                    String randomPun;

                    user_input = input.getText().toString();
                    //stringinput.setText(user_input);

                     user_input = user_input.toLowerCase();
                    if(!(error1 = primaryErrorCheck(user_input))){
                        randomPun = "Your input is not a word!";
                    } else if(!(error2 = Error_check(user_input, collection))){
                        randomPun = "Sorry, this word is not in our database.";
                    } else {
                        possiblePuns = punOut(user_input, collection);
                        randomPun = randomPunGenerator(possiblePuns);
                    }
                    output.setText(randomPun);
                }
            });

        ranpun.setOnClickListener(new View.OnClickListener() { //Output a pun
            @Override
            public void onClick(View v) {
                Vector<String> puns = new Vector();
                for(int i = 0;i<collection.size();i++){
                        puns.add(collection.elementAt(i).get_pun());
                }
                output.setText(randomPunGenerator(puns));

            }
        });

        setContentView(mainLayout);
    }

  /*  public void onClick(View v){
        //categories.setId(i);
        if(v.getId() == R.id.BCat){
            Intent i = new Intent(getApplicationContext(), categories.class);
            startActivity(i);
        }
    }*/

}
