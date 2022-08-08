package com.example.termtracker.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "course",
        foreignKeys = @ForeignKey(
                entity = Instructor.class,
                parentColumns = "id",
                childColumns = "instructor_id",
                onDelete = ForeignKey.CASCADE),
        indices = @Index("instructor_id"))
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String startDate;
    private String endDate;
    private String status;

    private int instructor_id;

    // Status options (in progress, completed, dropped, plan to take)
    public static final String IN_PROGRESS = "in progress";
    public static final String COMPLETED = "completed";
    public static final String DROPPED = "dropped";
    public static final String PLAN_TO_TAKE = "plan to take";

    public Course(String title, String startDate, String endDate, String status, int instructor_id) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructor_id = instructor_id;
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

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }
}
