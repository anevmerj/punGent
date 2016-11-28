package com.example.mirna.pungent;
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

    TextView ran_pun;

    Display screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        pickuplines = new Button(this);
        pickuplines.setText("Pick Up Lines");
        mainLayout.addView(pickuplines);

        food = new Button(this);
        food.setText("Food");
        mainLayout.addView(food);

        science = new Button(this);
        science.setText("Science");
        mainLayout.addView(science);

        celebrities = new Button(this);
        celebrities.setText("Celebrities");
        mainLayout.addView(celebrities);

        jokes = new Button(this);
        jokes.setText("Jokes");
        mainLayout.addView(jokes);

        animals = new Button(this);
        animals.setText("Animals");
        mainLayout.addView(animals);

        movies = new Button(this);
        movies.setText("Movies");
        mainLayout.addView(movies);

        holiday = new Button(this);
        holiday.setText("Holiday");
        mainLayout.addView(holiday);

        pokemon = new Button(this);
        pokemon.setText("Pokemon");
        mainLayout.addView(pokemon);

        myPuns = new Button(this);
        myPuns.setText("My Puns");
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
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(pickuplinesc4t);
                ran_pun.setText(pun);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(foodc4t);
                ran_pun.setText(pun);
            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(sciencec4t);
                ran_pun.setText(pun);
            }
        });

        celebrities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(celebritiesc4t);
                ran_pun.setText(pun);
            }
        });

        jokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(jokesc4t);
                ran_pun.setText(pun);
            }
        });

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(animalsc4t);
                ran_pun.setText(pun);
            }
        });

        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(moviesc4t);
                ran_pun.setText(pun);
            }
        });

        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(holidayc4t);
                ran_pun.setText(pun);
            }
        });

        pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpWindow.showAtLocation(mainLayout, Gravity.CENTER, 10, 10);
                popUpWindow.update(100, 100, 700, 500);  //postion x, position y, size x, size y
                String pun = randomPunGenerator(pokemonc4t);
                ran_pun.setText(pun);
            }
        });


        containerLayout.addView(ran_pun);
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