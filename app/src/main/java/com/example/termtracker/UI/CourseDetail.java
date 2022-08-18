package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.termtracker.Database.Repository;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Entity.User;
import com.example.termtracker.Helper.DaPicker;
import com.example.termtracker.Helper.Notify;
import com.example.termtracker.R;

import java.util.Locale;

public class CourseDetail extends AppCompatActivity {

    Repository repo;

    EditText courseTitleEditText;
    EditText courseStartEditText;
    EditText courseEndEditText;
    Spinner statusSpinner;
    EditText instructorNameEditText;
    EditText instructorPhoneEditText;
    EditText instructorEmailEditText;
    EditText notesEditText;

    boolean isNewCourse = false;
    Course course;
    Term term;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        initializeRepo();
        initializeView();
        getCourseAttributesFromIntent();
        setTextForViews();
        readElements();
        createRecycler();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                System.out.println("home selected in course detail");

                backToTermDetail();
                return true;
            case R.id.share:

                Intent sIntent = new Intent();
                sIntent.setAction(Intent.ACTION_SEND);


                String msgTitle = String.format(Locale.US, "TermTracker %s Notes", course.getTitle());


                String msg = String.format(Locale.US,
                        "Check out my Course Detail notes from TextTracker.\n\n%s\n",course.getTitle());

                if (course.getNote()!=null) {
                    msg = msg + String.format("%s", course.getNote());
                } else {
                    msg = msg + "There are no notes.";
                }

                sIntent.putExtra(Intent.EXTRA_TEXT, msg);
                sIntent.putExtra(Intent.EXTRA_TITLE, msgTitle);
                sIntent.setType("text/plain");

                Intent cIntent = Intent.createChooser(sIntent, null);
                startActivity(cIntent);

                return true;

            case R.id.notify:

                String startDate = courseStartEditText.getText().toString();
                String startMsg = String.format(Locale.US, "Course: %s starts today, %s", course.getTitle(), startDate);
                Notify.run(this, startDate, startMsg);

                String endDate = courseEndEditText.getText().toString();
                String endMsg = String.format("Course: %s ends today, %s", course.getTitle(), endDate);
                Notify.run(this, endDate, endMsg);

                return true;

            case R.id.deleteCourse:
                repo.delete(course);
                backToTermDetail();
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

        courseTitleEditText = findViewById(R.id.courseNameEditText);
        courseStartEditText = findViewById(R.id.courseStartDateEditText);
        courseEndEditText = findViewById(R.id.courseEndDateEditText);
        statusSpinner = findViewById(R.id.courseStatusSpinner);
        instructorNameEditText = findViewById(R.id.courseInstructorNameEditText);
        instructorEmailEditText = findViewById(R.id.courseInstructorEmailEditText);
        instructorPhoneEditText = findViewById(R.id.courseInstructorPhoneEditText);
        notesEditText = findViewById(R.id.courseNotesEditText);

    }

    private void getCourseAttributesFromIntent() {

        course = repo.getCourseByID(getIntent().getIntExtra(Course.ID_KEY, -1));
        if(course == null) {
            course = new Course();
            isNewCourse = true;

        }
        term = repo.getTermByID(getIntent().getIntExtra(Term.ID_KEY, -1));
        user = repo.getUserById(term.getUserId_FK());
        course.setTermId_FK(term.getId());
        System.out.printf(Locale.US, "cid: %d, tid: %d%n", course.getId(), course.getTermId_FK());

    }

    private void setTextForViews() {

        courseTitleEditText.setText(course.getTitle());
        courseStartEditText.setText(course.getStartDate());
        courseEndEditText.setText(course.getEndDate());

        DaPicker startPicker = new DaPicker(CourseDetail.this, courseStartEditText);
        startPicker.activate();
        DaPicker endPicker = new DaPicker(CourseDetail.this, courseEndEditText);
        endPicker.activate();

        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Course.STATUS_ALL);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statusSpinner.setAdapter(adapter);
        int pos = adapter.getPosition(course.getStatus());
        statusSpinner.setSelection(pos, true);
        instructorNameEditText.setText(course.getInstructorName());
        instructorPhoneEditText.setText(course.getInstructorPhone());
        instructorEmailEditText.setText(course.getInstructorEmail());
        notesEditText.setText(course.getNote());

    }

    private void readElements() {

        course.setTitle(String.valueOf(courseTitleEditText.getText()));
        course.setStartDate(String.valueOf(courseStartEditText.getText()));
        course.setEndDate(String.valueOf(courseEndEditText.getText()));
        course.setStatus(statusSpinner.getSelectedItem().toString());
        course.setNote(String.valueOf(notesEditText.getText()));
        course.setInstructorName(String.valueOf(instructorNameEditText.getText()));
        course.setInstructorPhone(String.valueOf(instructorPhoneEditText.getText()));
        course.setInstructorEmail(String.valueOf(instructorEmailEditText.getText()));

    }

    public void saveButton(View view) {

        // go back to term detail for term id.
        readElements();
        repo.inOrUp(course);
        backToTermDetail();
    }

    private void createRecycler() {
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        Repository repo = new Repository(getApplication());

        final AssessmentAdapter aAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(aAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        aAdapter.setAssessments(repo.getAssessmentByCourseId(course.getId()));

    }

    private void backToTermDetail() {
        Intent intent = new Intent(this, TermDetail.class);
        intent.putExtra(Term.ID_KEY, course.getTermId_FK());
        intent.putExtra(User.ID_KEY, term.getUserId_FK());
        this.startActivity(intent);
    }

    public void newAssessment(View view) {
        Intent intent = new Intent(this, AssessmentDetail.class);
        intent.putExtra(Course.ID_KEY, course.getId());
        this.startActivity(intent);
    }

}