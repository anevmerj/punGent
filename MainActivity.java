package com.example.mirna.pungent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.Display;
import android.content.res.AssetManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Color;
//import android.widget.GridLayout.LayoutParams;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.graphics.PorterDuff;
import android.view.ViewGroup.LayoutParams;
import android.net.Uri;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.String;
import java.io.File;

import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.Vector;
import java.util.Random;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity{

    EditText input;

    LinearLayout mainLayout;
    LinearLayout containerLayout;
    LinearLayout containerLayout2;
    LinearLayout containerLayout3;

    LayoutParams layoutParams;
    PopupWindow popUpWindow;

    LayoutParams layoutParams2;
    PopupWindow popUpWindow2;

    LayoutParams layoutParams3;
    PopupWindow popUpWindow3;

    Button pun_me;
    Button categories;
    Button ranpun;
    Button close;
    Button ok;
    Button my_puns;
    Button inv;
    Button cancel;
    Button image;

    TextView output;
    TextView warning;
    TextView test;

    Vector uservector;

    static Vector<catBase> collection = new Vector();
    int i = 0; //id for categories button
    int k = 0;
    int count = 0;

    //*******************ALEXEI***********
    //static boolean has_started = false;
    //static int first_time = 1;
    //**************************************

    //CATEGORIES STUFF STARTS
    static Vector<String> categoryVector = new Vector();
    static Vector<String> correspondingPuns = new Vector();

    static Vector<String> pickuplinesc4t = new Vector();
    static Vector<String> foodc4t = new Vector();
    static Vector<String> sciencec4t = new Vector();
    static Vector<String> celebritiesc4t = new Vector();
    static Vector<String> jokesc4t = new Vector();
    static Vector<String> animalsc4t = new Vector();
    static Vector<String> myPunsc4t = new Vector();
    static Vector<String> moviesc4t = new Vector();
    static Vector<String> holidayc4t = new Vector();
    static Vector<String> pokemonc4t = new Vector();
    //CATEGORIES STUFF ENDS

    String user_input;

    Display screen;

    static Context context;


    public void parser(){
        String sendToArray = null;
        String[] stuff = new String[3];
        String pun;
        String cat;
        String word;

        try{
            AssetManager am = getAssets();
            InputStream is = am.open("PunGen.txt");
            InputStreamReader readerFile = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(readerFile);
            int i = 0;
            while((sendToArray = br.readLine()) != null){

                stuff = sendToArray.split(",");
                pun = stuff[0];
                cat = stuff[1];
                if(stuff.length != 3){
                    word = "";
                }
                else {
                    word = stuff[2];
                }
                i++;
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
            is.close();

        }catch(IOException ex){
            ex.printStackTrace();
        }


    }

    //***************** ALEXEI'S SHIT************************
    /*public void parser(String fileName){
        String sendToArray = null;
        String[] stuff = new String[3];
        String pun = null;
        String cat = null;
        String word = null;
        if(fileName.equals("PunGen.txt")) {
            try {
                AssetManager am = getAssets();
                InputStream is = am.open(fileName);
                InputStreamReader readerFile = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(readerFile);
                int i = 0;
                while ((sendToArray = br.readLine()) != null) {

                    stuff = sendToArray.split(",");
                    pun = stuff[0];
                    cat = stuff[1];
                    if (stuff.length != 3) {
                        word = "";
                    } else {
                        word = stuff[2];
                    }
                    i++;
                    if (pun.equals("")) {
                        if (!(cat.equals(""))) {
                            collection.lastElement().add_cat(cat);
                        }
                        if (!(word.equals(""))) {
                            collection.lastElement().add_word(word);
                        }

                    } else {
                        collection.addElement(new catBase(cat, pun, word));
                    }
                    //readerFile.close();

                }

                is.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            try {
                InputStream is = openFileInput(fileName);
                if (is != null) {
                    InputStreamReader readerFile = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(readerFile);
                    while ((sendToArray = br.readLine()) != null) {

                        stuff = sendToArray.split(",");
                        pun = stuff[0];
                        cat = stuff[1];
                        if (stuff.length != 3) {
                            word = "";
                        } else {
                            word = stuff[2];
                        }
                        i++;
                        if (pun.equals("")) {
                            if (!(cat.equals(""))) {
                                collection.lastElement().add_cat(cat);
                            }
                            if (!(word.equals(""))) {
                                collection.lastElement().add_word(word);
                            }

                        } else {
                            collection.addElement(new catBase(cat, pun, word));
                        }
                        //readerFile.close();

                    }
                }
                is.close();
            }

            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }*/
    //************************************************************************

    String randomPunGenerator(Vector<String> possiblePuns){
        Random rand = new Random();
        if(possiblePuns.size() == 0){
            return "This category is punless";
        }
        else {
            int rnd_num = Math.abs(rand.nextInt() % possiblePuns.size());
            String rnd_pun = possiblePuns.elementAt(rnd_num);
            return rnd_pun;
        }
    }

    boolean primaryErrorCheck(String user_input){
        boolean errorFlag = true;
        user_input = user_input.replaceAll("\\s+","");
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
        for(int i = 0; i < collection.size(); i++){
            catBase collectionElement = collection.elementAt(i);
            Vector<String> c4t = collectionElement.get_cat();
            for(int j = 0; j < c4t.size(); j++){
                categoryVector.addElement(c4t.elementAt(j).toLowerCase());
                correspondingPuns.addElement(collection.elementAt(i).get_pun());
            }
        }
    }
    void fill_myPunsc4t (){
        for(int i = 0; i < collection.size(); i++){
            catBase collectionElement = collection.elementAt(i);
            Vector<String> c4t = collectionElement.get_cat();
            for(int j = 0; j < c4t.size(); j++){
                if(c4t.elementAt(j).equals("myPuns")) {
                    categoryVector.addElement(c4t.elementAt(j).toLowerCase());
                    correspondingPuns.addElement(collection.elementAt(i).get_pun());
                }
            }
        }
    }

    void fillCategoryVectors(){
        this.make_cat_and_pun_vectors();
        for(int x = 0; x < categoryVector.size(); x++){
            if(categoryVector.elementAt(x).equals("mypuns")){
                myPunsc4t.addElement(correspondingPuns.elementAt(x));
                continue;
            }
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
        //myPunsc4t.addElement(myPun);
        this.fill_myPunsc4t();
        try{
            FileOutputStream fos = openFileOutput("myPuns.txt", Context.MODE_APPEND);
            fos.write((myPun+",myPuns,").getBytes());
            fos.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screen = getWindowManager().getDefaultDisplay();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;


        final Typeface buttonFont = Typeface.createFromAsset(getAssets(), "Fonts/CurseCasual.ttf");


        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundColor(0xFFcde1f8);

        containerLayout3 = new LinearLayout(this);
        popUpWindow3 = new PopupWindow(this);
        layoutParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout3.setOrientation(LinearLayout.VERTICAL);
        popUpWindow3.setFocusable(true);

        containerLayout2 = new LinearLayout(this);
        popUpWindow2 = new PopupWindow(this);
        layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout2.setOrientation(LinearLayout.VERTICAL);
        popUpWindow2.setFocusable(true);

        containerLayout = new LinearLayout(this);
        popUpWindow = new PopupWindow(this);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        popUpWindow.setFocusable(true);


        ImageView title = new ImageView(this);
        title.setImageResource(R.drawable.pungent);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width,(int)(0.3*height));
        mainLayout.addView(title,lp);
        String hinter = new String("Enter your new pun here!");


        input = new EditText(getApplication());
        input.setHint("Enter your keyword or new pun here!");

        mainLayout.addView(input);
        input.setLayoutParams(new LinearLayout.LayoutParams(width,200));

        inv = new Button(this);
        inv.setBackgroundColor(Color.TRANSPARENT);

        image = new Button(this);
        //image.setBackgroundResource(R.drawable.pungent);

        my_puns = new Button(this);
        my_puns.getBackground().setColorFilter(0xFF41afe8, PorterDuff.Mode.MULTIPLY);
        my_puns.setText("A d d  T o  M y  P u n s");
        my_puns.setTextSize(25);
        my_puns.setTypeface(buttonFont);
        my_puns.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.1*height)));

        pun_me = new Button(this);
        pun_me.getBackground().setColorFilter(0xFFfc5e5e, PorterDuff.Mode.MULTIPLY);
        pun_me.setText("P u n  M e");
        pun_me.setTextSize(25);
        pun_me.setTypeface(buttonFont);
        pun_me.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.1*height)));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        pun_me.requestLayout();

        mainLayout.addView(pun_me);
        mainLayout.addView(my_puns);
        mainLayout.addView(inv);

        close = new Button(this);
        //close.setText("Close");
        close.setLayoutParams(params);
        close.setTypeface(buttonFont);
        close.setTextSize(16);
        close.setTextColor(0xffcccccc);
        //close.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //params2.setMargins(50, 50, 50, 100); //left, top, right, bottom
        params2.gravity = Gravity.CENTER;
        close.setLayoutParams(params2);


        ok = new Button(this);
        ok.setText("Ok");
        ok.setLayoutParams(params);
        ok.setTypeface(buttonFont);
        ok.setTextSize(16);
        ok.setTextColor(0xffcccccc);
        params2.gravity = Gravity.CENTER;
        ok.setLayoutParams(params2);

        cancel = new Button(this);
        cancel.setText("Cancel");
        cancel.setLayoutParams(params);
        cancel.setTypeface(buttonFont);
        cancel.setTextSize(16);
        cancel.setTextColor(0xffcccccc);
        params2.gravity = Gravity.CENTER;
        cancel.setLayoutParams(params2);

        categories = new Button(this);
        categories.setText("C a t e g o r i e s");
        categories.setTextSize(25);
        categories.setTypeface(buttonFont);
        categories.getBackground().setColorFilter(0xFFffcc00, PorterDuff.Mode.MULTIPLY);
        categories.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.1*height)));
        mainLayout.addView(categories);

        ranpun = new Button(this);
        ranpun.setText("R a n d o m  P u n");
        ranpun.setTextSize(25);
        ranpun.setTypeface(buttonFont);
        ranpun.getBackground().setColorFilter(0xFF46b551, PorterDuff.Mode.MULTIPLY);
        ranpun.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.1*height)));
        mainLayout.addView(ranpun);
        uservector = new Vector();

        output = new TextView(this);

        warning = new TextView(this);

        test = new TextView(this);
        warning = new TextView(this);

        this.parser();
        this.fillCategoryVectors();

        //****************************ALEXEI'S SHIT CONTINUES**********************
        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        first_time = sharedPref.getInt(R.string.preference_file_key);
        if(first_time == 1){// new addition
            File fileName = "mypuns.txt";
            fileName.delete();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(R.string.preference_file_key, first_time);
            editor.commit();
        }
        if(!(has_started)) {
            this.parser("PunGen.txt");
            this.parser("myPuns.txt");
            this.fillCategoryVectors();
            has_started = true;
        }*/
        //***************************

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

        pun_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error1, error2;
                Vector<String> possiblePuns = new Vector();
                String randomPun;

                user_input = input.getText().toString();

                close.setTextSize(25);
                close.setTypeface(buttonFont);

                popUpWindow.getBackground().setColorFilter(0xFFFFFF, PorterDuff.Mode.MULTIPLY);
                //output.setGravity(Gravity.CENTER_HORIZONTAL);
               // popUpWindow.setWidth(700);
                //popUpWindow.setHeight(500);

                if( user_input.equals("Craig") || user_input.equals("craig")){
                    Uri uriUrl = Uri.parse("https://www.youtube.com/watch?v=hbZZfQb4Olw");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
                else if( user_input.equals("Janice") || user_input.equals("janice")){
                    randomPun = "Alright!";
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    close.setText(randomPun);
                }
                else if( user_input.equals("Craig photo") || user_input.equals("craig photo")){
                    close.setBackgroundResource(R.drawable.craig);
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

                }
                else if(!(error1 = primaryErrorCheck(user_input))){
                    randomPun = "Your input must contain all letters";
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    close.setText(randomPun);
                } else if(!(error2 = Error_check(user_input, collection))){
                    randomPun = "Sorry, this word is not in our database.";
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    close.setText(randomPun);
                } else {
                    possiblePuns = punOut(user_input, collection);
                    randomPun = randomPunGenerator(possiblePuns);
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    close.setText(randomPun);
                }
            }
        });

        ranpun.setOnClickListener(new View.OnClickListener() { //Output a pun
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int rnd_num = Math.abs(rand.nextInt() % collection.size());
                close.setText(collection.elementAt(rnd_num).get_pun());
                //output.setText(collection.elementAt(rnd_num).get_pun());
                //popUpWindow.setWidth(700);
                //popUpWindow.setHeight(500);
                popUpWindow.getBackground().setColorFilter(0xFFFFFF, PorterDuff.Mode.MULTIPLY);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER,0, 0);
               // popUpWindow.update(0, 0, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL);  //postion x, position y, size x, size y
                close.setTextSize(25);
                close.setTypeface(buttonFont);
                //close.setGravity(Gravity.CENTER_HORIZONTAL);
                //output.setTextSize(25);
                //output.setTypeface(buttonFont);
                //output.setGravity(Gravity.CENTER_HORIZONTAL);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(user_input.length() < 90)
                input.setText("");
                popUpWindow.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_input = input.getText().toString();
               // String word;
                /*if(user_input.length() > 90){
                    String word = "You input is too long";
                    output.setText(word);
                    popUpWindow.setWidth(700);
                    popUpWindow.setHeight(500);
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    output.setTextSize(25);
                    output.setTypeface(buttonFont);
                    output.setGravity(Gravity.CENTER_HORIZONTAL);
                }
                else {}*/
                    add_to_myPuns(user_input);
                    input.setText("");

                popUpWindow2.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                popUpWindow2.dismiss();
            }
        });

        my_puns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow2.setWidth(700);
                popUpWindow2.setHeight(600);
                popUpWindow2.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                warning.setTextSize(25);
                warning.setTypeface(buttonFont);
                warning.setGravity(Gravity.CENTER_HORIZONTAL);
                warning.setText("Warning: Once pun is submitted, it cannot be pundone");
            }
        });

        inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count == 5) {
                    Uri uriUrl = Uri.parse("https://lucky1quotes.wordpress.com/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                    count = 0;
                }
                //test.setText(count);
            }
        });

        //mainLayout.addView(test);
       // containerLayout.addView(output);

        containerLayout.addView(close);
        containerLayout2.addView(warning);
        containerLayout2.addView(ok);
        containerLayout2.addView(cancel);
        containerLayout3.addView(image);
        popUpWindow3.setContentView(containerLayout3);
        popUpWindow.setContentView(containerLayout);
        popUpWindow2.setContentView(containerLayout2);
        setContentView(mainLayout);
    }
}
