package com.example.mirna.pungent;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class categories extends MainActivity {
    LinearLayout containerLayout;
    LinearLayout mainLayout;


    LayoutParams layoutParams;
    PopupWindow popUpWindow;

    Button pickuplines;
    Button food;
    Button science;
    Button celebrities;
    Button jokes;
    Button animals;
    Button movies;
    Button holiday;
    Button pokemon;
    Button myPuns;
    Button close;

    TextView ran_pun;

    Display screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundColor(0xFFcde1f8);
        final Typeface buttonFont = Typeface.createFromAsset(getAssets(), "Fonts/CurseCasual.ttf");
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        final int popUpHeight = (int)(height/2);
        final int popUpWidth = (int)(width/2);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        close = new Button(this);
        close.setText("Close");
        close.setLayoutParams(params);
        //LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        close.setLayoutParams(params);


        pickuplines = new Button(this);
        pickuplines.setText("P i c k  U p  L i n e s");
        pickuplines.setTextSize(25);
        pickuplines.getBackground().setColorFilter(0xFFfc5e5e, PorterDuff.Mode.MULTIPLY);
        pickuplines.setTypeface(buttonFont);
        pickuplines.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(pickuplines);

        food = new Button(this);
        food.setText("F o o d");
        food.setTextSize(25);
        food.setTypeface(buttonFont);
        food.getBackground().setColorFilter(0xFFff9c00, PorterDuff.Mode.MULTIPLY);
        food.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(food);

        science = new Button(this);
        science.setText("S c i e n c e");
        science.setTextSize(25);
        science.setTypeface(buttonFont);
        science.getBackground().setColorFilter(0xFF46b551, PorterDuff.Mode.MULTIPLY);
        science.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(science);

        celebrities = new Button(this);
        celebrities.setText("C e l e b r i t i e s");
        celebrities.setTextSize(25);
        celebrities.setTypeface(buttonFont);
        celebrities.getBackground().setColorFilter(0xFF41afe8, PorterDuff.Mode.MULTIPLY);
        celebrities.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(celebrities);

        jokes = new Button(this);
        jokes.setText("J o k e s");
        jokes.setTextSize(25);
        jokes.setTypeface(buttonFont);
        jokes.getBackground().setColorFilter(0xFF6963e0, PorterDuff.Mode.MULTIPLY);
        jokes.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(jokes);

        animals = new Button(this);
        animals.setText("A n i m a l s");
        animals.setTextSize(25);
        animals.setTypeface(buttonFont);
        animals.getBackground().setColorFilter(0xFF9e63e0, PorterDuff.Mode.MULTIPLY);
        animals.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(animals);

        movies = new Button(this);
        movies.setText("M o v i e s");
        movies.setTextSize(25);
        movies.setTypeface(buttonFont);
        movies.getBackground().setColorFilter(0xFFfc5e5e, PorterDuff.Mode.MULTIPLY);
        movies.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(movies);

        holiday = new Button(this);
        holiday.setText("H o l i d a y");
        holiday.setTextSize(25);
        holiday.setTypeface(buttonFont);
        holiday.getBackground().setColorFilter(0xFFff9c00, PorterDuff.Mode.MULTIPLY);
        holiday.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(holiday);

        pokemon = new Button(this);
        pokemon.setText("P o k e m o n");
        pokemon.setTextSize(25);
        pokemon.setTypeface(buttonFont);
        pokemon.getBackground().setColorFilter(0xFFffcc00, PorterDuff.Mode.MULTIPLY);
        pokemon.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(pokemon);

        myPuns = new Button(this);
        myPuns.setText("M y  P u n s");
        myPuns.setTextSize(25);
        myPuns.setTypeface(buttonFont);
        myPuns.getBackground().setColorFilter(0xFF46b551,PorterDuff.Mode.MULTIPLY);
        myPuns.setLayoutParams(new LinearLayout.LayoutParams(width,(int)(0.095*height)));
        mainLayout.addView(myPuns);

        containerLayout = new LinearLayout(this);
        popUpWindow = new PopupWindow(this);

        ran_pun = new TextView(this);

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        popUpWindow.setFocusable(true);

        screen = getWindowManager().getDefaultDisplay();

        pickuplines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(pickuplinesc4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);
                ran_pun.setText(pun);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(foodc4t);
                ran_pun.setText(pun);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);

            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(sciencec4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);

                ran_pun.setText(pun);
            }
        });

        celebrities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(celebritiesc4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);
                ran_pun.setText(pun);
            }
        });

        jokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(jokesc4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);
                ran_pun.setText(pun);
            }
        });

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(animalsc4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);
                ran_pun.setText(pun);
            }
        });

        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(moviesc4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);
                ran_pun.setText(pun);
            }
        });

        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(holidayc4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);
                ran_pun.setText(pun);
            }
        });

        pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(pokemonc4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);
                ran_pun.setText(pun);
            }
        });

        myPuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.setWidth(700);
                popUpWindow.setHeight(500);
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
                String pun = randomPunGenerator(myPunsc4t);
                ran_pun.setTextSize(25);
                ran_pun.setTypeface(buttonFont);
                ran_pun.setGravity(Gravity.CENTER_HORIZONTAL);
                ran_pun.setText(pun);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.dismiss();
            }
        });

        containerLayout.addView(ran_pun);
        containerLayout.addView(close);
        popUpWindow.setContentView(containerLayout);
        setContentView(mainLayout);
    }

    /*public void onPickuplines(View v) {
        //categories.setId(i);
        if (v.getId() == R.id.Bpickuplines) {
            //Intent i = new Intent(getApplicationContext(), pickuplines.class);
            //startActivity(i);
            popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
            popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
        }
    }*/
}
