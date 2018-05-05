package com.example.android.pelicula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



public class choice extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Button b = (Button) findViewById(R.id.regButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(choice.this, RegPage.class);
           /* myIntent.putExtra("key", value);*/ //Optional parameters
                startActivity(myIntent);
            }
        });

        Button b1 = (Button) findViewById(R.id.signin);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(choice.this, LoginPage.class);
           /* myIntent.putExtra("key", value);*/ //Optional parameters
                startActivity(myIntent);
            }
        });
    }
}