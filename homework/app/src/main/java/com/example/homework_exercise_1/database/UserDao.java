package com.example.homework_exercise_1.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Query("SELECT * FROM user WHERE uid = 1")
    User getUser();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Update
    int update(User user);

    @Insert
    void insert(User user);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

}
