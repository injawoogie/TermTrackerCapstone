package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.termtracker.Database.Repository;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Entity.User;
import com.example.termtracker.Helper.DaPicker;
import com.example.termtracker.Helper.Notify;
import com.example.termtracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TermDetail extends AppCompatActivity {

    EditText termIdEditText;
    EditText termTitleEditText;
    EditText termStartEditText;
    EditText termEndEditText;
    TextView courseListTextView;
    RecyclerView courseRecyclerView;
    FloatingActionButton addTermButton;

    boolean isNewTerm = false;
    Term term;
    User user;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        initializeRepo();
        initializeView();
        getTermAttributesFromIntent();
        setTextForViews();
        readElements();
        createRecycler();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_term_detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                toTermsList();
                return true;

            case R.id.deleteTerm:
                if(repo.getCoursesByTermID(term.getId()).isEmpty()) {
                    repo.delete(term);
                    toTermsList();
                } else {
                    Notify.show(this, "Please delete all courses for this term.");
                }

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

        addTermButton = findViewById(R.id.floatingActionButton);
        courseRecyclerView = findViewById(R.id.courseRecyclerView);
        courseListTextView = findViewById(R.id.termDetailCourseListTextView);

        termIdEditText = findViewById(R.id.termiDEditText);
        termIdEditText.setEnabled(false);

        termTitleEditText = findViewById(R.id.termTitleEditText);

        termStartEditText = findViewById(R.id.termStartDateEditText);

        termEndEditText = findViewById(R.id.termEndDateEditText);

        DaPicker startPicker = new DaPicker(TermDetail.this, termStartEditText);
        startPicker.activate();

        DaPicker endPicker = new DaPicker(TermDetail.this, termEndEditText);
        endPicker.activate();

    }

    private void getTermAttributesFromIntent() {

        term = repo.getTermByID(getIntent().getIntExtra(Term.ID_KEY, -1));
        user = repo.getUserById(getIntent().getIntExtra(User.ID_KEY, -1));

        if(term == null) {
            term = new Term();
            term.setUserId_FK(user.getId());
            isNewTerm = true;
        }

    }

    private void setTextForViews() {

        termIdEditText.setText(String.valueOf(term.getId()));
        termTitleEditText.setText(term.getTitle());
        termStartEditText.setText(term.getStartDate());
        termEndEditText.setText(term.getEndDate());

        if (isNewTerm) {
            addTermButton.hide();
            courseListTextView.setVisibility(View.INVISIBLE);
            termIdEditText.setText(R.string.auto_generated);
        }
    }

    private void readElements() {

        term.setTitle(String.valueOf(termTitleEditText.getText()));
        term.setStartDate(String.valueOf(termStartEditText.getText()));
        term.setEndDate(String.valueOf(termEndEditText.getText()));

    }

    public void saveButton(View view) {

        readElements();
        repo.inOrUp(term);
        toTermsList();
    }

    private void createRecycler() {
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        Repository repo = new Repository(getApplication());

        final CourseAdapter cAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(cAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cAdapter.setCourses(repo.getCoursesByTermID(term.getId()));

    }

    public void addNewCourse(View view) {
        Intent intent = new Intent(TermDetail.this, CourseDetail.class);
        intent.putExtra(Term.ID_KEY, term.getId());
        startActivity(intent);
    }

    private void toTermsList() {
        Intent intent = new Intent(this, TermList.class);
        intent.putExtra(User.ID_KEY, user.getId());
        this.startActivity(intent);
    }


}