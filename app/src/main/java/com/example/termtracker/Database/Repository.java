package com.example.termtracker.Database;

import android.app.Application;

import com.example.termtracker.DAO.CourseDao;
import com.example.termtracker.DAO.InstructorDao;
import com.example.termtracker.DAO.TermDao;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Instructor;
import com.example.termtracker.Entity.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private TermDao mTermDAO;
    private CourseDao mCourseDAO;
    private InstructorDao mInstructorDAO;
    private List<Term> mTerms;
    private List<Course> mCourses;
    private List<Instructor> mInstructors;
    private Instructor mInstructor;
    private Term mTerm;

    // Make thread
    private static int NUMBER_OF_THREADS = 3;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        ProgramBuilder db = ProgramBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mInstructorDAO = db.instructorDAO();
    }


    // INSTRUCTOR
    public List<Instructor> getAllInstructors() {
        databaseExecutor.execute(() -> {
            mInstructors = mInstructorDAO.getAll();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mInstructors;
    }

    public Instructor getInstructorByName(String name) {
        databaseExecutor.execute(() -> {
            mInstructor = mInstructorDAO.getInstructorBy(name);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mInstructor;
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

    public void delete(Instructor instructor) {
        databaseExecutor.execute(() -> {
            mInstructorDAO.delete(instructor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void inOrUp(Instructor instructor) {

        databaseExecutor.execute(() -> {
            mInstructorDAO.inOrUp(instructor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
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

    public Term getByID(int id) {
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
