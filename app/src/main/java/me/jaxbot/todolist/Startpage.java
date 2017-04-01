package me.jaxbot.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Startpage extends AppCompatActivity implements View.OnClickListener {
Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
     b=(Button)findViewById(R.id.button);

        b.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent();
        i.setClass(Startpage.this,MainActivity.class);
        startActivity(i);
    }
}
