package com.example.shawnalee.pungen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.Display;
import java.lang.String;
import android.widget.TextView;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    EditText input;
    LinearLayout mainLayout;
    Button pun_me;
    Button categories;
    Button ranpun;
    TextView output;

    Vector uservector;

    int i = 0; //id for categories button
    int k = 0;

    String user_input;

    Display screen;
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


        pun_me.setOnClickListener(new View.OnClickListener() { //Needs to check error in user input. It will output either a pun or an error
            @Override
            public void onClick(View v) {
                user_input = input.getText().toString();
            }
        });

        ranpun.setOnClickListener(new View.OnClickListener() { //Output a pun
            @Override
            public void onClick(View v) {
                output.setText();
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
