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
public interface TermDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("select * from term order by id asc")
    List<Term> getAll();

    @Query("select * from term where id = :id limit 1")
    Term getTermByID(int id);

    default void inOrUp(Term term) {
        Term found = getTermByID(term.getId());
        if (found == null) {
            System.out.println("Term not found.");
            insert(term);

        } else {
            System.out.println("Term found");
            update(term);
        }
    }
}
