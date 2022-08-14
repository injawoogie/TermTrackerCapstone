package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.termtracker.Entity.Assessment;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Helper.Utility;
import com.example.termtracker.R;

import com.example.termtracker.Database.Repository;

import java.time.LocalDate;
import java.util.Locale;

public class Main extends AppCompatActivity {

    public static int alertNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Delete before production
        makeDatabaseObjectsForTesting();

        setContentView(R.layout.activity_main);
    }

    // TODO: Delete before production
    public void makeDatabaseObjectsForTesting() {

        Repository repo = new Repository(getApplication());

        if(!repo.getAllTerms().isEmpty()) return;

        // Terms
        for (int i = 1; i < 2; i++) {
            Term term = new Term(String.format(Locale.US, "Term %d", i), Utility.localDateToString(LocalDate.now()), Utility.localDateToString(LocalDate.now()));
            repo.inOrUp(term);

            for (int j = 1; j < 2; j++) {
                Course course = new Course(
                        String.format(Locale.US,"Course %d", j),
                        "Billy Bob",
                        "billybob@wgu.edu",
                        Utility.localDateToString(LocalDate.now()),
                        Utility.localDateToString(LocalDate.now()),
                        Course.IN_PROGRESS,
                        "Some Notes go here.",
                        i);

                repo.inOrUp(course);

                for (int k = 1; k <3; k++) {
                    Assessment ass1 = new Assessment(
                            String.format(Locale.US, "Assessment %d", k),
                            Assessment.OBJECTIVE,
                            Utility.localDateToString(LocalDate.now()),
                            Utility.localDateToString(LocalDate.now()),
                            j);

                    repo.inOrUp(ass1);
                }

            }

        }

    }

    public void enterApp(View view) {
        System.out.println("Enter button pressed.");
        Intent intent = new Intent(Main.this, TermList.class);
        startActivity(intent);
    }
}