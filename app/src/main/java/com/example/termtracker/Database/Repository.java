package com.example.termtracker.Database;

import android.app.Application;

import com.example.termtracker.DAO.CourseDao;
import com.example.termtracker.DAO.TermDao;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final TermDao mTermDAO;
    private final CourseDao mCourseDAO;
    private List<Term> mTerms;
    private List<Course> mCourses;
    private Term mTerm;
    private Course mCourse;

    // Make thread
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        ProgramBuilder db = ProgramBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
    }

    // TERM
    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            mTerms = mTermDAO.getAll();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mTerms;
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

    public Term getTermByID(int id) {
        databaseExecutor.execute(() -> {
            mTerm = mTermDAO.getTermByID(id);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mTerm;
    }

    public void update(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void inOrUp(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.inOrUp(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }


    // COURSE
    public List<Course> getAllCourses() {
        databaseExecutor.execute(() -> {
            mCourses = mCourseDAO.getAll();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mCourses;
    }

    public Course getCourseByID(int id) {
        databaseExecutor.execute(() -> {
            mCourse = mCourseDAO.getCourseByID(id);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mCourse;
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

    public void inOrUp(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.inOrUp(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }



    // TODO: do delete, update


}
