package com.example.termtracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.termtracker.Entity.Assessment;

import java.util.List;

@Dao
public interface AssessmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("select * from assessment order by id asc")
    List<Assessment> getAll();

    @Query("select * from assessment where id = :id limit 1")
    Assessment getAssessmentByID(int id);

    default void inOrUp(Assessment assessment) {
        Assessment found = getAssessmentByID(assessment.getId());
        if (found == null) {
            System.out.println("Assessment not found. Inserting new.");
            insert(assessment);

        } else {
            System.out.println("Assessment found. Updating.");
            update(assessment);
        }
    }

    @Query("select * from assessment where courseId_FK = :courseId")
    List<Assessment> getAssessmentsByCourseID(int courseId);
}
