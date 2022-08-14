package com.example.termtracker.Database;

import android.app.Application;

import com.example.termtracker.DAO.AssessmentDao;
import com.example.termtracker.DAO.CourseDao;
import com.example.termtracker.DAO.TermDao;
import com.example.termtracker.Entity.Assessment;
import com.example.termtracker.Entity.Course;
import com.example.termtracker.Entity.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final TermDao mTermDAO;
    private final CourseDao mCourseDAO;
    private final AssessmentDao mAssessmentDAO;
    private List<Term> mTerms;
    private List<Course> mCourses;
    private List<Assessment> mAssessments;
    private Term mTerm;
    private Course mCourse;
    private Assessment mAssessment;
    private static final int DELAY = 250;

    // Make thread
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        ProgramBuilder db = ProgramBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDao();
    }

    // TERM
    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            mTerms = mTermDAO.getAll();
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mTerms;
    }

    public Term getTermByID(int id) {
        databaseExecutor.execute(() -> {
            mTerm = mTermDAO.getTermByID(id);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mTerm;
    }

    public void delete(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.delete(term);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }

    public void inOrUp(Term term) {
        databaseExecutor.execute(() -> {
            mTermDAO.inOrUp(term);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }

    // ASSESSMENT
    public Assessment getAssessmentByID(int id) {
        databaseExecutor.execute(() -> {
            mAssessment = mAssessmentDAO.getAssessmentByID(id);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mAssessment;
    }

    public List<Assessment> getAssessmentByCourseId(int courseId) {
        databaseExecutor.execute(() -> {
            mAssessments = mAssessmentDAO.getAssessmentsByCourseID(courseId);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mAssessments;
    }

    public void inOrUp(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.inOrUp(assessment);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }

    public void delete(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.delete(assessment);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }


    // COURSE
    public Course getCourseByID(int id) {
        databaseExecutor.execute(() -> {
            mCourse = mCourseDAO.getCourseByID(id);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mCourse;
    }

    public List<Course> getCoursesByTermID(int termId) {
        databaseExecutor.execute(() -> {
            mCourses = mCourseDAO.getCoursesByTermID(termId);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mCourses;

    }

    public void inOrUp(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.inOrUp(course);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }

    public void delete(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.delete(course);
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }


    public List<Course> getAllCourses() {
        databaseExecutor.execute(() -> {
            mCourses = mCourseDAO.getAll();
        });
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return mCourses;

    }
}
