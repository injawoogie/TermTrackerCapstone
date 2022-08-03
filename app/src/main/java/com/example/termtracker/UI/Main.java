package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.termtracker.DB.Repository;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Instructor;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Helper.Utility;
import com.example.termtracker.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Repository repo = new Repository(getApplication());
//        Instructor instructor = new Instructor("Instructor Name", "(520)555-5555", "instructor@email.com");
//        repo.insert(instructor);
//        repo.insert(new Term("Term Title", Utility.localDateToString(LocalDate.now()), Utility.localDateToString(LocalDate.now())));
//        repo.insert(new Course("Course Title", Utility.localDateToString(LocalDate.now()), Utility.localDateToString(LocalDate.now()), Course.IN_PROGRESS, instructor.getId()));
        setContentView(R.layout.activity_main);
    }

    public void enterApp(View view) {
        System.out.println("Enter button pressed.");
        Intent intent = new Intent(Main.this, TermList.class);
        startActivity(intent);
    }
}