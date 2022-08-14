package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import com.example.termtracker.Helper.DaPicker;
import com.example.termtracker.Helper.Noticeiver;
import com.example.termtracker.Helper.Utility;
import com.example.termtracker.R;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class CourseDetail extends AppCompatActivity {

    Repository repo;

    EditText courseTitleEditText;
    EditText courseStartEditText;
    EditText courseEndEditText;
    Spinner statusSpinner;
    EditText instructorNameEditText;
    EditText instructorEmailEditText;
    EditText notesEditText;

    boolean isNewCourse = false;
    Course course;
    Term term;

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
                sIntent.putExtra(Intent.EXTRA_TEXT, "Text extra");
                sIntent.putExtra(Intent.EXTRA_TITLE, "Text Title");
                sIntent.setType("text/plain");

                Intent cIntent = Intent.createChooser(sIntent, null);
                startActivity(cIntent);

                return true;

            case R.id.notify:

                String date = courseStartEditText.getText().toString();
                Date myDate = null;
                try {
                    myDate = Utility.SIMPLE_DATE_FORMAT.parse(date);

                } catch (ParseException exception) {
                    exception.printStackTrace();
                }

                long trigger = myDate.getTime();
                Intent intent = new Intent(CourseDetail.this, Noticeiver.class);
                intent.putExtra(Noticeiver.CONTENT_KEY, "Course starts today!");

                PendingIntent pIntent = PendingIntent.getBroadcast(CourseDetail.this,
                        Main.alertNum++,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE);

                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                manager.set(AlarmManager.RTC_WAKEUP, trigger, pIntent);

                return true;

            case R.id.deleteCourse:
                repo.delete(course);
                backToTermDetail();
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
        notesEditText = findViewById(R.id.courseNotesEditText);

    }

    private void getCourseAttributesFromIntent() {

        course = repo.getCourseByID(getIntent().getIntExtra(Course.ID_KEY, -1));
        if(course == null) {
            course = new Course();
            isNewCourse = true;

        }
        term = repo.getTermByID(getIntent().getIntExtra(Term.ID_KEY, -1));
        course.setTermId_FK(term.getId());
        System.out.println(String.format(Locale.US, "cid: %d, tid: %d", course.getId(), course.getTermId_FK()));

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
        this.startActivity(intent);
    }

    public void newAssessment(View view) {
        Intent intent = new Intent(this, AssessmentDetail.class);
        intent.putExtra(Course.ID_KEY, course.getId());
        this.startActivity(intent);
    }

}