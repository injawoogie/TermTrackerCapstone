package com.example.termtracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.termtracker.Entity.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("select * from user where userName = :userName limit 1")
    User getUserByUserName(String userName);

    @Query("select * from user where id = :id limit 1")
    User getUserById(int id);

    default void inOrUp(User user) {
        User found = getUserByUserName(user.getUserName());
        if (found == null) {
            insert(user);
        } else {
            update(user);
        }

    }


}
