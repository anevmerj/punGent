package com.example.mirna.pungent;

import android.content.SharedPreferences;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.view.Display;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.String;
import java.util.Vector;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    EditText input;

    LinearLayout mainLayout;
    LinearLayout containerLayout;
    LinearLayout containerLayout2;
    LinearLayout containerLayout3;

    LayoutParams layoutParams;
    PopupWindow popUpWindow; //normal pop up with only the close button

    LayoutParams layoutParams2;
    PopupWindow popUpWindow2; //pop up for "add to my puns"

    LayoutParams layoutParams3;
    PopupWindow popUpWindow3; //pop up for images

    Button pun_me;
    Button categories;
    Button ranpun;
    Button close; //button for general pop up
    Button my_puns;
    Button inv; //invisble button
    Button image; //button for hidden feature (image pop up)

    TextView warning;//warning message on the pop up for add to my puns

    Vector uservector;
    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;

    static Vector<catBase> collection = new Vector();
    int i = 0; //id for categories button
    int k = 0;
    int count = 0; //counter for invisble button

    static boolean has_started = false;
    int first_time;

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

    Context context; // changed to be non static


    public void parser(String fileName){
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
    }

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
//        if(user_input.length() == 0){ // used for error checking empty strings or strings of all spaces after space removal
//            errorFlag = false;        // can be reimplemented once empty string input below is defined in terms of function
//        }
        return errorFlag;
    }

    String remove_end_spaces(String user_input){
        String new_input = user_input;
        boolean last_space = true;
        if(user_input.length() == 0){
            return "";
        }
        for(int i = 0; last_space; i++){
            String last_char = Character.toString(new_input.charAt(new_input.length()-1));
            if(!(last_char.equals(" ")||last_char.equals("\n"))){
                last_space = false;
            }
            else{
                if(new_input.length()==1){
                    return "";
                }
                new_input = new_input.substring(0, new_input.length()-1);
            }
        }
        return new_input;
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
                //if(!c4t.elementAt(j).equals("Alexei")) {
                categoryVector.addElement(c4t.elementAt(j).toLowerCase());
                correspondingPuns.addElement(collection.elementAt(i).get_pun());
                //}
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
        myPunsc4t.addElement(myPun);
        this.fill_myPunsc4t();
        try{
            FileOutputStream fos = openFileOutput("myPuns.txt", Context.MODE_APPEND);
            fos.write((myPun+",myPuns,\n").getBytes());
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
        int width = size.x; //width of users phone
        int height = size.y; //height of users phone
        final int popUpHeight = (height/3);
        final int popUpWidth = (int)(width/1.5);

        //To set the font
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
        popUpWindow.getBackground().setColorFilter(0xFFFFFF, PorterDuff.Mode.MULTIPLY);


        ImageView title = new ImageView(this);
        title.setImageResource(R.drawable.pungent);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width,(int)(0.3*height));
        mainLayout.addView(title,lp);


        input = new EditText(getApplication());
        input.setHint("Enter your keyword or new pun here!");
        input.setHintTextColor(0xff666666);
        input.setTextColor(0xff000000);
        input.getBackground().setColorFilter(0xFF9e63e0, PorterDuff.Mode.SRC_ATOP);

        mainLayout.addView(input);
        input.setLayoutParams(new LinearLayout.LayoutParams(width,200));

        inv = new Button(this);
        inv.setBackgroundColor(Color.TRANSPARENT); //sets button invisible but still clickable

        image = new Button(this);

        my_puns = new Button(this);
        my_puns.getBackground().setColorFilter(0xFF41afe8, PorterDuff.Mode.MULTIPLY);
        my_puns.setText("M y  P u n s");
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

        mainLayout.addView(pun_me);
        mainLayout.addView(inv);
        mainLayout.addView(my_puns);

        close = new Button(this);
        close.setLayoutParams(params);
        close.setTypeface(buttonFont);
        //close.setTextSize(16);
        close.setTextColor(0xffcccccc);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.CENTER;
        close.setLayoutParams(params2);
        close.setTextSize(25);
        //close.setTypeface(buttonFont);

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

        warning = new TextView(this);

        preferenceSettings = this.getPreferences(Context.MODE_PRIVATE);
        first_time = preferenceSettings.getInt("first_time",1);
        if(first_time == 1){
            first_time = 0;
            //File fileName = new File("/data/user/0/com.example.mirna.pungent/files/myPuns.txt");
            //fileName.delete();
            //context.deleteFile("myPuns.txt");
            try{
                FileOutputStream fos = openFileOutput("myPuns.txt", Context.MODE_PRIVATE);
                fos.write(("").getBytes());
                fos.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            preferenceEditor = preferenceSettings.edit();
            preferenceEditor.putInt("first_time", first_time);
            preferenceEditor.commit();
        }
        if(!(has_started)) {
            this.parser("PunGen.txt");
            this.parser("myPuns.txt");
            this.fillCategoryVectors();
            has_started = true;
        }

        //categories button opens new activity
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

        //pun me button with hidden keywords
        pun_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error1, error2;
                Vector<String> possiblePuns = new Vector();
                String randomPun;

                user_input = input.getText().toString();
                user_input = user_input.toLowerCase();
                user_input = remove_end_spaces(user_input);


                if( user_input.equals("")){// add message for blank text box or all spaces input
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    close.setText("Please input a word");
                }
                else if( user_input.equals("Craig") || user_input.equals("craig")){
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
                    image.setBackgroundResource(R.drawable.craig);
                    popUpWindow3.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

                }
                else if( user_input.equals("Craig photo Scarlet") || user_input.equals("craig photo scarlet")){
                    image.setBackgroundResource(R.drawable.craig_pic_scarlet);
                    popUpWindow3.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

                }
                else if(!(error1 = primaryErrorCheck(user_input))){
                    randomPun = "Your input must contain all letters";
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    close.setText(randomPun);

                } else if(user_input.equals("Alexei") || user_input.equals("alexei")) {
                    possiblePuns = punOut("Alexei", collection);
                    randomPun = randomPunGenerator(possiblePuns);
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    close.setText(randomPun);

                } else if(!(error2 = Error_check(user_input, collection))){
                    randomPun = "Sorry, this word/phrase is not in our database.";
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

        //random pun generator button
        ranpun.setOnClickListener(new View.OnClickListener() { //Output a pun
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int rnd_num = Math.abs(rand.nextInt() % collection.size());
                String cat = collection.elementAt(rnd_num).get_cat_string();

                while(cat.equals("Alexei")){
                    rnd_num = Math.abs(rand.nextInt() % collection.size());
                    cat = collection.elementAt(rnd_num).get_cat_string();
                }

                String pun = collection.elementAt(rnd_num).get_pun();
                close.setText(pun);
                popUpWindow.getBackground().setColorFilter(0xFFFFFF, PorterDuff.Mode.MULTIPLY);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER,0, 0);
                close.setTextSize(25);
                close.setTypeface(buttonFont);
            }
        });

        //dismiss pop up and clear input when closed button is pressed
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                popUpWindow.dismiss();
            }
        });

        //dismiss pop up and clear input when image button is pressed
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                popUpWindow3.dismiss();
            }
        });


        //pop up for add to my puns button
        my_puns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*my_puns.setId(i);
                if(v.getId() == i){
                    Intent i = new Intent(getApplicationContext(), mypuns.class);
                    startActivity(i);

                }*/
                /*user_input = input.getText().toString();
                user_input = remove_end_spaces(user_input);
                if(user_input.equals("")) {
                    popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    close.setText("Please input a pun");
                }
                else{
                    popUpWindow2.setWidth(popUpWidth);
                    popUpWindow2.setHeight(popUpHeight);
                    popUpWindow2.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                    warning.setTextSize(25);
                    warning.setTypeface(buttonFont);
                    warning.setGravity(Gravity.CENTER_HORIZONTAL);
                    warning.setText("WARNING: ONCE PUN IS SUBMITTED, IT CANNOT BE PUNDONE");
                }*/
            }
        });

        //Invisble button
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
            }
        });

        //when popup is dismissed
        popUpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                input.setText("");
            }
        });

        //when popup for add to my puns is dismissed
        popUpWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                input.setText("");
            }
        });

        //when popup for image is dismissed
        popUpWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                input.setText("");
            }
        });

        containerLayout.addView(close);
        containerLayout2.addView(warning);
        containerLayout3.addView(image);
        popUpWindow3.setContentView(containerLayout3);
        popUpWindow.setContentView(containerLayout);
        popUpWindow2.setContentView(containerLayout2);
        setContentView(mainLayout);
    }
}
