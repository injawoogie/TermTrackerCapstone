package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.termtracker.DB.Repository;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.R;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterApp(View view) {
        System.out.println("Enter button pressed.");

        Repository repo = new Repository(getApplication());
        repo.insert(new Term(1, "First Title", (int) System.currentTimeMillis(), (int) System.currentTimeMillis() + 1000));
        repo.insert(new Course(1, "First Course", (int) System.currentTimeMillis(), (int) (System.currentTimeMillis() + 1000), "in progress"));

        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }
}