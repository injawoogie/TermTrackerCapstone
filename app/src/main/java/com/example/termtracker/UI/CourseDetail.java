package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.termtracker.Helper.DaPicker;
import com.example.termtracker.Helper.Noticeiver;
import com.example.termtracker.Helper.Utility;
import com.example.termtracker.R;

import java.text.ParseException;
import java.util.Date;

public class CourseDetail extends AppCompatActivity {

    Repository repo;

    EditText courseTitleEditText;
    EditText courseStartEditText;
    EditText courseEndEditText;
    Spinner statusSpinner;
    EditText instructorNameEditText;
    EditText instructorEmailEditText;
    EditText notesEditText;

    String title;
    String start;
    String end;
    String status;
    String name;
    String email;
    String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        initializeRepo();
        initializeView();
        getValuesFromView();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
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



        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeRepo() {
        repo = new Repository(getApplication());
    }

    private void initializeView() {
        courseTitleEditText = findViewById(R.id.courseNameEditText);
//        courseTitleEditText.setText(getIntent().getStringExtra("name"));

        courseStartEditText = findViewById(R.id.courseStartDateEditText);
//        courseStartEditText.setText(getIntent().getStringExtra("startDate"));
        DaPicker startPicker = new DaPicker(CourseDetail.this, courseStartEditText);
        startPicker.activate();

        courseEndEditText = findViewById(R.id.courseEndDateEditText);
//        courseEndEditText.setText(getIntent().getStringExtra("endDate"));
        DaPicker endPicker = new DaPicker(CourseDetail.this, courseEndEditText);
        endPicker.activate();

        statusSpinner = findViewById(R.id.courseStatusSpinner);

        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Course.statusAll);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statusSpinner.setAdapter(adapter);
//        status = getIntent().getStringExtra("status");
        int pos = adapter.getPosition("dropped");
        statusSpinner.setSelection(pos, true);

        instructorNameEditText = findViewById(R.id.courseInstructorNameEditText);

        instructorEmailEditText = findViewById(R.id.courseInstructorEmailEditText);

        notesEditText = findViewById(R.id.courseNotesEditText);
    }

    private void getValuesFromView() {

        title = String.valueOf(courseTitleEditText.getText());
        start = String.valueOf(courseStartEditText.getText());
        end = String.valueOf(courseEndEditText.getText());
        name = String.valueOf(instructorNameEditText.getText());
        email = String.valueOf(instructorEmailEditText.getText());
        notes = String.valueOf(notesEditText.getText());
        status = statusSpinner.toString();

    }

    public void saveButton(View view) {

        getValuesFromView();
        int id = 1;
        Course course = repo.getCourseByID(id);

        if (course == null) {

            course = new Course(title,name,email,start,end,status,notes,id);

        } else {

            course.setTitle(title);
            course.setStartDate(start);
            course.setEndDate(end);
            course.setInstructorName(name);
            course.setInstructorEmail(email);
            course.setNote(notes);
            course.setTermIdFK(id);
        }

        repo.inOrUp(course);
        this.finish();

    }
}