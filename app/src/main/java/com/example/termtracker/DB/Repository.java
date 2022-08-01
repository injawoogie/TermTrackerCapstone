package com.example.termtracker.DB;

import android.app.Application;

import com.example.termtracker.DAO.CourseDAO;
import com.example.termtracker.DAO.InstructorDAO;
import com.example.termtracker.DAO.TermDAO;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Instructor;
import com.example.termtracker.Entity.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private InstructorDAO mInstructorDAO;
    private List<Term> mTerms;
    private List<Course> mCourses;
    private List<Instructor> mInstructors;

    // Make thread
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        ProgramBuilder db = ProgramBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mInstructorDAO = db.instructorDAO();

    }

    public List<Instructor> getAllInstructors() {
        databaseExecutor.execute(() -> {
            mInstructors = mInstructorDAO.getAllInstructors();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mInstructors;

    }

    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            mTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mTerms;
    }

    public List<Course> getAllCourses() {
        databaseExecutor.execute(() -> {
            mCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mCourses;
    }

    public void insert(Instructor instructor) {
        databaseExecutor.execute(() -> {
            mInstructorDAO.insert(instructor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }


    public void insert(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void insert(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }

    // TODO: do delete, update


}
