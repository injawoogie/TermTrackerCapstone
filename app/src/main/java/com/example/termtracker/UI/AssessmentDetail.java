package com.example.termtracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.termtracker.Database.Repository;
import com.example.termtracker.Entity.Assessment;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Helper.DaPicker;
import com.example.termtracker.Helper.Notify;
import com.example.termtracker.R;

import java.util.Locale;

public class AssessmentDetail extends AppCompatActivity {

    Repository repo;

    EditText assessmentTitleEditText;
    EditText assessmentStartEditText;
    EditText assessmentEndEditText;
    Spinner testTypeSpinner;

    boolean isNewAssessment = false;
    Assessment assessment;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);

        initializeRepo();
        initializeView();
        getCourseAttributesFromIntent();
        setTextForViews();
        readElements();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_assessment_detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                System.out.println("home selected in assessment detail");
                goToCourseDetail();
                return true;
            case R.id.notify:

                String date = assessmentStartEditText.getText().toString();
                String startDate = assessmentStartEditText.getText().toString();
                String startMsg = String.format(Locale.US, "Assessment: %s starts today, %s", assessment.getTitle(), startDate);
                Notify.run(this, startDate, startMsg);

                String endDate = assessmentEndEditText.getText().toString();
                String endMsg = String.format("Assessment: %s ends today, %s", assessment.getTitle(), endDate);
                Notify.run(this, endDate, endMsg);

                return true;

            case R.id.deleteAssessment:
                repo.delete(assessment);
                goToCourseDetail();
                return true;

            case R.id.logout:
                startActivity(new Intent(this, Login.class));
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeRepo() {
        repo = new Repository(getApplication());
    }

    private void initializeView() {

        assessmentTitleEditText = findViewById(R.id.assessmentNameEditText);
        assessmentStartEditText = findViewById(R.id.assessmentStartDateEditText);
        assessmentEndEditText = findViewById(R.id.assessmentEndDateEditText);
        testTypeSpinner = findViewById(R.id.testTypeStatusSpinner);

    }

    private void getCourseAttributesFromIntent() {

        assessment = repo.getAssessmentByID(getIntent().getIntExtra(Assessment.ID_KEY, -1));
        if(assessment == null) {
            assessment = new Assessment();
            isNewAssessment = true;

        }
        // TODO: Fix
        course = repo.getCourseByID(getIntent().getIntExtra(Course.ID_KEY, -1));
        assessment.setCourseId_FK(course.getId());

    }

    private void setTextForViews() {

        assessmentTitleEditText.setText(assessment.getTitle());
        assessmentStartEditText.setText(assessment.getStartDate());
        assessmentEndEditText.setText(assessment.getEndDate());

        DaPicker startPicker = new DaPicker(AssessmentDetail.this, assessmentStartEditText);
        startPicker.activate();
        DaPicker endPicker = new DaPicker(AssessmentDetail.this, assessmentEndEditText);
        endPicker.activate();

        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Assessment.TEST_TYPES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        testTypeSpinner.setAdapter(adapter);
        int pos = adapter.getPosition(String.valueOf(assessment.getTestType()));
        testTypeSpinner.setSelection(pos, true);

    }

    private void readElements() {

        assessment.setTitle(String.valueOf(assessmentTitleEditText.getText()));
        assessment.setStartDate(String.valueOf(assessmentStartEditText.getText()));
        assessment.setEndDate(String.valueOf(assessmentEndEditText.getText()));
        assessment.setTestType(testTypeSpinner.getSelectedItem().toString());

    }

    public void saveButton(View view) {

        // go back to term detail for term id.
        readElements();
        repo.inOrUp(assessment);
        goToCourseDetail();
    }

    private void goToCourseDetail() {
        Intent intent = new Intent(this, CourseDetail.class);
        intent.putExtra(Course.ID_KEY, course.getId());
        intent.putExtra(Term.ID_KEY, course.getTermId_FK());
        this.startActivity(intent);
    }
}