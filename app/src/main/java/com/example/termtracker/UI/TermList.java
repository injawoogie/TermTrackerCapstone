package com.example.termtracker.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.termtracker.Database.Repository;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.R;

import java.util.Objects;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Set up recycler view
        RecyclerView rView = findViewById(R.id.termRecyclerView);

        // Grab new instance of Database
        Repository repo = new Repository(getApplication());

        // Set up new instance of the adapter and set to recycler view
        final TermAdapter tAdapter = new TermAdapter(this);
        rView.setAdapter(tAdapter);
        rView.setLayoutManager(new LinearLayoutManager(this));
        tAdapter.setTerms(repo.getAllTerms());

    }

    public void addNewTerm(View view) {
        // TODO: Send to term detail view, not course list.
        Intent intent = new Intent(TermList.this, TermDetail.class);
        intent.putExtra(Term.ID_KEY, -1);
        startActivity(intent);
    }
}