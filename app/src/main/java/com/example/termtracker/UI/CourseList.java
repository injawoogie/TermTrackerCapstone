package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.termtracker.Database.Repository;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.R;

public class CourseList extends AppCompatActivity {

    EditText courseIdEditText;
    EditText courseTitleEditText;
    EditText courseStartEditText;
    EditText courseEndEditText;

    int courseId;
    String courseTitle;
    String courseStart;
    String courseEnd;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        initializeRepo();
        initializeView();
        getValuesFromView();

    }

    private void initializeRepo() {
        repo = new Repository(getApplication());
    }

    private void initializeView() {

        courseIdEditText = findViewById(R.id.courseIdEditText);
        courseIdEditText.setText(String.valueOf(getIntent().getIntExtra("id", -1)));
        courseIdEditText.setEnabled(false);

        courseTitleEditText = findViewById(R.id.courseTitleEditText);
        courseTitleEditText.setText(getIntent().getStringExtra("title"));

        courseStartEditText = findViewById(R.id.courseStartDateEditText);
        courseStartEditText.setText(getIntent().getStringExtra("startDate"));

        courseEndEditText = findViewById(R.id.ourseEndDateEditText);
        courseEndEditText.setText(getIntent().getStringExtra("endDate"));


    }

    private void getValuesFromView() {

        courseId = Integer.parseInt(String.valueOf(courseIdEditText.getText()));
        courseTitle = String.valueOf(courseTitleEditText.getText());
        courseStart = String.valueOf(courseStartEditText.getText());
        courseEnd = String.valueOf(courseEndEditText.getText());

    }

    public void saveButton(View view) {
        getValuesFromView();

        Term term = repo.getByID(courseId);

        // if term from db returns null make a new term.
        if (term == null) {
            term = new Term(courseTitle, courseStart, courseEnd);
            System.out.println(term);

        // if term from db exists, update the term
        } else {
            term.setTitle(courseTitle);
            term.setStartDate(courseStart);
            term.setEndDate(courseEnd);
            System.out.println(term);
        }
        repo.inOrUp(term);
        Intent intent = new Intent(view.getContext(), TermList.class);
        startActivity(intent);
    }

}