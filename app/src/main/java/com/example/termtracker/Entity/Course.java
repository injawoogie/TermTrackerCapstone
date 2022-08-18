package com.example.termtracker.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.termtracker.Helper.Utility;

import java.time.LocalDate;

@Entity(tableName = "course",
        foreignKeys = @ForeignKey(
                entity = Term.class,
                parentColumns = "id",
                childColumns = "termId_FK",
                onDelete = ForeignKey.RESTRICT
        ))
public class Course {

    public static final String ID_KEY = "course_key";
    public static final String IN_PROGRESS = "in progress";
    public static final String COMPLETED = "completed";
    public static final String DROPPED = "dropped";
    public static final String PLAN_TO_TAKE = "plan to take";
    public static final String[] STATUS_ALL = new String[] {IN_PROGRESS, COMPLETED, DROPPED, PLAN_TO_TAKE};

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String startDate;
    private String endDate;
    private String status;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhone;
    private String note;
    private int termId_FK;

    public Course(String title, String instructorName, String instructorEmail, String instructorPhone, String startDate, String endDate, String status, String note, int termIdFK) {
        this.title = title;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this. instructorPhone = instructorPhone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.note = note;
        this.termId_FK = termIdFK;

    }

    public Course() {
        this.startDate = Utility.localDateToString(LocalDate.now());
        this.endDate = Utility.localDateToString(LocalDate.now().plusDays(15));

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

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public int getTermId_FK() {
        return termId_FK;
    }

    public void setTermId_FK(int termId_FK) {
        this.termId_FK = termId_FK;
    }
}
