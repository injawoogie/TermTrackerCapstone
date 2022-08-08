package com.example.termtracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.termtracker.Entity.Instructor;

import java.util.List;

@Dao
public interface InstructorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Instructor instructor);

    @Update
    void update(Instructor instructor);

    @Delete
    void delete(Instructor instructor);

    @Query("select * from instructor order by id asc")
    List<Instructor> getAll();

    @Query("select * from instructor where name = :name limit 1")
    Instructor getInstructorBy(String name);

    default void inOrUp(Instructor instructor) {
        Instructor found = getInstructorBy(instructor.getName());
        if (found == null) {
            System.out.println("Instructor not found.");
            insert(instructor);

        } else {

            System.out.println("Instrucors found");
            update(instructor);
        }
    }
}
