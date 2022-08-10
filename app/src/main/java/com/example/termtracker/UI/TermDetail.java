package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.termtracker.Database.Repository;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Helper.DaPicker;
import com.example.termtracker.R;

public class TermDetail extends AppCompatActivity {

    EditText termIdEditText;
    EditText termTitleEditText;
    EditText termStartEditText;
    EditText termEndEditText;

    int termId;
    String termTitle;
    String termStart;
    String termEnd;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        initializeRepo();
        initializeView();
        getValuesFromView();

    }

    private void initializeRepo() {
        repo = new Repository(getApplication());
    }

    private void initializeView() {

        termIdEditText = findViewById(R.id.termiDEditText);
        termIdEditText.setText(String.valueOf(getIntent().getIntExtra("id", -1)));
        termIdEditText.setEnabled(false);

        termTitleEditText = findViewById(R.id.termTitleEditText);
        termTitleEditText.setText(getIntent().getStringExtra("title"));

        termStartEditText = findViewById(R.id.termStartDateEditText);
        termStartEditText.setText(getIntent().getStringExtra("startDate"));

        termEndEditText = findViewById(R.id.termEndDateEditText);
        termEndEditText.setText(getIntent().getStringExtra("endDate"));

        DaPicker startPicker = new DaPicker(TermDetail.this, termStartEditText);
        startPicker.activate();

        DaPicker endPicker = new DaPicker(TermDetail.this, termEndEditText);
        endPicker.activate();


    }

    private void getValuesFromView() {

        termId = Integer.parseInt(String.valueOf(termIdEditText.getText()));
        termTitle = String.valueOf(termTitleEditText.getText());
        termStart = String.valueOf(termStartEditText.getText());
        termEnd = String.valueOf(termEndEditText.getText());

    }

    public void saveButton(View view) {
        getValuesFromView();

        Term term = repo.getTermByID(termId);

        // if term from db returns null make a new term.
        if (term == null) {
            term = new Term(termTitle, termStart, termEnd);
            System.out.println(term);

        // if term from db exists, update the term
        } else {
            term.setTitle(termTitle);
            term.setStartDate(termStart);
            term.setEndDate(termEnd);
            System.out.println(term);
        }
        repo.inOrUp(term);
        this.finish();
    }

    public void toCourseDetail(View view) {
        Intent intent = new Intent(TermDetail.this, CourseDetail.class);
        startActivity(intent);
    }
}