package com.example.termtracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.termtracker.Entity.Term;

import java.util.List;

@Dao
public interface TermDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("select * from term order by id asc")
    List<Term> getAllTerms();


}
