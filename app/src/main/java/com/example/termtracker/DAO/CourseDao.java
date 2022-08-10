package com.example.termtracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("select * from course order by id asc")
    List<Course> getAll();

    @Query("select * from course where id = :id limit 1")
    Course getCourseByID(int id);

    default void inOrUp(Course course) {
        Course found = getCourseByID(course.getId());
        if (found == null) {
            System.out.println("Course not found.");
            insert(course);

        } else {
            System.out.println("Course found");
            update(course);
        }
    }

}
