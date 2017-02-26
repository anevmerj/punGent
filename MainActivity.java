package com.example.mirna.intro;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;
import android.content.Intent;
import android.widget.RelativeLayout;



public class MainActivity extends AppCompatActivity {
    RelativeLayout mainLayout;

    Button todolist;
    Button qcards;
    Button notes;
    Button agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = new RelativeLayout(this);
        //mainLayout.set;
        mainLayout.setBackgroundColor(0xFFcde1f8);

        todolist = (Button)findViewById(R.id.Btodolistpage);

        qcards = (Button)findViewById(R.id.Bqcardspage);

        notes = (Button)findViewById(R.id.Bnotespage);

        agenda = (Button)findViewById(R.id.Bagendapage);
        //agenda.getBackground().setColorFilter(0xFFfc5e5e, PorterDuff.Mode.MULTIPLY);

        //mainLayout.addView(agenda);
       // mainLayout.addView(notes);
       // mainLayout.addView(todolist);
       // mainLayout.addView(qcards);
        //setContentView(mainLayout);


    }
    public void onNotesClick(View v){
        if(v.getId() == R.id.Bnotespage){
            Intent i = new Intent(MainActivity.this, notespage.class);
            startActivity(i);
        }
    }

    public void onAgendaClick(View v){
        if(v.getId() == R.id.Bagendapage){
            Intent i = new Intent(MainActivity.this, agendapage.class);
            startActivity(i);
        }
    }
    public void onQcardsClick(View v){
        if(v.getId() == R.id.Bqcardspage){
            Intent i = new Intent(MainActivity.this, qcardspage.class);
            startActivity(i);
        }
    }
    public void onTodolistClick(View v){
        if(v.getId() == R.id.Btodolistpage){
            Intent i = new Intent(MainActivity.this, todolistpage.class);
            startActivity(i);
        }
    }
    public void onAddClick(View v){
        if(v.getId() == R.id.Btodolistpage){
            RelativeLayout relative = (RelativeLayout) findViewById(R.id.activity_main);
            CheckBox newcheckbox = new CheckBox(this);
            relative.addView(newcheckbox);
            //Intent i = new Intent(MainActivity.this, todolistpage.class);
            //startActivity(i);
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
