package com.example.termtracker.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.termtracker.Helper.Utility;

@Entity(tableName = "user")
public class User {

    public static final String ID_KEY = "user_key";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String password;
    private long lastLogin;

    public User(String userName, String password) {

        this.userName = userName;
        this.password = password;
        this.lastLogin = Utility.getEpochSecondsUTC();
    }

    public User() {
        this.lastLogin = Utility.getEpochSecondsUTC();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean validateCredentials(String password) {
        return this.password.equals(password);
    }
    public void resetLastLogin() {
        this.lastLogin = Utility.getEpochSecondsUTC();
    }
}
