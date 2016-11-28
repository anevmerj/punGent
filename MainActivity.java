package com.example.mirna.pungent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.Display;
import android.util.Log;
import android.content.res.AssetManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.String;
import android.widget.TextView;
import java.util.Vector;
import java.util.Random;
import java.io.StringReader;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity{


    EditText input;
    LinearLayout mainLayout;
    Button pun_me;
    Button categories;
    Button ranpun;

    TextView output;
    TextView test;

    Vector uservector;

    Vector<catBase> collection = new Vector();
    int i = 0; //id for categories button
    int k = 0;
    
    //CATEGORIES STUFF STARTS
    Vector<String> categoryVector = new Vector();
    Vector<String> correspondingPuns = new Vector();
    
    Vector<String> pickuplinesc4t = new Vector();
    Vector<String> foodc4t = new Vector();
    Vector<String> sciencec4t = new Vector();
    Vector<String> celebritesc4t = new Vector();
    Vector<String> jokesc4t = new Vector();
    Vector<String> animalsc4t = new Vector();
    Vector<String> myPunsc4t = new Vector();
    Vector<String> moviesc4t = new Vector();
    Vector<String> holidayc4t = new Vector();
    Vector<String> pokemonc4t = new Vector();
    //CATEGORIES STUFF ENDS

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

            //Reader readerFile = new FileReader(fileToRead);
            BufferedReader br = new BufferedReader(new StringReader("Do you have any raisins? No then what about a date?,Pick up Lines,date\nWhat do elves learn in school? The Elf-abet!,Holiday,christmas\nYou've got to be kitten me.,Animals,kitten\n,,Kidding\nAre you a beaver? Cause Dam.,Pick up Lines,damn\nIf you were a fruit you'd be a fineapple.,Pick up Lines,pineapple\nIf you were a vegetable you'd be a cutecumber.,Pick up Lines,cucumber\nAre you an angle from Heaven? Beause you're acute.,Pick up Lines,angel\nIf you were a chicken you'd be impeccable.,Pick up Lines,peck\nWhat does a nosy pepper do? Get jalapeno business.,Food,jalapeno\nHow do you kill a vegetarian vampire?  A steak.,Food,stake\n,,steak\nDid you hear about the Mexican train killer? He had locomotives,,loco\nHow does NASA orgnize their company parties? They planet.,Science,plan\nWhat did Jay-Z call his girlfriend before they got married? Feyonce.,Celebrities,beyonce\n,Jokes,\nWhat do you call dangerous precipitation? A rain of terror.,Science,reign\n,Jokes,\nWhy can't bikes stand on their own? They're two tired.,Jokes,too\nAtheism is a non-prophet organization,Jokes,profit\nWhat do you call a dinosaur with an extensive vocabulary? A Thesaurus.,Dinosaur,\n,Jokes,\nPampered cows produce spoiled milk,Animals,spoiled\nLearn sign language - it's very handy,Life Lessons,handy\nDry erase boards are remarkable,Life Lessons,mark\nDwarfs and midgets have very little in common,Life Lessons,little\nHow do you make holy water? Boil the hell out of it.,Jokes ,hell\nA friend tried to annoy me with bird puns but I learned that toucan play at that game.,Animals,two\nI'm no photographer but I can picture us together.,Pick up Lines,picture\nWhat is Forrest Gump's email password? 1forrest1,Jokes,run\n,Movies,\nWhat do you call people who are afraid of Santa Claus? Claustrophobic.,Holiday,claus\nWhich day do potatoes hate the most? Friday.,Animals,fry\nWhat do sea monsters eat for breakfast? Fish and ships.,Animals,chips\nWhich of Santa's reindeers needs to mind his manners the most? Rudeolph,Holiday,christmas\n"));
            while((sendToArray = br.readLine()) != null){
                //sendToArray = "Do you have any raisins? No then what about a date?,Pick up Lines,date\nWhat do elves learn in school? The Elf-abet!,Holiday,christmas\nYou've got to be kitten me.,Animals,kitten\n,,Kidding\n";
                stuff = sendToArray.split(",");
                pun = stuff[0];
                cat = stuff[1];
                word = stuff[2];
                if(pun.equals("")){
                    if(!(cat.equals(""))){
                        collection.lastElement().add_cat(cat);
                    }
                    if(!(word.equals(""))){
                        collection.lastElement().add_word(word);
                    }

                }
                else{
                    collection.addElement(new catBase(cat,pun,word));
                }
                //readerFile.close();

            }

        }catch(Exception err){
            String curDir = System.getProperty("user.dir");

            Log.d("error",curDir);
        }


    }

    String randomPunGenerator(Vector<String> possiblePuns){
        Random rand = new Random();
        int rnd_num = Math.abs(rand.nextInt() % possiblePuns.size());
        String rnd_pun = possiblePuns.elementAt(rnd_num);
        return rnd_pun;
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
        boolean true_flag = false;
        for(int i = 0;i<collection.size();i++){
            if(collection.elementAt(i).is_word(arg)){
                true_flag = true;
                //return true;
            }
        }
        return true_flag;
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
    
    void make_cat_and_pun_vectors(){
        for(int i = 0; i < collection.size(); i++){ //runs through every element of vector of pun obj
            Vector<String> c4t = new Vector();
            c4t = collection.elementAt(i).get_cat(); //creates vector of catrgories for specific pun
            for(int j = 0; j < c4t.size(); i++){ //adds an element to pun vec and cat vec for each unique pair of cat:pun
                categoryVector.addElement(c4t.elementAt(j).toLowerCase());
                correspondingPuns.addElement(collection.elementAt(i).get_pun());
            }
        }
    }
    
    void fillCategoryVectors(){
        make_cat_and_pun_vectors();
        for(int x = 0; x < categoryVector.size(); ++x){
                if(categoryVector.elementAt(x).equals("pick up lines")){
                    pickuplinesc4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
                if(categoryVector.elementAt(x).equals("food")){
                    foodc4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
                if(categoryVector.elementAt(x).equals("science")){
                    sciencec4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
                if(categoryVector.elementAt(x).equals("celebrities")){
                    celebritiesc4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
                if(categoryVector.elementAt(x).equals("jokes")){
                    jokesc4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
                if(categoryVector.elementAt(x).equals("pokemon")){
                    pokemonc4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
                if(categoryVector.elementAt(x).equals("animals")){
                    animalsc4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
                if(categoryVector.elementAt(x).equals("movies")){
                    moviesc4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
               if(categoryVector.elementAt(x).equals("holiday")){
                    holidayc4t.addElement(correspondingPuns.elementAt(x));
                    continue;
                }
        }
    }    
    
    void add_to_myPuns(String myPun){
        collection.addElement(new catBase(myPun));
        myPunsc4t.addElement(myPun);
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

        test = new TextView(this);
        mainLayout.addView(test);
        parser();
        fillCategoryVectors();

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

                //user_input = user_input.toLowerCase();
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
                //String empty_input = null;
                Random arand = new Random();
                int rnd_num = Math.abs(rand.nextInt() % collection.size());
                output.setText(collection.elementAt(rnd_num).get_pun());
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
