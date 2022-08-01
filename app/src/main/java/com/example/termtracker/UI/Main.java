package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.termtracker.DB.Repository;
import com.example.termtracker.R;

public class Main extends AppCompatActivity {

    private Repository repo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Repository getRepo() {
        return repo;
    }

    public void enterApp(View view) {
        System.out.println("Enter button pressed.");
        repo = new Repository(getApplication());
        Intent intent = new Intent(Main.this, TermList.class);
        startActivity(intent);
    }
}