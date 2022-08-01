package com.example.termtracker.DB;

import static androidx.room.Room.databaseBuilder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.termtracker.DAO.CourseDAO;
import com.example.termtracker.DAO.TermDAO;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;

@Database(entities={Term.class, Course.class}, version=1, exportSchema = false)
public abstract class CurriculumBuilder extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();

    private static volatile CurriculumBuilder INSTANCE;

    public static CurriculumBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (CurriculumBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = databaseBuilder(context.getApplicationContext(), CurriculumBuilder.class, "myCurriculumDatabase")
                            .fallbackToDestructiveMigration()
//                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
