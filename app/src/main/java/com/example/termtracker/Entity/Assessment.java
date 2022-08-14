package com.example.termtracker.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment",
        foreignKeys = @ForeignKey(
                entity = Course.class,
                parentColumns = "id",
                childColumns = "courseId_FK",
                onDelete = ForeignKey.CASCADE
        ))
public class Assessment {

    public static final String ID_KEY = "assessment_key";
    public static final String OBJECTIVE = "Objective";
    public static final String PERFORMANCE = "Performance";
    public static final String[] TEST_TYPES = {OBJECTIVE, PERFORMANCE};


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String testType;
    private String startDate;
    private String endDate;
    private int courseId_FK;

    public Assessment(String title, String testType, String startDate, String endDate, int courseId_FK) {
        this.title = title;
        this.testType = testType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseId_FK = courseId_FK;

    }

    public Assessment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getCourseId_FK() {
        return courseId_FK;
    }

    public void setCourseId_FK(int courseId_FK) {
        this.courseId_FK = courseId_FK;
    }
}
