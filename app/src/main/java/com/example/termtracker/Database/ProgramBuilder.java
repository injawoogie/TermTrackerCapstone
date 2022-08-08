package com.example.termtracker.Database;

import static androidx.room.Room.databaseBuilder;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.termtracker.DAO.CourseDao;
import com.example.termtracker.DAO.InstructorDao;
import com.example.termtracker.DAO.TermDao;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Instructor;
import com.example.termtracker.Entity.Term;

@Database(entities={Term.class, Course.class, Instructor.class}, version=6, exportSchema = false)
public abstract class ProgramBuilder extends RoomDatabase {

    public abstract TermDao termDAO();
    public abstract CourseDao courseDAO();
    public abstract InstructorDao instructorDAO();

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
