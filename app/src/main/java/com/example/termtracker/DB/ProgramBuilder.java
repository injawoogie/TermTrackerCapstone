package com.example.termtracker.DB;

import static androidx.room.Room.databaseBuilder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.termtracker.DAO.CourseDAO;
import com.example.termtracker.DAO.InstructorDAO;
import com.example.termtracker.DAO.TermDAO;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Instructor;
import com.example.termtracker.Entity.Term;

@Database(entities={Term.class, Course.class, Instructor.class}, version=5, exportSchema = false)
public abstract class ProgramBuilder extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract InstructorDAO instructorDAO();

    private static volatile ProgramBuilder INSTANCE;

    public static ProgramBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ProgramBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = databaseBuilder(context.getApplicationContext(),
                                               ProgramBuilder.class,
                                               "programDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
