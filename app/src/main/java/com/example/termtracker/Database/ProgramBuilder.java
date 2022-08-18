package com.example.termtracker.Database;

import static androidx.room.Room.databaseBuilder;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.termtracker.DAO.AssessmentDao;
import com.example.termtracker.DAO.CourseDao;
import com.example.termtracker.DAO.TermDao;
import com.example.termtracker.DAO.UserDao;
import com.example.termtracker.Entity.Assessment;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;
import com.example.termtracker.Entity.User;

@Database(entities={Term.class, Course.class, Assessment.class, User.class}, version=18, exportSchema = false)
public abstract class ProgramBuilder extends RoomDatabase {

    public abstract TermDao termDAO();
    public abstract CourseDao courseDAO();
    public abstract AssessmentDao assessmentDao();
    public abstract UserDao userDao();

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
