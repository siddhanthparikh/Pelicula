package com.example.android.pelicula;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by kavit on 21-09-2017.
 */
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.StringTokenizer;
import android.view.Menu;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent i = getIntent();
        email = i.getStringExtra(email);



    }
}
