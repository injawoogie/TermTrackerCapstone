package com.example.termtracker.Entity;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.termtracker.Helper.Utility;

import java.time.LocalDate;
import java.util.Locale;

@Entity(tableName = "term",
        foreignKeys = @ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "userId_FK",
        onDelete = ForeignKey.CASCADE
))
public class Term {


    public static final String ID_KEY = "term_key";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String startDate;
    private String endDate;
    private int userId_FK;

    public Term(String title, String startDate, String endDate, int userId_fk) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        userId_FK = userId_fk;
    }

    public Term(String title, int userId_fk) {
        this.title = title;
        this.startDate = Utility.localDateToString(LocalDate.now());
        this.endDate = Utility.localDateToString(LocalDate.now().plusDays(15));
        userId_FK = userId_fk;
    }

    public Term() {
        this.startDate = Utility.localDateToString(LocalDate.now());
        this.endDate = Utility.localDateToString(LocalDate.now().plusDays(15));
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.US, "Term ID: %d, Title: %s", id, title);
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

    public int getUserId_FK() {
        return userId_FK;
    }

    public void setUserId_FK(int userId_FK) {
        this.userId_FK = userId_FK;
    }
}
