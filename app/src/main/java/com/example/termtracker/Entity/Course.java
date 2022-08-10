package com.example.termtracker.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "course",
        foreignKeys = @ForeignKey(
                entity = Term.class,
                parentColumns = "id",
                childColumns = "termIdFK",
                onDelete = ForeignKey.CASCADE
        ))
public class Course {

    // Status options (in progress, completed, dropped, plan to take)
    public static final String IN_PROGRESS = "in progress";
    public static final String COMPLETED = "completed";
    public static final String DROPPED = "dropped";
    public static final String PLAN_TO_TAKE = "plan to take";
    public static final String[] statusAll = new String[] {IN_PROGRESS, COMPLETED, DROPPED, PLAN_TO_TAKE};

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String startDate;
    private String endDate;
    private String status;
    private String instructorName;
    private String instructorEmail;
    private String note;
    private int termIdFK;

    public Course(String title, String instructorName, String instructorEmail, String startDate, String endDate, String status, String note, int termIdFK) {
        this.title = title;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.note = note;
        this.termIdFK = termIdFK;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String notes) {
        this.note = note;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public int getTermIdFK() {
        return termIdFK;
    }

    public void setTermIdFK(int termIdFK) {
        this.termIdFK = termIdFK;
    }
}
