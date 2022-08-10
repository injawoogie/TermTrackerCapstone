package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Helper.Utility;
import com.example.termtracker.R;

import com.example.termtracker.Database.Repository;

import java.time.LocalDate;

public class Main extends AppCompatActivity {

    public static int alertNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Repository repo = new Repository(getApplication());

//        String title, String instructorName, String instructorEmail, String startDate, String endDate, String status, String note
//        Course course = new Course(
//                "Course 1",
//                "Billy Bob",
//                "billybob@wgu.edu",
//                Utility.localDateToString(LocalDate.now()),
//                Utility.localDateToString(LocalDate.now()),
//                Course.IN_PROGRESS,
//                "Some Notes go here."
//                );
//
//        repo.insert(course);
//
//        repo.insert(new Term("Term Title", Utility.localDateToString(LocalDate.now()), Utility.localDateToString(LocalDate.now())));
//        repo.insert(new Course(
//                "Course 2",
//                "Billy Bob Jr.",
//                "billybobjr@wgu.edu",
//                Utility.localDateToString(LocalDate.now()),
//                Utility.localDateToString(LocalDate.now()),
//                Course.IN_PROGRESS,
//                "Some Notes go here."
//                ));

        setContentView(R.layout.activity_main);
    }

    public void enterApp(View view) {
        System.out.println("Enter button pressed.");
        Intent intent = new Intent(Main.this, TermList.class);
        startActivity(intent);
    }
}