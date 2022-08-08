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
import com.example.termtracker.Entity.Instructor;

import java.time.LocalDate;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Repository repo = new Repository(getApplication());
//        Instructor instructor = new Instructor("Instructor Name 1", "(520)555-5557", "instructor@email.com");
//        System.out.println(instructor);
//        System.out.println(repo.getInstructorBy(instructor.getName()));

        // deletes the first instance
//        repo.inOrUp(instructor);
//
//        Instructor instructor5 = new Instructor("Instructor Name 5", "(520)555-5556", "instructor@email.com");
//        repo.inOrUp(instructor5);
//
//        Instructor inst = repo.getInstructorBy(instructor5.getName());
//
//        Course course = new Course("Course 1", Utility.localDateToString(LocalDate.now()), Utility.localDateToString(LocalDate.now()), Course.IN_PROGRESS, inst.getId());
//        repo.insert(course);
//        repo.insert(instructor);

//        Instructor instructor = repo.getInstructorByName("Instructor Name 1");
//        System.out.println(instructor.getName());

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